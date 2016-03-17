/*
 * 文件名：HttpTest.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test;

import auto.core.util.HttpUtils;

/**
 * 〈HTTP请求测试〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see HttpTest
 * @since 1.0
 */
public class HttpTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String url = "http://www.oschina.net/";
        System.out.println(HttpUtils.sendGet(url));
    }

}
