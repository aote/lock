package com.aote.juc.jucUtil;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 * 等待线程执行完成之后才会完成主线程
 *
 *  * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 *  * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 *  * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6; i++) {
            new Thread(() ->{
                System.out.println("第"+Thread.currentThread().getName()+"位同学离开教室");
                // 需要在这里计数
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        // 等待线程执行完成继续执行
        countDownLatch.await();
        System.out.println("班长走人关闭教室");

    }

    public static void closeDoor(){
    }

}