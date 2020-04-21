package com.aote.juc.vlatile;

/**
 * 多线程中的单例模式会出问题
 * 使用DCL单例模式
 */
public class DclSingleton {

    private static volatile DclSingleton instance = null;

    private DclSingleton(){}

    public static DclSingleton getInstance(){
        if(instance == null){
            // 双端检索 double check lock
            synchronized (DclSingleton.class){
                if(instance == null) {
                    instance = new DclSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() ->{
                System.out.println(getInstance());
            },"").start();
        }
    }

}
