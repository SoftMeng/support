/*
 * 文件名：RedisPub.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年4月1日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.cfilmcloud.marketing.local;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisPub {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    public void sendMessage(String channel, Serializable message) {
        System.out.println(message);
        redisTemplate.convertAndSend(channel, message);
    }
}
