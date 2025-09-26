package com.sixdog.art.c_atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sixdog
 * @since 2024/8/13
 */
public class AtomicLab {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++){
            threads[i] = new Thread(new MyThread());
        }
        for (int i = 0; i < 5; i++){
            threads[i].start();
        }
        for (int i = 0; i < 5; i++){
            threads[i].join();
        }

        System.out.println(atomicInteger.get());
    }

    public static class MyThread implements Runnable {

        @Override
        public void run(){
            for (int i = 0; i < 10; i++)
                atomicInteger.incrementAndGet();
        }
    }
}
