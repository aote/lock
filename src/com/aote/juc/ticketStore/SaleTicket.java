package com.aote.juc.ticketStore;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{

    private int number = 1000;
    private Lock lock = new ReentrantLock();

    public void saleTicket(){

        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName()+"卖出第"+(number--)+"张票，还剩" +
                        +number);
            }
        } finally {
            lock.unlock();
        }

    }

}

public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() ->{for (int i = 0; i < 1000; i++) {ticket.saleTicket();}},"A").start();
        new Thread(() ->{for (int i = 0; i < 1000; i++) {ticket.saleTicket();}},"B").start();
        new Thread(() ->{for (int i = 0; i < 1000; i++) {ticket.saleTicket();}},"C").start();

    }
}
