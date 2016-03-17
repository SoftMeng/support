/*
 * 文件名：BasicJavaInfomation.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java;

/**
 * 〈Java相关介绍--类和对象〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see BasicJavaInfomation
 * @since 1.0
 */
public class BasicJavaInfomation {
    
    private String info = "这个是外部类的成员变量";


    public String getInfo() {
        return info;
    }


    public void setInfo(String info) {
        this.info = info;
    }
    
    /**
     * 〈JAVA对象，类似于前端JS中的存储器，Model〉
     * 〈这里是个内部类，而且是成员内部类。〉
     *  成员内部类，就是作为外部类的成员，可以直接使用外部类的所有成员和方法，即使是private的。
     *  同时外部类要访问内部类的所有成员变量/方法，则需要通过内部类的对象来获取。
     *  成员内部类不能含有static的变量和方法
     *  在成员内部类要引用外部类对象时，使用BasicJavaInfomation.this来表示外部类对象；
     * @author mengxy[孟祥元]
     * @version 2016年3月14日
     * @see Dog
     * @since 1.0
     */
    protected class Dog{
        
        /**
         * 类变量也声明在类中，方法体之外，但必须声明为static类型.final修饰，所以线程安全<br>
         */
        protected static final String leibianliang = "这是一个类变量";
        /**
         * 成员变量是定义在类中，方法体之外的变量。
         * 这种变量在创建对象的时候实例化。成员变量可以被类中方法、构造方法和特定类的语句块访问<br>
         */
        String breed = "这是一个成员变量";
        int age;
        String color;
        
        {
            //语法糖
            String name = "这是一个成员变量";
        }
        
        /**
         * 构造方法，类至少有一个构造方法
         * @param breed
         * @param age
         * @param color
         */
        public Dog(String breed, int age, String color) {
            super();
            this.breed = breed;
            this.age = age;
            this.color = color;
        }
        
        /**
         * 构造方法，类至少有一个构造方法
         * @param breed
         */
        public Dog(String breed) {
            super();
            this.breed = breed;
            System.out.println("使用构造方法实例化："+this.toString());
        }
       
        public Dog() {
            super();
        }

        /**
         * 描述: 这是一个类方法。一个类可以拥有多个方法。<br>
         * 1、…<br>
         * 2、…<br>
         * Implement: <br>
         * 1、…<br>
         * 2、…<br>
         * ［作者：孟祥元］ 
         * @see 
         *  *为者常成，行者常至*
         */
        void barking(){
            //局部变量：在方法、构造方法或者语句块中定义的变量被称为局部变量。
            //变量声明和初始化都是在方法中，方法结束后，变量就会自动销毁
            String jububianliang = "这是一个局部变量";
            System.out.println(jububianliang);
        }
        
        void hungry(){
            {
                //语法糖
                String name = "这是一个局部变量";
                System.out.println("语法糖内部的Name："+name);
            }
            String name = "我在外面";
            System.out.println("语法糖外部的Name:"+name);
        }
        
        void sleeping(){
            //局部内部类,定义在方法内
            class LittleDog{}
            if("1".equals("2")){
                //局部内部类，定义在作用域中
                class LittleDog2{}
            }
            //局部内部类也像别的类一样进行编译，但只是作用域不同而已，只在该方法或条件的作用域内才能使用，退出这些作用域后无法引用的。
        }
        
        void getBasicJavaInfomationInfo(){
            System.out.println("在成员内部类要引用外部类对象时，使用BasicJavaInfomation.this来表示外部类对象:"+BasicJavaInfomation.this.getInfo());
        }
     }
    
    /**
     * 〈嵌套型内部类〉
     * 〈 嵌套内部类，就是修饰为static的内部类。
     * 声明为static的内部类，不需要内部类对象和外部类对象之间的联系，就是说我们可以直接引用outer.inner，即不需要创建外部类，也不需要创建内部类。〉
     * 〈嵌套类和普通的内部类还有一个区别：普通内部类不能有static数据和static属性，也不能包含嵌套类，但嵌套类可以。
     * 而嵌套类不能声明为private，一般声明为public，方便调用。〉
     * @author mengxy[孟祥元]
     * @version 2016年3月14日
     * @see Cat
     * @since 1.0
     */
    public static class Cat{
        public static void doIt(){
            System.out.println("直接调用嵌套型内部类的方法");
        }
    }
    
    
    public static void main(String[] args) {
        //对象的实例化
        BasicJavaInfomation basicJavaInfomation = new BasicJavaInfomation();
        //成员内部类的实例化
        BasicJavaInfomation.Dog dog = basicJavaInfomation.new Dog();
        //内部类调用外部类的属性或者方法
        dog.getBasicJavaInfomationInfo();
        //调用嵌套型内部类的方法
        BasicJavaInfomation.Cat.doIt();
        //匿名内部类:...
        //内部类的继承:...
        //内部类和闭包的区别:...
    }

    
    

}
