/*
 * 文件名：EnumExample2.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java;

/**
 * 〈JAVA枚举的用法2〉
 * 〈可以在枚举属性后面添加()来调用指定参数的构造方法，添加{}来实现其对应的匿名内部类〉
 * @author mengxy[孟祥元]
 * @version 2016年3月18日
 * @see EnumExample2
 * @since 1.0
 */
public enum EnumExample2 {
//    1. 枚举中的属性必须放在最前面，一般使用大写字母表示

//    2. 枚举中可以和java类一样定义方法

//    3. 枚举中的构造方法必须是私有的
    
    RED(30) {
        public EnumExample2 nextLamp() {
            System.out.println("+RED");
            return GREEN;
        }
    },
    GREEN(20) {
        public EnumExample2 nextLamp() {
            System.out.println("+GREEN");
            return YELLOW;
        }
    },
    YELLOW(10) {
        public EnumExample2 nextLamp() {
            System.out.println("+YELLOW");
            return RED;
        }
    };
    public abstract EnumExample2 nextLamp();

    private int time;

    private EnumExample2(int time){
        this.time = time ;
        System.out.println(time);
    }

    public static void main(String[] args) {
        System.out.println(EnumExample2.GREEN.nextLamp());
    }
}
