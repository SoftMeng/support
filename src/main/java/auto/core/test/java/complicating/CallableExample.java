/*
 * 文件名：CallableExample.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java.complicating;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 〈JAVA回调〉
 * 〈JAVA并发时，如果不需要返回结果，那么Runable就足够了，如果需要返回结果，那么可以使用Callable〉
 * @author mengxy[孟祥元]
 * @version 2016年3月18日
 * @see CallableExample
 * @since 1.0
 */
public class CallableExample {

    static class Sum implements Callable<Long> {
        private final long from;
        private final long to;
        
        Sum(long from, long to) {
            this.from = from;
            this.to = to;
        }
        //实现
        public Long call() {
            long acc = 0;
            for (long i = from; i <= to; i++) {
                acc = acc + i;
            }
            return acc;
        }                
    }
    
    public static void main(String[] args) throws Exception {
        //获取回调的结果的方法
        ExecutorService executor=Executors.newFixedThreadPool(2);
        System.out.println("===================================");
        {
            List<Future<Long>>results=executor.invokeAll(
                    Arrays.asList(new Sum(0,10),new Sum(100,1_000),new Sum(10_000,1_000_000)));
            for(Future<Long>result:results){
                System.out.println(result.get());
            }
        }
        System.out.println("===================================");
        {
            Callable<Long> sum = new Sum(1,10);
            FutureTask<Long> futureTask = new FutureTask<Long>(sum);
            executor.submit(futureTask);
            executor.shutdown();
            System.out.println(futureTask.get());
        }
        System.out.println("===================================");
        {
            Callable<Long> sum = new Sum(100,1_000);
            FutureTask<Long> futureTask = new FutureTask<Long>(sum);
            Thread thread = new Thread(futureTask);
            thread.start();
            System.out.println(futureTask.get());
        }

    }  
}
