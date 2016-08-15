///*
// * 文件名：PriceTask.java
// * 版权：Copyright by www.cfilmcloud.com
// * 描述：
// * 修改人：mengxy[孟祥元]
// * 修改时间：2016年3月28日
// * 跟踪单号：
// * 修改单号：
// * 修改内容：
// */
//
//package com.cfilmcloud.marketing.local;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.apache.log4j.Logger;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.context.annotation.Scope;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import com.cfilmcloud.marketing.core.codemap.Msg;
//import com.cfilmcloud.marketing.core.utils.JsonUtils;
//import com.cfilmcloud.marketing.price.ConfigTask;
//import com.cfilmcloud.marketing.price.PriceEngineTask;
//import com.cfilmcloud.marketing.price.PriceStaticConfig;
//import com.cfilmcloud.marketing.price.dto.MktPriceLogDto;
//import com.cfilmcloud.marketing.price.dto.PriceActivityInfo;
//import com.cfilmcloud.marketing.price.dto.PriceEngineInfo;
//import com.cfilmcloud.marketing.price.dto.PriceReturnInfo;
//import com.cfilmcloud.marketing.price.dto.PriceShowInfo;
//import com.cfilmcloud.marketing.price.helper.PriceCommonHelper;
//import com.cfilmcloud.marketing.service.dto.FilmShowsForPrSys;
//import com.cfilmcloud.marketing.service.dto.FilmShowsResult;
//import com.cfilmcloud.marketing.service.dto.ShowPriceLogInfo;
//
///**
// * 〈执行算价，争抢Redis中的资源参与运算〉
// * 〈功能详细描述〉
// * @author mengxy[孟祥元]
// * @version 2016年3月28日
// * @see PriceTask
// * @since 1.0
// */
//@Component
//@Scope("prototype")
//@CacheConfig(cacheNames = "priceCache")
//public class PriceTask {
//
//    private static Logger logger = Logger.getLogger(PriceTask.class);
//
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
//    @Resource
//    private PriceCommonHelper priceCommonHelper;
//    @Resource
//    private ConfigTask configTask;
//    @Resource
//    private PriceCache priceCache;
//
//    /**
//     * 〈算价引擎线程内部类〉
//     * 〈不断争抢资源〉
//     * @author mengxy[孟祥元]
//     * @version 2016年3月28日
//     * @see PriceThread
//     * @since 1.0
//     */
//    class PriceThread extends Thread {
//        @Override
//        public void run() {
//            while (true) {
//                try {
//                    Object obj = redisTemplate.opsForList().leftPop(PriceStaticConfig.MK_PRICE_FILMSHOW);
//                    if (obj == null) {
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        final long startAll = System.nanoTime();
//                        System.out.println("TEST:"+priceCache.test(String.valueOf(System.nanoTime())));
//                        FilmShowsForPrSys filmShowsForPrSys = (FilmShowsForPrSys) obj;
//                        List<PriceActivityInfo> activities = priceCache.getActivities();
//                        List<String> channels = priceCache.getChannels();
//                        if (activities == null || activities.isEmpty()) {
//                            continue;
//                        }
//                        if (channels == null || channels.isEmpty()) {
//                            continue;
//                        }
//                        /**********校验数据END*****************/
//                        /********初始化数据Start********/
//                        MktPriceLogDto mktPriceLogDto = new MktPriceLogDto();
//                        mktPriceLogDto.setInterfaceName("询价接口");
//                        mktPriceLogDto.setStartDate(new Date());
//                        String dataJson = "";
//                        String request = JsonUtils.getInstance().toJson(filmShowsForPrSys);
//                        String errorMsg = "";
//                        String errorCode = "0000";
//                        double time = 0.0;
//                        /********初始化数据End********/
//                        try {
//                            /***************************价格引擎计算数据Start************************************************************/
//                            final long startPrice = System.nanoTime();
//                            PriceEngineInfo priceEngineInfos = priceCommonHelper.getPriceEngineInfo(filmShowsForPrSys,
//                                    activities, channels);
//                            List<PriceReturnInfo> priceReturnInfos = new PriceEngineTask(priceEngineInfos).compute();
//                            List<FilmShowsResult> results = priceCommonHelper.trans(priceReturnInfos);
//                            priceReturnInfos = priceCommonHelper.filterByPriorityCode(priceReturnInfos);
//                            Map<String, Object> dataMap = new HashMap<String, Object>();
//                            if (results != null && results.size() > 0) {
//                                for (Iterator<FilmShowsResult> iterator = results.iterator(); iterator.hasNext();) {
//                                    FilmShowsResult filmShowsResult = iterator.next();
//                                    dataMap.put(filmShowsResult.getChannelName(),
//                                            filmShowsResult.getFilmShowsResultDetails());
//                                }
//                            }
//                            dataJson = JsonUtils.getInstance().toJson(dataMap);
//                            final long endPrice = System.nanoTime();
//                            System.out.println("PriceEngineTask-COSTTIME:" + (endPrice - startPrice) / 1.0e9);
//                            /***************************价格引擎计算数据End************************************************************/
//                            /***************************计算数据请求结果返回Start************************************************************/
//                            try {
//                                final long httpStart = System.nanoTime();
//                                HttpClient httpClient = new DefaultHttpClient();
//                                HttpPost post = new HttpPost(configTask.getOnlineShopUrl());
//                                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//                                nvps.add(new BasicNameValuePair("data", dataJson));
//                                post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//
//                                HttpResponse result = httpClient.execute(post);
//                                logger.info("DADIDIANSHANG-HTTPSTATUSCODE：" + result.getStatusLine().getStatusCode());
//                                if (HttpStatus.SC_OK == result.getStatusLine().getStatusCode()) {
//                                    // 请求结束，返回结果
//                                    String resData = EntityUtils.toString(result.getEntity());
//                                    Map<String, Object> resJson = JsonUtils.getInstance().toObject(resData, Map.class);
//                                    String code = resJson.get("status").toString(); // 对方接口请求返回结果：0成功，其余失败
//                                    if (!"0".equals(code)) {
//                                        errorCode = code;
//                                        errorMsg = resJson.get("msg").toString();
//                                    } else {
//                                        //当成功时，记录详细价格信息
//                                        final long writeDescLogStart = System.nanoTime();
//                                        if (priceReturnInfos != null) {
//                                            priceCommonHelper.WriteDescLogList(priceReturnInfos);
//                                        }
//                                        final long writeDescLogEnd = System.nanoTime();
//                                        final double writeDescLogTime = (writeDescLogEnd - writeDescLogStart) / 1.0e9;
//                                        System.out.println("priceCommonHelper.WriteDescLog.COSTTIME:" + writeDescLogTime);
//                                    }
//                                    final long httpEnd = System.nanoTime();
//                                    System.out.println("DADIDIANSHANG-COSTTIME:" + (httpEnd - httpStart) / 1.0e9);
//                                    logger.info("DADIDIANSHANG-RESPONSE:{'status':" + code + ",'msg':'"
//                                            + resJson.get("msg").toString() + "'}");
//                                }
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                                errorCode = Msg.PRICE_ERROR_HTTP.getValue();
//                                errorMsg = e.toString();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        } finally {
//                            final long endAll = System.nanoTime();
//                            time = (endAll - startAll) / 1.0e9;
//                            mktPriceLogDto.setErrorCode(errorCode);
//                            mktPriceLogDto.setErrorMsg(
//                                    errorMsg.substring(0, errorMsg.length() > 1000 ? 1000 : errorMsg.length()));
//                            mktPriceLogDto.setEndDate(new Date());
//                            mktPriceLogDto.setTimeConsuming(time);
//                            mktPriceLogDto.setRequest(request);
//                            mktPriceLogDto.setResponse(dataJson);
//                            priceCommonHelper.WriteLog(mktPriceLogDto);
//                            System.out.println("THREAD[" + Thread.currentThread().getName() + " ]COST TIME IS "
//                                    + (endAll - startAll) / 1.0e9);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void run() {
//        new PriceThread().start();
//    }
//}
