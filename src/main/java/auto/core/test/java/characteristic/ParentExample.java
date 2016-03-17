/*
 * 文件名：ParentExample.java
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
 * 〈被继承的父类〉
 * 〈通过继承可以使对对象的描述更加清晰，可以实现代码的复用，可以实现重写类中的变量或方法，可以实现在无源代码的情况下修改被继承的类。〉
 * @author mengxy[孟祥元]
 * @version 2016年3月15日
 * @see ParentExample
 * @since 1.0
 */
public class ParentExample {
    
    public ParentExample(){
        System.out.println("这个是ParentExample的构造方法");
    }
    
    private String a;
    String b = "ParentExample.b";
    protected String c = "ParentExample.c";
    public String d = "ParentExample.d";

    public void functionOne(){
        System.out.println("我是ParentExample的方法functionOne");
    }
    
    public void functionTwo(){
        System.out.println("我是ParentExample的方法functionTwo");
    }
    
}
