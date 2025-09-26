package com.sixdog.art.e_thread;

import java.util.concurrent.FutureTask;

/**
 * @author sixdog
 * @since 2024/10/9
 */
public class Task {

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }, "T1").start();

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            return Thread.currentThread().getName();
        });
        new Thread(futureTask, "T2").start();
        System.out.println(futureTask.get());
    }
}
