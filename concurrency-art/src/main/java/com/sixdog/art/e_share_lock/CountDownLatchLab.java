package com.sixdog.art.e_share_lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author sixdog
 * @since 2024/8/19
 */
public class CountDownLatchLab {

    public static void main(String[] args) throws InterruptedException {
        // CountDownLatch countDownLatch = new CountDownLatch(1);
        // for (int i = 1; i <= 3; i++) {
        //     new Thread(() -> {
        //         try {
        //             System.out.println("线程：" + Thread.currentThread().getName() + "....阻塞等待！");
        //             countDownLatch.await();
        //             // 可以在此处调用需要并发测试的方法或接口
        //             System.out.println("线程：" + Thread.currentThread().getName() + "....开始执行！");
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }, "T" + i).start();
        // }
        // Thread.sleep(1000);
        // countDownLatch.countDown();

        // 独占模式实现, 阻塞到3个线程都进来开始执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                try {
                    System.out.println("线程：" + Thread.currentThread().getName() + "....阻塞等待！");
                    cyclicBarrier.await();
                    // 可以在此处调用需要并发测试的方法或接口
                    System.out.println("线程：" + Thread.currentThread().getName() + "....开始执行！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }, "T" + i).start();
        }
        Thread.sleep(1000);
    }
}
