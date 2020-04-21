package com.aote.juc.jucUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 完成指定数量之后执行线程
 *  * CyclicBarrier * 的字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是， * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞， * 直到最后一个线程到达屏障时，屏障才会开门，所有 * 被屏障拦截的线程才会继续干活。 * 线程进入屏障通过CyclicBarrier的await()方法。
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {System.out.println("召唤神龙");});
        for (int i = 1; i <=7; i++) {
            // lambda表达式中需要用final参数
            final int temp = i;
            new Thread(() -> {
                try {
                    System.out.println(temp);
                    cyclicBarrier.await();
                } catch (InterruptedException e) { e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }

}
