/*
 * 文件名：TimerTaskExample.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java.complicating;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 〈JAVA调度器，@Schedule〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月18日
 * @see TimerTaskExample
 * @since 1.0
 */
public class TimerTaskExample {

    public static void main(String[] args) {
        Timer timer = new Timer();
        //这个方法是调度一个task，在delay（ms）后开始调度，每次调度完后，最少等待period（ms）后才开始调度。
        timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println("abc");
                }
        }, 2000 , 1000);
        //这个方法是调度一个task，经过delay(ms)后开始进行调度，仅仅调度一次。
        timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println("123");
                }
        }, 3000);
    }

}
