package com.aote.juc.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * java多线程第三种启动方式
 * 使用callable启动线程
 */
public class MyThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyCallable());
        new Thread(futureTask,"A").start();
        String resp = (String)futureTask.get();
        System.out.println(resp);
    }
}

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Person person = new Person();
        person.takeList();
        return "success";
    }
}

class Person{

    public void takeList(){
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list.toString());
    }

}