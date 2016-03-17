/*
 * 文件名：GroupTest.java
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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import auto.core.util.GroupUtils;
import auto.core.util.GroupUtils.GroupBy;
import auto.core.util.dto.Student;

/**
 * 〈分组测试，以及遍历MAP〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see GroupTest
 * @since 1.0
 */
public class GroupTest {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();

        list.add(new Student(3, "b", 1, new Date(11110000)));
        list.add(new Student(1, "c", 3, new Date(44440000)));
        list.add(new Student(2, "a", 2, new Date(22210000)));
        list.add(new Student(4, "a", 11, new Date(33330000)));

        Map<String, List<Student>> map = GroupUtils.group(list, new GroupBy<String>() {
            public String groupby(Object obj) {
                Student student = (Student) obj;
                return student.getName();//以name作为分组依据
            }
        });
        System.out.println("分组后的结果为：");
        for (Entry<String, List<Student>> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

    }

}
