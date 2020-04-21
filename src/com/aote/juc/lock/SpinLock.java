package com.aote.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: Weicf
 * @date: 2020-04-09 23:04
 * @description: 自旋锁，反复的循环比较实现
 */
public class SpinLock {

    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        // 当前进来的这个线程
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t Come in");
        /**
         * 第一次线程进来是null，如果是null，就把这个线程放进去
         * atomicReference.compareAndSet(null,thread)
         */
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        // 如果线程存在，比较并交换为空，实现解锁
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoked myUnlock()");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLock spinLock = new SpinLock();
        new Thread(()->{
            try {
                spinLock.myLock();
                TimeUnit.SECONDS.sleep(50);
                spinLock.myUnlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        TimeUnit.MILLISECONDS.sleep(100);

        new Thread(()->{
            spinLock.myLock();
            spinLock.myUnlock();
        },"BB").start();

    }

}