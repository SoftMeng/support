/*
 * 文件名：PriceHttpTask.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2015年11月30日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.cfilmcloud.marketing.local;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.cfilmcloud.marketing.core.codemap.Msg;
import com.cfilmcloud.marketing.core.utils.JsonUtils;
import com.cfilmcloud.marketing.price.dto.MktPriceLogDto;
import com.cfilmcloud.marketing.price.dto.PriceActivityInfo;
import com.cfilmcloud.marketing.price.dto.PriceReturnInfo;
import com.cfilmcloud.marketing.price.dto.PriceShowInfo;
import com.cfilmcloud.marketing.price.helper.PriceCommonHelper;
import com.cfilmcloud.marketing.service.dto.FilmShowsForPrSys;
import com.cfilmcloud.marketing.service.dto.FilmShowsResult;
import com.cfilmcloud.marketing.service.dto.ShowPriceLogInfo;


/**
 * 〈线程类〉
 * 〈调用引擎，并返回给中台〉
 * @author mengxy[孟祥元]
 * @version 2015年12月2日
 * @see PriceHttpHelper
 * @since 1.0
 */
@SuppressWarnings("deprecation")
@Component("priceHttpTask")
@ConfigurationProperties(locations = "classpath:application.properties")
public class PriceHttpHelper implements Runnable {
    
    @Autowired
    private PriceCommonHelper priceCommonHelper;

    // 日志记录
    private static Logger logger = Logger.getLogger(PriceHttpHelper.class);
    
    // 推送地址
    @Value("${onlineShop.url}")
    private String url;
    
    private List<FilmShowsForPrSys> filmShowsForPrSys;
    private List<PriceActivityInfo> priceActivityInfos;
    private Date requestDate;

    public PriceHttpHelper() {
    }

    public PriceHttpHelper(List<FilmShowsForPrSys> filmShowsForPrSys, Date requestDate) {
        this.filmShowsForPrSys = filmShowsForPrSys;
        this.requestDate = requestDate;
    }

    public void setFilmShowsForPrSys(List<FilmShowsForPrSys> filmShowsForPrSys) {
        this.filmShowsForPrSys = filmShowsForPrSys;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public void setPriceActivityInfos(List<PriceActivityInfo> priceActivityInfos) {
        this.priceActivityInfos = priceActivityInfos;
    }

    /* (根据电商请求数据，计算出价格，并返回给电商)
     * @see java.lang.Runnable#run()
     */
    @SuppressWarnings({ "unchecked", "resource" })
    @Override
    public void run() {
        final long startAll = System.nanoTime();
        if(filmShowsForPrSys==null||filmShowsForPrSys.isEmpty()||priceActivityInfos==null||priceActivityInfos.isEmpty()){
            return;
        }
        MktPriceLogDto mktPriceLogDto = new MktPriceLogDto();
        mktPriceLogDto.setInterfaceName("询价接口");
        mktPriceLogDto.setStartDate(new Date());
        /********初始化数据Start********/
        Map<String,Object> map = null;
        String dataJson = "";
        String request = JsonUtils.getInstance().toJson(filmShowsForPrSys);
        String errorMsg = "";
        String errorCode = "0000";
        double time = 0.0;
        /********初始化数据End********/
        try{
            /***************************价格引擎计算数据Start************************************************************/
            try{
                Map<String, Object> dataMap = new HashMap<String, Object>();
                final long start = System.nanoTime();
                map = priceCommonHelper.askPrice(filmShowsForPrSys,priceActivityInfos);
                List<FilmShowsResult> results = (List<FilmShowsResult>) map.get("results");
                final long end = System.nanoTime();
                time = (end - start)/1.0e9;
                if(filmShowsForPrSys.get(0)!=null){
                    logger.debug(filmShowsForPrSys.get(0).getSid()+"--算价总耗时："+time);
                }
                if(results!=null&&results.size()>0){
                    for (Iterator<FilmShowsResult> iterator = results.iterator(); iterator.hasNext();) {
                        FilmShowsResult filmShowsResult = iterator.next();
                        dataMap.put(filmShowsResult.getChannelName(), filmShowsResult.getFilmShowsResultDetails());
                    }
                }
                dataJson = JsonUtils.getInstance().toJson(dataMap);
            }catch(Exception e){
                e.printStackTrace();
                errorCode = Msg.PRICE_EROOR_ENGINE.getValue();
                errorMsg = e.toString();
            }
            /***************************价格引擎计算数据End************************************************************/
            /***************************计算数据请求结果返回Start************************************************************/
            try {
                final long httpStart = System.nanoTime();
                logger.info("向电商DIANSHANG-URL："+this.url+"发送请求信息.");
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost post = new HttpPost(this.url);    
                List<NameValuePair> nvps = new ArrayList <NameValuePair>(); 
                nvps.add(new BasicNameValuePair("data", dataJson));
                post.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
                
                HttpResponse result = httpClient.execute(post);
                logger.info("DADIDIANSHANG-HttpStatusCode is："+result.getStatusLine().getStatusCode());
                if(HttpStatus.SC_OK == result.getStatusLine().getStatusCode()){
                    // 请求结束，返回结果
                    String resData = EntityUtils.toString(result.getEntity());
                    Map<String, Object> resJson = JsonUtils.getInstance().toObject(resData, Map.class);
                    String code = resJson.get("status").toString(); // 对方接口请求返回结果：0成功，其余失败
                    final long httpEnd = System.nanoTime();
                    logger.info("Send message to DIANSHANG costTime:"+(httpEnd - httpStart)/1.0e9);
                    if(!"0".equals(code)){
                        errorCode = code;
                        errorMsg = resJson.get("msg").toString();
                    }else{
                        //当成功时，记录详细价格信息
                        if(map!=null){
                            final long writeDescLogStart = System.nanoTime();
                            List<PriceReturnInfo> priceReturnInfos = (List<PriceReturnInfo>)map.get("priceReturnInfos");
                            if(priceReturnInfos!=null){
                                priceCommonHelper.WriteDescLogList(priceReturnInfos);
                            }
                            final long writeDescLogEnd = System.nanoTime();
                            final double writeDescLogTime = (writeDescLogEnd - writeDescLogStart)/1.0e9;
                            logger.info("priceCommonHelper.WriteDescLog and CostTime=="+writeDescLogTime);
                        }
                    }
                    logger.info("DADIDIANSHANG-RESPONSE:{'status':" + code + ",'msg':'" + resJson.get("msg").toString() + "'}");
                }
            } catch (IOException e) {
                e.printStackTrace();
                errorCode = Msg.PRICE_ERROR_HTTP.getValue();
                errorMsg = e.toString();
            }
            /***************************计算数据请求结果返回End************************************************************/
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            mktPriceLogDto.setErrorCode(errorCode);
            mktPriceLogDto.setErrorMsg(errorMsg.substring(0,errorMsg.length()>1000?1000:errorMsg.length()));
            mktPriceLogDto.setEndDate(new Date());
            mktPriceLogDto.setTimeConsuming(time);
            mktPriceLogDto.setRequest(request);
            mktPriceLogDto.setResponse(dataJson);
            priceCommonHelper.WriteLog(mktPriceLogDto);
            final long endAll = System.nanoTime();
            System.out.println("THREAD COST TIME IS "+(endAll - startAll)/1.0e9);
        }
    }

}
