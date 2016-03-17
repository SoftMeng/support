/*
 * 文件名：EnumTest.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test;

import java.util.EnumSet;

import auto.core.test.java.EnumExample;

/**
 * 〈枚举的测试〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see EnumTest
 * @since 1.0
 */
public class EnumTest {

    public static void main(String[] args) {
        System.out.println("枚举1:"+EnumExample.getNameByCode(EnumExample.CHECKING.getCode()));
        System.out.println("枚举2:"+EnumExample.CHECKING.getCode());
        System.out.println("枚举2:"+EnumExample.CHECKING.getName());
        
        System.out.println("［循环枚举］：");
        for(EnumExample one : EnumSet.range(EnumExample.UNSUBMITTED, EnumExample.PASSED)) { 
            System.out.println(one.getName()); 
        }
    }

}
