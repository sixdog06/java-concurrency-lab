package com.sixdog.art.a_Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的使用
 * @author sixdog
 * @since 2024/8/1
 */
public class LockLab {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(task.getCount());

        task.reentrantLockApiTest();
    }

     static class Task implements Runnable {
        private ReentrantLock lock = new ReentrantLock();
        private int count = 0;

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++){
                lock.lock(); // 第一次阻塞式获取锁
                lock.tryLock(); // 第二次非阻塞式获取锁
                try {
                    lock.tryLock(10, TimeUnit.SECONDS); // 第三次非阻塞等待式获取锁
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    count++; // 非原子性操作
                } finally {
                    lock.unlock(); // 第一次释放锁
                    lock.unlock(); // 第二次释放锁
                    lock.unlock();
                }
            }
        }

         public void reentrantLockApiTest() {
             lock.lock(); // 获取锁
             try {
                 //获取当前线程调用lock()方法的次数
                 System.out.println("线程：" + Thread.currentThread().getName() + "\t调用lock()次数：" + lock.getHoldCount());
                 // 判断当前锁是否为公平锁
                 System.out.println("当前锁资源类型是否为公平锁？" + lock.isFair());
                 // 获取等待获取当前锁资源的估计线程数
                 System.out.println("目前有：" + lock.getQueueLength() + "个线程正在等待获取锁资源！");
                 // 指定线程是否在等待获取当前锁资源
                 System.out.println("当前线程是否在等待获取当前锁资源？" + lock.hasQueuedThread(Thread.currentThread()));
                 // 判断当前锁资源是否有线程在等待获取
                 System.out.println("当前锁资源是否存在线程等待获取？" + lock.hasQueuedThreads());
                 // 判断当前线程是否持有当前锁资源
                 System.out.println("当前线程是否持有当前锁资源？" + lock.isHeldByCurrentThread());
                 // 判断当前锁资源是否被线程持有
                 System.out.println("当前锁资源是否被线程占用？" + lock.isLocked());
             } finally {
                 lock.unlock(); // 释放锁
             }
         }

         public int getCount() {
             return count;
         }
     }
}
