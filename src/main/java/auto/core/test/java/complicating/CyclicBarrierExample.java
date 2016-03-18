/*
 * 文件名：CyclicBarrierExample.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java.complicating;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 〈JAVA回环栏杆--与CountDownLatch比较，CyclicBarrier是可重用的〉
 * 〈通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 *  叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 *  我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。〉
 * @author mengxy[孟祥元]
 * @version 2016年3月18日
 * @see CyclicBarrierExample
 * @since 1.0
 */
public class CyclicBarrierExample {
    
    /**
     * public int await() throws InterruptedException, BrokenBarrierException { };
     * 比较常用，用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
     */
    /**
     * public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException { };
     * 让这些线程等待至一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务。
     */

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N+1);
        //启动了四个写入操作的线程
        for (int i = 0; i < N; i++){
            new Writer(barrier).start();
        }
        barrier.await();
        //等待所有线程写入完成后才会进行后续操作
        System.out.println("所有线程写入完毕，继续处理其他任务...");
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep((new Random().nextInt(5000))); //以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
