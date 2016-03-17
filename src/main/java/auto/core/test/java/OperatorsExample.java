/*
 * 文件名：OperatorsExample.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java;

import auto.core.util.dto.Student;

/**
 * 〈JAVA运算符的应用〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see OperatorsExample
 * @since 1.0
 */
public class OperatorsExample {

    public static void main(String args[]) {
        { //算术运算符
            int a = 10;
            int b = 20;
            int c = 25;
            int d = 25;
            System.out.println("a + b = " + (a + b));
            System.out.println("a - b = " + (a - b));
            System.out.println("a * b = " + (a * b));
            System.out.println("b / a = " + (b / a));
            System.out.println("b % a = " + (b % a));
            System.out.println("c % a = " + (c % a));
            System.out.println("a++   = " + (a++));
            System.out.println("a--   = " + (a--));
            // 查看  d++ 与 ++d 的不同
            System.out.println("d++   = " + (d++));
            System.out.println("++d   = " + (++d));
        }
        {
            //关系运算符
            int a = 10;
            int b = 20;
            System.out.println("a == b = " + (a == b));
            System.out.println("a != b = " + (a != b));
            System.out.println("a > b = " + (a > b));
            System.out.println("a < b = " + (a < b));
            System.out.println("b >= a = " + (b >= a));
            System.out.println("b <= a = " + (b <= a));
        }
        {
            //位运算符
            int a = 60; /* 60 = 0011 1100 */
            int b = 13; /* 13 = 0000 1101 */
            int c = 0;
            c = a & b; /* 12 = 0000 1100 */
            System.out.println("a & b = " + c);

            c = a | b; /* 61 = 0011 1101 */
            System.out.println("a | b = " + c);

            c = a ^ b; /* 49 = 0011 0001 */
            System.out.println("a ^ b = " + c);

            c = ~a; /*-61 = 1100 0011 */
            System.out.println("~a = " + c);

            c = a << 2; /* 240 = 1111 0000 */
            System.out.println("a << 2 = " + c);

            c = a >> 2; /* 15 = 1111 */
            System.out.println("a >> 2  = " + c);

            c = a >>> 2; /* 15 = 0000 1111 */
            System.out.println("a >>> 2 = " + c);
        }
        {
            //逻辑运算符
            boolean a = true;
            boolean b = false;
            System.out.println("a && b = " + (a && b));
            System.out.println("a || b = " + (a || b));
            System.out.println("!(a && b) = " + !(a && b));
        }
        {
            //赋值运算符
            int a = 10;
            int b = 20;
            int c = 0;
            c = a + b;
            System.out.println("c = a + b = " + c);
            c += a;
            System.out.println("c += a  = " + c);
            c -= a;
            System.out.println("c -= a = " + c);
            c *= a;
            System.out.println("c *= a = " + c);
            a = 10;
            c = 15;
            c /= a;
            System.out.println("c /= a = " + c);
            a = 10;
            c = 15;
            c %= a;
            System.out.println("c %= a  = " + c);
            c <<= 2;
            System.out.println("c <<= 2 = " + c);
            c >>= 2;
            System.out.println("c >>= 2 = " + c);
            c >>= 2;
            System.out.println("c >>= a = " + c);
            c &= a;
            System.out.println("c &= 2  = " + c);
            c ^= a;
            System.out.println("c ^= a   = " + c);
            c |= a;
            System.out.println("c |= a   = " + c);
        }
        {
            //条件运算符
            int a, b;
            a = 10;
            b = (a == 1) ? 20 : 30;
            System.out.println("Value of b is : " + b);
            b = (a == 10) ? 20 : 30;
            System.out.println("Value of b is : " + b);
        }
        {
            Student a = new Student();
            boolean result =  a instanceof Student;
            System.out.println("a 是 Student的一个实例吗？ "+result);
        }
        {
            System.out.println("运算符存在优先级关系");
//            类别  操作符 关联性
//            后缀  () [] . (点操作符)  左到右
//            一元  + + - ！〜    从右到左
//            乘性  * /％    左到右
//            加性  + - 左到右
//            移位  >> >>>  <<  左到右
//            关系  >> = << =   左到右
//            相等  ==  !=  左到右
//            按位与 ＆   左到右
//            按位异或    ^   左到右
//            按位或 |   左到右
//            逻辑与 &&  左到右
//            逻辑或 | | 左到右
//            条件  ？：  从右到左
//            赋值  = + = - = * = / =％= >> = << =＆= ^ = | = 从右到左
//            逗号  ，   左到右
        }
    }
}
