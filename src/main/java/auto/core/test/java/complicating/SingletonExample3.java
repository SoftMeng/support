/*
 * 文件名：SingletonExample3.java
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
 * 〈通过枚举实现单例〉
 * 〈1.线程安全 2.不会因为序列化而产生新实例 3.防止反射攻击〉
 * @author mengxy[孟祥元]
 * @version 2016年3月18日
 * @see SingletonExample3
 * @since 1.0
 */
public enum SingletonExample3 {
    INSTANCE {
        @Override
        protected void read() {
            System.out.println("read");
        }

        @Override
        protected void write() {
            System.out.println("write");
        }

    };
    protected abstract void read();
    protected abstract void write();
    
    public static void main(String[] args) {
        SingletonExample3.INSTANCE.read();
    }
}
