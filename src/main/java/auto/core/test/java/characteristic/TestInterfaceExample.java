/*
 * 文件名：TestInterfaceExample.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java.characteristic;

/**
 * 〈测试下接口的一些性质〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月16日
 * @see TestInterfaceExample
 * @since 1.0
 */
public class TestInterfaceExample implements InterfaceExample {
    
    /**
     * 〈内部的接口类〉
     * 〈功能详细描述〉
     * @author mengxy[孟祥元]
     * @version 2016年3月16日
     * @see InnerInterfaceExample
     * @since 1.0
     */
    public interface InnerInterfaceExample {
        public String sayHello(String name);
    }

    public void functionOne() {
        System.out.println("TestInterfaceExample来实现接口InterfaceExample的方法functionOne");
    }

    public void functionTwo() {
        System.out.println("TestInterfaceExample来实现接口InterfaceExample的方法functionTwo");
    }

    public static void main(String[] args) {
        {
            //这是一种多态
            InterfaceExample example = new TestInterfaceExample();
            example.functionOne();
            example.functionTwo();
            System.out.println("example是InterfaceExample的一个实例吗？"+(example instanceof InterfaceExample));
            System.out.println("example是TestInterfaceExample的一个实例吗？"+(example instanceof TestInterfaceExample));
        }
        {
            //接口无法直接实例化，会报错
            //InterfaceExample example = new InterfaceExample();
        }
        {
            //不实例化，只是实现接口内部的方法
            System.out.println((new InnerInterfaceExample() {
                public String sayHello(String name) {
                    return "Hello, " + name;
                }
            }).sayHello("World"));
        }

    }



}
