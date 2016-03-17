/*
 * 文件名：AbstractExample.java
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
 * 〈JAVA抽象类〉
 * 〈功能详细描述〉
 * @author mengxy[孟祥元]
 * @version 2016年3月16日
 * @see AbstractExample
 * @since 1.0
 */
public abstract class AbstractExample {
//    无法定义类变量
//    public static final String someStaticArgs;
    
    /**
     * 描述: 抽象类的抽象方法，此方法会被子类重写<br>
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     * ［作者：孟祥元］ 
     * @see 
     *  *为者常成，行者常至*
     */
    public abstract void functionOne();
    /**
     * 描述: 抽象类AbstractExample定义的非抽象方法functionTwo<br>
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     * ［作者：孟祥元］ 
     * @see 
     *  *为者常成，行者常至*
     */
    public void functionTwo(){
        System.out.println("这个是抽象类AbstractExample定义的非抽象方法functionTwo");
    }
}
