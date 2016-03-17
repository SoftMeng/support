/*
 * 文件名：ClassTest.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test;

import auto.core.util.ClassUtils;

/**
 * 〈反射的测试－－〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see ClassTest
 * @since 1.0
 */
public class ClassTest {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        ClassUtils.invokeMethod("auto.core.util.TestUtils", "sayHello", new Object[]{"nininini","wowowowo"});
    }

}
