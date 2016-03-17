/*
 * 文件名：ChildOneExample.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java.characteristic;

/**
 * 〈子类继承父类〉
 * 〈它可以使用父类的非私有方法和非私有属性〉
 * @author mengxy[孟祥元]
 * @version 2016年3月15日
 * @see ChildOneExample
 * @since 1.0
 */
public class ChildOneExample extends ParentExample{
    
    private String c = "ChildOneExample.c";

    public ChildOneExample() {
        System.out.println("这个是ChildOneExample的构造方法");
    }
    
    public void functionTwo(){
        System.out.println("这个是ChildOneExample的functionTwo方法");
    }
    public void functionThree(){
        System.out.println("这个是ChildOneExample的functionThree方法");
    }
    public void functionOne(String one){
        System.out.println("这个是ChildOneExample的functionOne方法，但是有参数");
    }

    public static void main(String[] args) {
        {   /****继承****/
            //实例化时会先调用父类的构造方法，然后再调用子类的构造方法
            ChildOneExample childOneExample = new ChildOneExample();
            childOneExample.functionOne();//子类继承父类的方法
            childOneExample.functionTwo();//子类的方法会覆盖父类的方法
            childOneExample.functionThree();//子类独有的方法
            childOneExample.functionOne("haha");//子类独有的方法
            System.out.println(childOneExample.b);//调用父类的成员变量
            System.out.println(childOneExample.c);//子类定义同名成员变量会实现对父类成员变量的隐藏
            System.out.println(childOneExample.d);
        }
        System.out.println("====================================================");
        {
            ParentExample childOneExample = new ChildOneExample();
            childOneExample.functionOne();//子类继承父类的方法
            childOneExample.functionTwo();//子类的方法会覆盖父类的方法，重写了父类的方法
//            childOneExample.functionThree();//子类独有的方法,不能被父类使用
//            childOneExample.functionOne("haha");//子类独有的方法,不能被父类使用
            System.out.println(childOneExample.b);//调用父类的成员变量
            System.out.println(childOneExample.c);//子类定义同名成员变量会实现对父类成员变量的隐藏,但是此时无效
            System.out.println(childOneExample.d);
            System.out.println(childOneExample.toString());
        }
        System.out.println("====================================================");
        {
            //Java多态
            //条件1:继承：在多态中必须存在有继承关系的子类和父类。
            //条件2:重写：子类对父类中某些方法进行重新定义，在调用这些方法时就会调用子类的方法。
            //条件3:向上转型：在多态中需要将子类的引用赋给父类对象，只有这样该引用才能够具备技能调用父类的方法和子类的方法。
            //Java多态的原则：当超类对象引用变量引用子类对象时，被引用对象的类型而不是引用变量的类型决定了调用谁的成员方法，但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法。
            //实现多态的方式：继承和接口
        }
    }

}
