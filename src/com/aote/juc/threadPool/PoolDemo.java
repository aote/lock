package com.aote.juc.threadPool;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class PoolDemo {

    public static void main(String[] args) {
        /*
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler

            使用ThreadPoolExecutor新建一个线程池
         */
        ExecutorService threadPool = new ThreadPoolExecutor(3,5,2L, TimeUnit.SECONDS
        ,new LinkedBlockingQueue<>(3),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());


        try {
            for (int i = 0; i < 12; i++) {
                threadPool.execute(() -> {
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "-办理业务");
                });
            }
        } finally {
            threadPool.shutdown();
        }

    }

}
