/*
 * 文件名：ReflectUtils.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2015年12月7日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 〈实现MAP和POJO的相互转换〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2015年12月7日
 * @see ReflectUtils
 * @since 1.0
 */
public final class ReflectUtils {

    /**
     * Description:将POJO转化为MAP <br>
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     * 
     * @param params
     * @param t 
     * @see 
     */
    public static <T extends Object> void flushParams(Map<String, Object> params, T t) {
        if (params == null || t == null)
            return;

        Class<?> clazz = t.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();

                for (int j = 0; j < fields.length; j++) { // 遍历所有属性
                    String name = fields[j].getName(); // 获取属性的名字
                    Object value = null;
                    String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method[] methods =  t.getClass().getMethods();
                    for(Method method : methods){
                        if(methodName.equals(method.getName())){
                            value = method.invoke(t);
                        }
                    }
                    if (value != null) {
                        params.put(name, value);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Description: 将MAP转化为POJO<br>
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     * 
     * @param t
     * @param params 
     * @see 
     */
    public static <T extends Object> void flushObject(T t, Map<String, Object> params) {
        if (params == null || t == null)
            return;

        Class<?> clazz = t.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();

                for (int i = 0; i < fields.length; i++) {
                    String name = fields[i].getName(); // 获取属性的名字

                    Object value = params.get(name);
                    if (value != null && !"".equals(value)) {
                        //注意下面这句，不设置true的话，不能修改private类型变量的值
                        fields[i].setAccessible(true);
                        fields[i].set(t, value);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
