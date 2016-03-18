/*
 * 文件名：SingletonExample.java
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
 * 〈线程安全的单例模式-- 双检锁〉
 * 〈这样的写法在很多平台和优化编译器上是错误的，只有加上volatile保证了执行顺序后，才能在JAVA5以后使用〉
 *  相比使用 static final 修饰符的饿汉式单例来说，可以设置参数。
 *  更推荐使用静态内部类的单例模式，比如SingletonExample2
 * @author mengxy[孟祥元]
 * @version 2016年3月18日
 * @see SingletonExample
 * @since 1.0
 */
public class SingletonExample {

    private volatile static SingletonHandler single;    //声明静态的单例对象的变量  
    private SingletonExample(){}    //私有构造方法   
      
    public static SingletonHandler getSingle(){    //外部通过此方法可以获取对象    
      if(single == null){     
          synchronized (SingletonExample.class) {   //保证了同一时间只能只能有一个对象访问此同步块        
              if(single == null){      
                  single = new SingletonHandler();          
          }     
        }  
      }    
      return single;   //返回创建好的对象   
    } 
}
