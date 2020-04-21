package com.aote.juc.lock;

/**
 * 多线程8锁
 * 1.标准访问，先打印短信还是邮件 (短信)
 * 2.短信暂停4秒，先打印短信还是邮件 (短信)
 * 3.新增一个普通hello方法，先打短信件还是hello (Hello)
 * 4.两部手机，先打印短信还是邮件 (邮件)
 * 5.两个静态同步方法，同一部手机，先打印短信还是邮件 (短信)
 * 6.两个静态同步方法，2部手机，先打印邮件还是短信 (短信)
 * 7.1个普通同步方法，1个静态同步方法，1部手机，先打印短信还是邮件 (邮件)
 * 8.1个普通同步方法，1个静态同步方法，2部手机，先打印邮件还是短信 (邮件)
 *
 */
public class PhoneDemo {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() ->{
            phone.sendMessage();
        },"A").start();

        Thread.sleep(500);

        new Thread(() ->{
            phone2.sendEmail();
        },"B").start();

    }

}

class Phone {

    public static synchronized void sendMessage(){
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("=====发短信");
    }

    public static void sendEmail(){

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("=====发邮件");
    }

    public void hello(){
        System.out.println("=====Hello");
    }

}
