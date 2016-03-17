/*
 * 文件名：TestAbstractExample.java
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
 * 〈测试下抽象类的一些性质〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月16日
 * @see TestAbstractExample
 * @since 1.0
 */
public class TestAbstractExample extends AbstractExample{

    @Override
    public void functionOne() {
        System.out.println("子类TestAbstractExample实现了抽象父类AbstractExample的方法functionOne");
    }
    
    public static void main(String[] args) {
        {
            //这是一种多态
            AbstractExample example = new TestAbstractExample();
            example.functionOne();
            example.functionTwo();
            System.out.println("example是TestAbstractExample的实例吗？"+(example instanceof TestAbstractExample));
            System.out.println("example是AbstractExample的实例吗？"+(example instanceof AbstractExample));
        }
        {
            //抽象类无法直接实例化
            //AbstractExample example = new AbstractExample();
        }
        System.out.println("================================================");
        {
            TestAbstractExample example = new TestAbstractExample();
            example.functionOne();
            example.functionTwo();
            System.out.println("example是TestAbstractExample的实例吗？"+(example instanceof TestAbstractExample));
            System.out.println("example是AbstractExample的实例吗？"+(example instanceof AbstractExample));
        }
    }



}
