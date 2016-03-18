/*
 * 文件名：RunnableExample.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java.complicating;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈JAVA线程〉
 * 〈JAVA并发时，可以使用Runnable来分线程执行不需要返回结果的任务〉
 * @author mengxy[孟祥元]
 * @version 2016年3月18日
 * @see RunnableExample
 * @since 1.0
 */
public class RunnableExample {

    public static class Task implements Runnable{

        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName()+":这个一个任务！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public static void main(String[] args) {
        ExecutorService executor=Executors.newFixedThreadPool(2);
        for(@SuppressWarnings("unused") int i:(new int[10])){
            executor.submit(new Task());
        }
        System.out.println("=================================");
    }

}
