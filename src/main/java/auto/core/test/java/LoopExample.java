/*
 * 文件名：LoopExample.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java;

import java.util.Collection;
import java.util.Iterator;

/**
 * 〈JAVA循环的一般写法〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see LoopExample
 * @since 1.0
 */
public class LoopExample {

    public static void main(String[] args) {
        {
            System.out.println("=====================while循环==========================");
            int x = 10;
            while (x < 15) {
                System.out.print("value of x : " + x);
                x++;
                System.out.print("\n");
            }
        }
        {
            System.out.println("=====================do..while循环==========================");
            int x = 10;
            do {
                System.out.print("value of x : " + x);
                x++;
                System.out.print("\n");
            } while (x < 20);
        }
        {
            System.out.println("=====================for循环==========================");
            for (int x = 10; x < 20; x = x + 1) {
                System.out.print("value of x : " + x);
                System.out.print("\n");
            }
        }
        {
            System.out.println("=====================增强for循环：推荐==========================");
            int[] numbers = { 10, 20, 30, 40, 50 };
            for (int x : numbers) {
                System.out.print(x);
                System.out.print(",");
            }
            System.out.print("\n");
            String[] names = { "James", "Larry", "Tom", "Lacy" };
            for (String name : names) {
                System.out.print(name);
                System.out.print(",");
            }
        }
        {
            System.out.println("=====================Arrays.asList并使用Iterator循环：旧式学院风格==========================");
            String[] strings = { "A", "B", "C", "D" };
            Collection stringList = java.util.Arrays.asList(strings);
            /* 开始遍历 */
            for (Iterator itr = stringList.iterator(); itr.hasNext();) {
                Object str = itr.next();
                System.out.println(str);
            }
        }
    }

}
