/*
 * 文件名：ReflectTest.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import auto.core.util.ReflectUtils;
import auto.core.util.dto.Student;

/**
 * 〈POJO和Map相互转换测试〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see ReflectTest
 * @since 1.0
 */
public class ReflectTest {
    
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();

        list.add(new Student(3, "b", 1, new Date(11110000)));
        list.add(new Student(1, "c", 3, new Date(44440000)));
        list.add(new Student(2, "a", 2, new Date(22210000)));
        list.add(new Student(4, "a", 11, new Date(33330000)));
        
        Map<String, Object> map = new HashMap<String,Object>();
        
        Student s = new Student(5, "c", 1, new Date(11110000));
        
        ReflectUtils.flushParams(map, s);
        System.out.println("POJO转换为MAP："+map);
        for (Entry<String, Object> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        Student t = new Student();
        ReflectUtils.flushObject(t , map);
        System.out.println("MAP转换为POJO："+t);
    }
}
