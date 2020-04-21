package com.aote.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * ABA问题产生：线程一执行需要10秒，线程二执行需要2秒，两个线程修改同一个资源类，
 * 当线程二修改过资源类之后，如果中间有猫腻，线程一是不知道的。
 *
 * 使用原子引用解决ABA问题
 *
 * 还可以新增一个版本号解决ABA问题
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User z3 = new User("z3",20);
        User l4 = new User("l4",30);
        AtomicReference<User> reference = new AtomicReference<>();
        reference.set(z3);

        System.out.println(reference.compareAndSet(z3,l4)+"\t"+reference.get().toString());
        System.out.println(reference.compareAndSet(z3,l4)+"\t"+reference.get().toString());
    }

}

class User{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}