/*
 * 文件名：ModifiersExample.java
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
 * 〈JAVA修饰符〉
 * 〈访问修饰符，非访问修饰符〉
 * @author mengxy[孟祥元]
 * @version 2016年3月14日
 * @see ModifiersExample
 * @since 1.0
 */
public class ModifiersExample {

    private String flag1 = "私有的，以private修饰符指定，在同一类内可见。";
    final String flag2 = "默认的，也称为default，在同一包内可见，不使用任何修饰符。";
    protected String flag3 = "受保护的，以protected修饰符指定，对同一包内的类和所有子类可见。";
    public String flag4 = "共有的，以public修饰符指定，对所有类可见。";
    //    父类中声明为public的方法在子类中也必须为public。
    //    父类中声明为protected的方法在子类中要么声明为protected，要么声明为public。不能声明为private。
    //    父类中声明为private的方法，不能够被继承。
    static String flag5 = "static关键字用来声明独立于对象的静态变量，无论一个类实例化多少对象，它的静态变量只有一份拷贝。 静态变量也被成为类变量。局部变量能被声明为static变量。";
    static void sayStatic(){
        System.out.println("Static关键字用来声明独立于对象的静态方法。静态方法不能使用类的非静态变量。静态方法从参数列表得到数据，然后计算这些数据。");
    }
    final String flag6 = "Final变量能被显式地初始化并且只能初始化一次。";
    //被声明为final的对象的引用不能指向不同的对象。但是final对象里的数据可以被改变。也就是说final对象的引用不能改变，但是里面的值可以改变。
    static final String flag7 = "Final修饰符通常和static修饰符一起使用来创建类常量。";
    
    final void sayFinal(){
        System.out.println("类中的Final方法可以被子类继承，但是不能被子类修改。");
        System.out.println("声明final方法的主要目的是防止该方法的内容被修改。");
        System.out.println("Final类不能被继承，没有类能够继承final类的任何特性。");
    }
    //抽象类和抽象方法：抽象方法是一种没有任何实现的方法，该方法的的具体实现由子类提供。抽象方法不能被声明成final和strict。
    //任何继承抽象类的子类必须实现父类的所有抽象方法，除非该子类也是抽象类。
    //如果一个类包含若干个抽象方法，那么该类必须声明为抽象类。抽象类可以不包含抽象方法。
    //抽象方法的声明以分号结尾，例如：public abstract sample();
    public abstract class SuperClass{
        abstract void m(); //抽象方法
    }
    public synchronized void saySynchronized(){
        System.out.println("Synchronized关键字声明的方法同一时间只能被一个线程访问。Synchronized修饰符可以应用于四个访问修饰符。");
    } 
    //该修饰符transient包含在定义变量的语句中，用来预处理类和变量的数据类型。
    public transient String limit = "序列化的对象包含被transient修饰的实例变量时，java虚拟机(JVM)跳过该特定的变量。";   // will not persist
    
    //Volatile修饰的成员变量在每次被线程访问时，都强迫从共享内存中重读该成员变量的值。而且，当成员变量发生变化时，强迫线程将变化值回写到共享内存。这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。一个volatile对象引用可能是null。
    private volatile boolean active;
    //一般地，在一个线程中调用run()方法，在另一个线程中调用stop()方法。如果line 1中的active位于缓冲区的值被使用，那么当把line 2中的active设置成false时，循环也不会停止。
    public static void main(String[] args) {
        System.out.println("修饰符的运用");
    }

}
