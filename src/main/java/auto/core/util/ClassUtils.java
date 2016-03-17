/*
 * 文件名：ClassHelper.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：反射的公共方法
 * 修改人：mengxy[孟祥元]
 * 修改时间：2015年11月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * 〈根据class的路径，以及方法名，反射的方法〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2015年12月16日
 * @see ClassUtils
 * @since 1.0
 */
public class ClassUtils {
    /**
     * Description: 反射公共方法<br>
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     * 
     * @param className
     * @param methodName
     * @param args
     * @return
     * @throws Exception 
     * @see 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object invokeMethod(String className, String methodName, Object[] args) throws Exception {
        Class c = Class.forName(className);
        Object o = c.newInstance();
        Class ownerClass = o.getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(o, args);
    }
    
    public static boolean isNum(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
