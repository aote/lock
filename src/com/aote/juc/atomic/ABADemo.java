package com.aote.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 使用带时间戳的原子引用解决ABA问题
 */
public class ABADemo {

    public static void main(String[] args) {
        // 模拟处理ABA问题
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
        new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,stamp,stamp+1);
            stamp = stamp+1;
            System.out.println("t1第一次更新版本号："+stamp);
            atomicStampedReference.compareAndSet(101,100,stamp,stamp+1);
            stamp = stamp+1;
            System.out.println("t1第二次更新版本号："+stamp);
            System.out.println("t1="+atomicStampedReference.getReference());
        },"t1").start();

        new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            try {
                // t2线程先sleep一秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag = atomicStampedReference.compareAndSet(100,2020,stamp,stamp+1);
            if(flag){
                stamp = stamp+1;
                System.out.println("t2版本号："+stamp);
                System.out.println("t2=" + atomicStampedReference.getReference());
            } else {
                System.out.println("t2版本号："+stamp);
                System.out.println("atomicStampedReference版本号已被更改");
            }
        },"t2").start();
    }

    /**
     * 模拟ABA问题
     */
    public static void simulateABA(){
        AtomicInteger atomicInteger = new AtomicInteger(100);
        new Thread(() ->{
            // 这里t1线程先将atomicInteger比对并设置101，之后再设置回100
            atomicInteger.compareAndSet(100,101);
            atomicInteger.compareAndSet(101,100);
            System.out.println("t1="+atomicInteger);
        },"t1").start();

        new Thread(() ->{
            // t2线程比对并设置，如果atomicInteger是100，将值设置为2020
            try {
                // t2线程先sleep一秒
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(100,2020);
            System.out.println("t2="+atomicInteger);
        },"t2").start();
        while (Thread.activeCount()>2){
            Thread.yield();
        }
    }

}
