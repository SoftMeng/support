package com.cfilmcloud.marketing.local;
///*
// * 文件名：AAA.java
// * 版权：Copyright by www.cfilmcloud.com
// * 描述：
// * 修改人：mengxy[孟祥元]
// * 修改时间：2016年3月17日
// * 跟踪单号：
// * 修改单号：
// * 修改内容：
// */
//
//package com.cfilmcloud.marketing.rest.controller;
//
//import java.util.concurrent.CountDownLatch;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.listener.PatternTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.cfilmcloud.marketing.price.RedisPub;
//import com.cfilmcloud.marketing.price.RedisSub;
//
//@Component
//@Configurable
//@EnableScheduling
//public class AAA{
//    @Resource
//    private RedisPub redisPub;
//
//    @Bean
//    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
//            MessageListenerAdapter listenerAdapter) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(listenerAdapter, new PatternTopic("KIT"));
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(RedisSub receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }
//
//    @Bean
//    RedisSub receiver(CountDownLatch latch) {
//        return new RedisSub(latch);
//    }
//
//    @Bean
//    CountDownLatch latch() {
//        return new CountDownLatch(1);
//    }
//    
//    @Scheduled(fixedRate = 1000 * 3)
//    public void reportCurrentTime(){
//        redisPub.sendMessage("KIT", "hi");
//    }
//}
