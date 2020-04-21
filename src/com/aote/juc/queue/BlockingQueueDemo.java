package com.aote.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Queue案例
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        /*
        抛异常。
         */
        /*
        System.out.println(blockingQueue.add("aaa"));
        blockingQueue.add("bbb");
        blockingQueue.add("ccc");
        String a = blockingQueue.remove();
        System.out.println(a);
        blockingQueue.add("ddd");
         */

        /*
        特殊值，结果：
        true
        true
        true
        false
        aaa
        bbb
        ccc
        null
         */
        /*
        System.out.println(blockingQueue.offer("aaa"));
        System.out.println(blockingQueue.offer("bbb"));
        System.out.println(blockingQueue.offer("ccc"));
        System.out.println(blockingQueue.offer("ddd"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
         */

        /*

         */
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");

    }

}
