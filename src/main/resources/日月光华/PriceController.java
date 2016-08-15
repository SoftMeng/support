/*
 * 文件名：PriceController.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2015年11月25日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.cfilmcloud.marketing.local;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cfilmcloud.marketing.core.codemap.Msg;
import com.cfilmcloud.marketing.price.dto.PriceActivityInfo;
import com.cfilmcloud.marketing.price.helper.FilmShowsInsertHelper;
import com.cfilmcloud.marketing.price.helper.FilmShowsPriceValidator;
import com.cfilmcloud.marketing.service.PriceEngineService;
import com.cfilmcloud.marketing.service.SceneService;
import com.cfilmcloud.marketing.service.dto.FilmShowsForPrSys;
import com.cfilmcloud.marketing.service.dto.FilmShowsInfo;
import com.cfilmcloud.masterdata.service.ChannelService;
import com.cfilmcloud.masterdata.service.dto.ChannelInfo;

/**
 * 〈提供给中台的Restful接口〉
 * 〈目前按照以前的接口规范做的〉
 * @author mengxy[孟祥元]
 * @version 2015年12月2日
 * @see PriceController
 * @since 1.0
 */
@Controller
@RequestMapping("/showprice2/**")
public class PriceController {
    private final static Logger logger = LoggerFactory.getLogger(PriceController.class);
    
//    @Autowired
//    private PriceCommonHelper priceCommonHelper;
    
    @Autowired
    private FilmShowsInsertHelper filmShowsInsertHelper;
    
    @Autowired
    private PriceHttpHelper priceHttpHelper;
    
    @Reference(version="1.0.0")
    private SceneService sceneService;
    @Reference(version="1.0.0")
    private ChannelService channelService;
    @Reference(version="1.0.0")
    private PriceEngineService priceEngineService;
    /**
     * 线程粒度，每count个场次启动一个线程处理<br>
     */
    private int count = 1000;
    
    /**
     * 用Spring的ThreadPoolTaskExecutor<br>
     */
//    @Resource
//    private ThreadPoolTaskExecutor taskExecutor;
    
    ExecutorService pool = Executors.newFixedThreadPool(100);
    
    /**
     * Description: 询价接口 <br>
     * 1、询价接口必备的参数为场次信息列表<br>
     * 2、询价引擎必备的参数为，场次信息详情和活动规则信息<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     * 
     * @param list
     * @return 
     * @see 
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> askPrice(@RequestBody List<FilmShowsForPrSys> filmShowsForPrSysList) {
        priceHttpHelper.setRequestDate(new Date());//记录请求时间
        final long start1 = System.nanoTime();
        String mark = java.util.UUID.randomUUID().toString();
        List<PriceActivityInfo> priceActivityInfos = priceEngineService.getPriceActivityInfo(null,mark); 
        final long bt1 = System.nanoTime();
        logger.info(mark + "--调用priceEngineService.getPriceActivityInfo获取costTimeis："+String.valueOf((bt1 - start1)/1.0e9));
        priceHttpHelper.setPriceActivityInfos(priceActivityInfos);
        logger.info(mark + "--接受到电商的场次推送请求,SHOWFILM.SIZE为："+filmShowsForPrSysList.size());
        Map<String,Object> dataMap = new HashMap<String,Object>();
        
        FilmShowsPriceValidator validator = new FilmShowsPriceValidator(filmShowsForPrSysList) ;
        validator.valid(); // pathVariable validate
        if(validator.hasError()){//验证参数是否存在错误
            dataMap.put(Msg.RET_COCE.getValue(),Msg.RET_FAILURE.getValue()) ; //failure
            dataMap.put(Msg.RET_MSG.getValue(),validator.getErrors()) ;
            return dataMap ;
        }
//        List<FilmShowsForPrSys> filmShowsForPrSysList = new ArrayList<FilmShowsForPrSys>();
//        for(int m=0;m<1000;m++){
//            FilmShowsForPrSys e = new FilmShowsForPrSys();
//            e.setSid("m"+m);
//            e.setShowDate("2015-12-01");
//            e.setShowTime("1700");
//            filmShowsForPrSysList.add(e);
//        }
        /*******************************将场次数据记录到营销活动数据库Start******************************/
        filmShowsInsertHelper.setFilmShowsForPrSys(filmShowsForPrSysList);
        new Thread(filmShowsInsertHelper).start();
        /*******************************将场次数据记录到营销活动数据库End******************************/
        int len = filmShowsForPrSysList.size();
        //  如果设置为场次分线程处理，那么按照粒度去实现。场次作为源数据，不允许其内部数据修改。
        if(count>1){
            for(int i=0;i<len/count+1;i++){
                int from = count*i;
                int to = count*(i+1);
                if(to<=len){
                    priceHttpHelper.setFilmShowsForPrSys(filmShowsForPrSysList.subList(from, to));
                    pool.execute(priceHttpHelper);
                }else{
                    if(from!=len){
                        priceHttpHelper.setFilmShowsForPrSys(filmShowsForPrSysList.subList(from, len));
                        pool.execute(priceHttpHelper);
                    }
                }
            }
        // 不分线程，所有场次同步处理
        }else{
            priceHttpHelper.setFilmShowsForPrSys(filmShowsForPrSysList);
            pool.execute(priceHttpHelper);
        }
        //启动完多线程后，直接返回成功
        dataMap.put(Msg.RET_COCE.getValue(),Msg.RET_SUCCESS.getValue()) ; //success
        return dataMap;
    }
    
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @ResponseBody
//    public String doIYYYY() {
//        List<FilmShowsForPrSys> filmShowsForPrSysList = new ArrayList<FilmShowsForPrSys>();
//        for (int m = 0; m < 20; m++) {
//            FilmShowsForPrSys e = new FilmShowsForPrSys();
//            e.setSid("" + m);
//            e.setShowDate("2015-12-01");
//            e.setShowTime("1700");
//            filmShowsForPrSysList.add(e);
//        }
//        filmShowsInsertHelper.setFilmShowsForPrSys(filmShowsForPrSysList);
//        new Thread(filmShowsInsertHelper).start();
//        return "ok";
//    }
}
