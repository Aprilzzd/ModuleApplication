package com.example.mytest;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {

    public static void main(String[] args) {
        MyRunnable runnable=new MyRunnable();
        Thread thread1=new Thread(runnable);
        Thread thread2=new Thread(runnable);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(runnable.integer);
    }

    static class MyRunnable implements Runnable{

        AtomicInteger integer=new AtomicInteger();

        @Override
        public void run() {
            for (int i=0;i<100;i++){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integer.incrementAndGet();
            }

        }
    }
}
