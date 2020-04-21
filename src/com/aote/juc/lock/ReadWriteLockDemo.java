package com.aote.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReadWriteLockDemo {


    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i <5 ; i++) {
            final int temp = i;
            new Thread(() ->{
                myCache.put(String.valueOf(myCache),temp);
            },String.valueOf(i)).start();
        }

        for (int i = 0; i <5 ; i++) {
            final int temp = i;
            new Thread(() ->{
                myCache.get(String.valueOf(temp));
            },String.valueOf(i)).start();
        }
    }

}

class MyCache{

    private volatile Map<String,Object> map = new HashMap<String,Object>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入成功");
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取成功");
        } finally {
//            readWriteLock.readLock().unlock();
        }

    }

}