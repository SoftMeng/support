/*
 * 文件名：PackageExample.java
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
 * 〈什么是封装呢？〉
 * 〈封装隐藏了类的内部实现机制，可以在不影响使用的情况下改变类的内部结构，同时也保护了数据。对外界而已它的内部细节是隐藏的，暴露给外界的只是它的访问方法。〉
 * @author mengxy[孟祥元]
 * @version 2016年3月15日
 * @see PackageExample
 * @since 1.0
 */
public class PackageExample {
    
    private String name;
    private String desc;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    /**
     * 这个类的成员变量的修饰符为private，是私有的，保证了成员变量数据。同时提供了外部调用的方法，setter和getter方法。
     * 这就是一种封装。
     */
    

}
