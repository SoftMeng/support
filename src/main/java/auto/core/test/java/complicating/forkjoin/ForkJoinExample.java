/*
 * 文件名：ForkJoinExample.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2016年3月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.test.java.complicating.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 〈基于ForkJoin框架的一个并发计算的例子－－Fahd Shariff的博客中的例子〉
 * 〈分支合并〉
 * @author mengxy[孟祥元]
 * @version 2016年3月17日
 * @see ForkJoinExample
 * @since 1.0
 */
public class ForkJoinExample extends RecursiveTask<Integer> {

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 1L;
    private static final int SEQUENTIAL_THRESHOLD = 5;
    private final int[] data;
    private final int start;
    private final int end;

    public ForkJoinExample(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    public ForkJoinExample(int[] data) {
        this(data, 0, data.length);
    }

    @Override
    protected Integer compute() {
        final int length = end - start;
        if (length < SEQUENTIAL_THRESHOLD) {
            return computeDirectly();
        }
        final int split = length / 2;
        final ForkJoinExample left = new ForkJoinExample(data, start, start + split);
        left.fork();
        final ForkJoinExample right = new ForkJoinExample(data, start + split, end);
        return Math.max(right.compute(), left.join());
    }

    private Integer computeDirectly() {
        System.out.println(Thread.currentThread() + " computing: " + start + " to " + end);
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        final int[] data = new int[1000];
        final Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100);
        }
        // submit the task to the pool
        final ForkJoinPool pool = new ForkJoinPool(4);
        final ForkJoinExample finder = new ForkJoinExample(data);
        System.out.println(pool.invoke(finder));
    }
}