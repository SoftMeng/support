///*
// * 文件名：PriceRedisTask.java
// * 版权：Copyright by www.cfilmcloud.com
// * 描述：
// * 修改人：mengxy[孟祥元]
// * 修改时间：2016年3月18日
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
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
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
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.dubbo.common.utils.CollectionUtils;
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.cfilmcloud.marketing.core.codemap.Msg;
//import com.cfilmcloud.marketing.core.utils.JsonUtils;
//import com.cfilmcloud.marketing.price.dto.MktPriceLogDto;
//import com.cfilmcloud.marketing.price.dto.PriceActivityInfo;
//import com.cfilmcloud.marketing.price.dto.PriceEngineInfo;
//import com.cfilmcloud.marketing.price.dto.PriceReturnInfo;
//import com.cfilmcloud.marketing.price.dto.PriceShowInfo;
//import com.cfilmcloud.marketing.price.helper.PriceCommonHelper;
//import com.cfilmcloud.marketing.service.PriceEngineService;
//import com.cfilmcloud.marketing.service.dto.FilmShowsForPrSys;
//import com.cfilmcloud.marketing.service.dto.FilmShowsResult;
//import com.cfilmcloud.marketing.service.dto.ShowPriceLogInfo;
//import com.cfilmcloud.masterdata.service.ChannelService;
//import com.cfilmcloud.masterdata.service.dto.ChannelInfo;
//
///**
// * 〈循环取Redis中的数据并处理〉
// * 〈功能详细描述〉
// * @author mengxy[孟祥元]
// * @version 2016年3月21日
// * @see PriceRedisTask
// * @since 1.0
// */
//@Component
//@ConfigurationProperties(locations = "classpath:application.properties")
//public class PriceRedisTask {
//
//    private static Logger logger = Logger.getLogger(PriceRedisTask.class);
//    private static final Object LOCK_ACTIVITY = new Object();
//    private static final Object LOCK_CHANNEL = new Object();
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
//    @Resource
//    private PriceCommonHelper priceCommonHelper;
//    @Reference(version = "1.0.0")
//    private ChannelService channelService;
//    @Reference(version = "1.0.0")
//    private PriceEngineService priceEngineService;
//
//    //线程池大小
//    @Value("${pool.count}")
//    private int count = 20;
//    //使用线程workQueue的大小
//    @Value("${pool.queue.count}")
//    private int queueCount = 1000;
//    //redis缓存时间
//    @Value("${redis.expire.seconds}")
//    private int seconds = 120;
//    // 推送地址
//    @Value("${onlineShop.url}")
//    private String url;
//
//    class PriceRedisThread implements Runnable {
//        private FilmShowsForPrSys filmShowsForPrSys;
//
//        PriceRedisThread(FilmShowsForPrSys filmShowsForPrSys) {
//            this.filmShowsForPrSys = filmShowsForPrSys;
//        }
//
//        @Override
//        public void run() {
//            final long startAll = System.nanoTime();
//            /**********校验数据START*****************/
//            if (filmShowsForPrSys == null) {
//                return;
//            }
//            //获取到营销活动信息
//            List<PriceActivityInfo> activities = (List<PriceActivityInfo>) redisTemplate.opsForValue()
//                    .get("MK.PRICE.ACTIVITY");
//            if (activities == null || activities.isEmpty()) {
//                synchronized (LOCK_ACTIVITY) {
//                    activities = (List<PriceActivityInfo>) redisTemplate.opsForValue().get("MK.PRICE.ACTIVITY");
//                    if (activities == null || activities.isEmpty()) {
//                        String mark = java.util.UUID.randomUUID().toString();
//                        activities = priceEngineService.getPriceActivityInfo(null, mark);
//                        redisTemplate.opsForValue().set("MK.PRICE.ACTIVITY", activities);
//                        redisTemplate.expire("MK.PRICE.ACTIVITY", seconds, TimeUnit.SECONDS);
//                    }
//                }
//
//            }
//            //获取到渠道信息
//            List<String> channels = (List<String>) redisTemplate.opsForValue().get("MK.PRICE.CHANNEL");
//            if (channels == null || channels.isEmpty()) {
//                synchronized (LOCK_CHANNEL) {
//                    channels = (List<String>) redisTemplate.opsForValue().get("MK.PRICE.CHANNEL");
//                    if (channels == null || channels.isEmpty()) {
//                        List<ChannelInfo> channelList = channelService.getChannels(null, null, null);
//                        channels = new ArrayList<String>();
//                        if (CollectionUtils.isNotEmpty(channelList)) {
//                            for (ChannelInfo ci : channelList) {
//                                channels.add(ci.getChannelCode());
//                            }
//                        }
//                        redisTemplate.opsForValue().set("MK.PRICE.CHANNEL", channels);
//                        redisTemplate.expire("MK.PRICE.CHANNEL", seconds, TimeUnit.SECONDS);
//                    }
//                }
//            }
//            if (activities == null || activities.isEmpty()) {
//                return;
//            }
//            if (channels == null || channels.isEmpty()) {
//                return;
//            }
//            /**********校验数据END*****************/
//            /********初始化数据Start********/
//            MktPriceLogDto mktPriceLogDto = new MktPriceLogDto();
//            mktPriceLogDto.setInterfaceName("询价接口");
//            mktPriceLogDto.setStartDate(new Date());
//            String dataJson = "";
//            String request = JsonUtils.getInstance().toJson(filmShowsForPrSys);
//            String errorMsg = "";
//            String errorCode = "0000";
//            double time = 0.0;
//            /********初始化数据End********/
//            try {
//                /***************************价格引擎计算数据Start************************************************************/
//                Map<String, Object> map = new HashMap<String, Object>();
//                PriceEngineInfo priceEngineInfos = priceCommonHelper.getPriceEngineInfo(filmShowsForPrSys,
//                        activities, channels);
//                List<PriceReturnInfo> priceReturnInfos = new PriceEngineTask(priceEngineInfos).compute();
//                List<FilmShowsResult> results = priceCommonHelper.trans(priceReturnInfos);
//                priceReturnInfos = priceCommonHelper.filterByPriorityCode(priceReturnInfos);
//                map.put("priceEngineInfos", priceEngineInfos);//入参
//                map.put("priceReturnInfos", priceReturnInfos);//计算结果
//                map.put("results", results);//计算结果最后转换为电商需要的形式
//                Map<String, Object> dataMap = new HashMap<String, Object>();
//                if (results != null && results.size() > 0) {
//                    for (Iterator<FilmShowsResult> iterator = results.iterator(); iterator.hasNext();) {
//                        FilmShowsResult filmShowsResult = iterator.next();
//                        dataMap.put(filmShowsResult.getChannelName(), filmShowsResult.getFilmShowsResultDetails());
//                    }
//                }
//                dataJson = JsonUtils.getInstance().toJson(dataMap);
//                /***************************价格引擎计算数据End************************************************************/
//                /***************************计算数据请求结果返回Start************************************************************/
//                try {
//                    final long httpStart = System.nanoTime();
//                    logger.info("DIANSHANG-URL：" + url + "发送请求信息.");
//                    HttpClient httpClient = new DefaultHttpClient();
//                    HttpPost post = new HttpPost(url);
//                    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//                    nvps.add(new BasicNameValuePair("data", dataJson));
//                    post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//
//                    HttpResponse result = httpClient.execute(post);
//                    logger.info("DADIDIANSHANG-HTTPSTATUSCODE：" + result.getStatusLine().getStatusCode());
//                    if (HttpStatus.SC_OK == result.getStatusLine().getStatusCode()) {
//                        // 请求结束，返回结果
//                        String resData = EntityUtils.toString(result.getEntity());
//                        Map<String, Object> resJson = JsonUtils.getInstance().toObject(resData, Map.class);
//                        String code = resJson.get("status").toString(); // 对方接口请求返回结果：0成功，其余失败
//                        final long httpEnd = System.nanoTime();
//                        logger.info("DADIDIANSHANG-COSTTIME:" + (httpEnd - httpStart) / 1.0e9);
//                        if (!"0".equals(code)) {
//                            errorCode = code;
//                            errorMsg = resJson.get("msg").toString();
//                        } else {
//                            //当成功时，记录详细价格信息
//                            final long writeDescLogStart = System.nanoTime();
//                            if (priceReturnInfos != null) {
//                                priceCommonHelper.WriteDescLogList(priceReturnInfos);
//                            }
//                            final long writeDescLogEnd = System.nanoTime();
//                            final double writeDescLogTime = (writeDescLogEnd - writeDescLogStart) / 1.0e9;
//                            logger.info("priceCommonHelper.WriteDescLog.CostTime:" + writeDescLogTime);
//                        }
//                        logger.info("DADIDIANSHANG-RESPONSE:{'status':" + code + ",'msg':'"
//                                + resJson.get("msg").toString() + "'}");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    errorCode = Msg.PRICE_ERROR_HTTP.getValue();
//                    errorMsg = e.toString();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                mktPriceLogDto.setErrorCode(errorCode);
//                mktPriceLogDto
//                        .setErrorMsg(errorMsg.substring(0, errorMsg.length() > 1000 ? 1000 : errorMsg.length()));
//                mktPriceLogDto.setEndDate(new Date());
//                mktPriceLogDto.setTimeConsuming(time);
//                mktPriceLogDto.setRequest(request);
//                mktPriceLogDto.setResponse(dataJson);
//                priceCommonHelper.WriteLog(mktPriceLogDto);
//                final long endAll = System.nanoTime();
//                System.out.println("THREAD[" + Thread.currentThread().getName() + " ]COST TIME IS "
//                        + (endAll - startAll) / 1.0e9);
//
//            }
//        }
//    }
//
//    public void run() {
//        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
//        ExecutorService pool = new ThreadPoolExecutor(count, count, 0L, TimeUnit.MILLISECONDS, queue);
//        while (true) {
//            if (queue.size() < queueCount) {
//                if (redisTemplate.opsForList().size("MK.PRICE.FILMSHOW") > 0) {
//                    pool.execute(
//                            new PriceRedisThread((FilmShowsForPrSys) redisTemplate.opsForList().leftPop("MK.PRICE.FILMSHOW")));
//                }
//            }
//        }
//    }
//
//}
