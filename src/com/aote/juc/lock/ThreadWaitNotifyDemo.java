package com.aote.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock和Condition处理多线程抢占资源的问题
 */
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() ->{
            try {
                for(int i=0;i<=10;i++){
                    airConditioner.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(() ->{
            try {
                for(int i=0;i<=10;i++){
                    airConditioner.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }



}

class AirConditioner{

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {

        // lock版本
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println("A:"+number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {

        // lock版本
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println("B:"+number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}