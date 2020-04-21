package com.aote.juc.atomic;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 因为volatile不保证原子性，需要使用atomicInteger保证数据的原子性
 */
public class AtomicIntegerDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() ->{
                for (int j = 0; j <1000 ; j++) {
                    myData.addMyAtomic();
                    myData.number++;
                }
            },"").start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(myData.atomicInteger);
        System.out.println(myData.number);
    }

}

class MyData {
    AtomicInteger atomicInteger = new AtomicInteger();
    volatile int number = 0;


    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}

