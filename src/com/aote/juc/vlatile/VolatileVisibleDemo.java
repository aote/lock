package com.aote.juc.vlatile;

import java.util.concurrent.TimeUnit;

/**
 * Volatile测试
 */
public class VolatileVisibleDemo {

    public static void main(String[] args) {
        VolatileVisibleDemo volatileDemo = new VolatileVisibleDemo();
        volatileDemo.visible();
    }

    /**
     * 可见性演示
     */
    public void visible(){
        MyData myData = new MyData();
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.changeNum();
            System.out.println("修改完成，number="+myData.number);
        },"a").start();
        while (myData.number == 0){

        }
        System.out.println("main线程结束");
    }

}

class MyData {
    volatile int number = 0;

    public void changeNum(){
        number = 60;
    }
}

