/*
 * 文件名：PriceEngineHelper.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：价格引擎入口
 * 修改人：mengxy[孟祥元]
 * 修改时间：2015年11月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.cfilmcloud.marketing.local;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cfilmcloud.marketing.price.PriceWorkTask;
import com.cfilmcloud.marketing.price.dto.PriceEngineInfo;
import com.cfilmcloud.marketing.price.dto.PriceReturnInfo;

/**
 * 〈价格引擎入口〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2015年12月4日
 * @see PriceEngineHelper
 * @since 1.0
 */
public class PriceEngineHelper {
    
    private final static Logger logger = LoggerFactory.getLogger(PriceEngineHelper.class);
    
    private final static ForkJoinPool forkJoinPool = new ForkJoinPool();
    
    /**
     * Description: 引擎询价方法<br>
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     * 
     * @param list
     * @return 
     * @see 
     */
    public static List<PriceReturnInfo> takeResult(List<PriceEngineInfo> list){
        final long start = System.nanoTime();
        //将请求数据作为参数，建立一个有返回值的引擎任务
        PriceWorkTask task = new PriceWorkTask(list);
        // 执行该任务获取结果，此线程由线程池管理
        List<PriceReturnInfo> priceReturnInfos = forkJoinPool.invoke(task);
        final long end = System.nanoTime();
        logger.info("调用forkJoinPool.invoke算价引擎总耗时："+list.size()+"==>"+String.valueOf((end - start)/1.0e9));
        return priceReturnInfos;
    }

}
