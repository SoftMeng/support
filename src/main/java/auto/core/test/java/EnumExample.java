/*
 * 文件名：EnumExample.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈Java枚举举例〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see EnumExample
 * @since 1.0
 */
public enum EnumExample {
    
    //审核状态
    /*** 未提交 */
    UNSUBMITTED("1", "未提交"),
    /*** 待审核 */
    CHECKING("2", "待审核"),
    /*** 审核通过 */
    PASSED("3", "审核通过"),
    /*** 审核未通过 */
    REJECTED("4", "审核未通过");
    
    private String code;
    private String name;

    private EnumExample(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    /**
     * @param code 编码
     * @return 名称
     */
    public static String getNameByCode(String code) {
        Map<String, String> m = new HashMap<String, String>() {
            private static final long serialVersionUID = 1L;
            {
                put(UNSUBMITTED.code, UNSUBMITTED.name);
                put(CHECKING.code, CHECKING.name);
                put(PASSED.code, PASSED.name);
                put(REJECTED.code, REJECTED.name);
            }
        };
        return m.get(code);
    }

}
