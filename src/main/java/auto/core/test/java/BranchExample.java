/*
 * 文件名：BranchExample.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java;

/**
 * 〈JAVA的分支结构〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see BranchExample
 * @since 1.0
 */
public class BranchExample {

    public static void main(String[] args) {
        {
            //if..else..分支
            int x = 10;

            if (x < 20) {
                System.out.print("这是 if 语句");
            }
        }
        {
            //switch分支
            char grade = 'C';

            switch (grade) {
            case 'A':
                System.out.println("Excellent!");
                break;
            case 'B':
            case 'C':
                System.out.println("Well done");
                break;
            case 'D':
                System.out.println("You passed");
            case 'F':
                System.out.println("Better try again");
                break;
            default:
                System.out.println("Invalid grade");
            }
            System.out.println("Your grade is " + grade);
        }
    }

}
