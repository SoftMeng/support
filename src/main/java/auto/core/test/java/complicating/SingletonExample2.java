/*
 * 文件名：SingletonExample2.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java.complicating;

/**
 * 〈使用静态内部类的单例模式〉
 * 〈线程安全的，推荐〉
 * @author mengxy[孟祥元]
 * @version 2016年3月18日
 * @see SingletonExample2
 * @since 1.0
 */
public class SingletonExample2 {

    private static class SingletonHolder {
        private static final SingletonHandler INSTANCE = new SingletonHandler();
    }

    private SingletonExample2 (){}

    public static final SingletonHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
