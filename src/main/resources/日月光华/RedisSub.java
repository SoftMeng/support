/*
 * 文件名：RedisSub.java
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
import java.util.concurrent.CountDownLatch;

public class RedisSub{
    
    private CountDownLatch latch;
    public RedisSub(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(Serializable message) {
        System.out.println(message);
        latch.countDown();
    }
}
