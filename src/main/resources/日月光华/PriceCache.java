/*
 * 文件名：PriceCache.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月29日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.cfilmcloud.marketing.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cfilmcloud.marketing.price.dto.PriceActivityInfo;
import com.cfilmcloud.marketing.service.PriceEngineService;
import com.cfilmcloud.masterdata.service.ChannelService;
import com.cfilmcloud.masterdata.service.dto.ChannelInfo;

/**
 * 〈EHcache缓存－－算价引擎〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月29日
 * @see PriceCache
 * @since 1.0
 */
@Component
/**
 * cacheNames属性表示使用哪个缓存策略，缓存策略在ehcache.xml
 */
@CacheConfig(cacheNames = "priceCache")
public class PriceCache {

    @Reference(version = "1.0.0")
    private ChannelService channelService;
    @Reference(version = "1.0.0")
    private PriceEngineService priceEngineService;

    //@Cacheable(value=PRICE_CACHE_NAME, key = "#root.target.MY_KEY")
    @Cacheable(key = "#root.methodName")
    public List<String> getChannels() {
        List<ChannelInfo> channelList = null;
        channelList = channelService.getChannels(null, null, null);
        List<String> channels = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(channelList)) {
            for (ChannelInfo ci : channelList) {
                channels.add(ci.getChannelCode());
            }
        }
        System.out.println("haha2");
        return channels;
    }

    @Cacheable(key = "#root.methodName")
    public List<PriceActivityInfo> getActivities() {
        List<PriceActivityInfo> activities = null;
        String mark = java.util.UUID.randomUUID().toString();
        activities = priceEngineService.getPriceActivityInfo(null, mark);
        System.out.println("haha");
        return activities;
    }

    @Cacheable(key = "#root.methodName")
    public String test(String str) {
        System.out.println("haha:" + str);
        return str;
    }
}
