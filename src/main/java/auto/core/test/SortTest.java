/*
 * 文件名：SortTest.java
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

import auto.core.util.SortListUtils;
import auto.core.util.dto.Student;

/**
 * 〈排序测试〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see SortTest
 * @since 1.0
 */
public class SortTest {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();

        list.add(new Student(3, "b", 1, new Date(11110000)));
        list.add(new Student(1, "c", 3, new Date(44440000)));
        list.add(new Student(2, "a", 2, new Date(22210000)));
        list.add(new Student(4, "a", 11, new Date(33330000)));
        System.out.println("-------原来序列-------------------");
        for (Student stu : list) {
            System.out.println(stu.toString());
        }

        // 按age正序排序,注意结果排完后是1,2,3,11. 不是1,11,2,3(如果是String类型正序排序是这样)
        SortListUtils.sort(list, "age", null);
        System.out.println("---------测试Integer和正序,按age正序排序-----------------");
        for (Student stu : list) {
            System.out.println(stu.toString());
        }

        // 按id倒序
        SortListUtils.sort(list, "id", SortListUtils.DESC);
        System.out.println("--------测试int和倒序,按id倒序------------------");
        for (Student stu : list) {
            System.out.println(stu.toString());
        }

        // 先按name正序排序,再按id正序排序
        SortListUtils.sort(list, new String[] { "name", "id" }, new String[] {});
        System.out
                .println("---------测试多个排序字段,先按name正序,name相同时再按id正序-----------------");
        for (Student stu : list) {
            System.out.println(stu.toString());
        }

        // 先按name正序排序,再按id倒序排序
        SortListUtils.sort(list, new String[] { "name", "id" }, new String[] {
                SortListUtils.ASC, SortListUtils.DESC });
        System.out
                .println("---------测试多个排序字段,先按name正序,name相同时再按id倒序-----------------");
        for (Student stu : list) {
            System.out.println(stu.toString());
        }

        // 按birthday排序
        SortListUtils.sort(list, "birthday");
        System.out
                .println("---------测试实现了Comparable接口的对象排序,按birthday正序-----------------");
        for (Student stu : list) {
            System.out.println(stu.toString());
        }

        // sortByMethod
        SortListUtils.sortByMethod(list, "getId", null);
        System.out
                .println("---------测试sortByMethod,按getId方法正序-----------------");
        for (Student stu : list) {
            System.out.println(stu.toString());
        }
    }

}
