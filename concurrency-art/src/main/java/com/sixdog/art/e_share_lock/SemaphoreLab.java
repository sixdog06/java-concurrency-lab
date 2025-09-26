package com.sixdog.art.e_share_lock;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author sixdog
 * @since 2024/8/19
 */
public class SemaphoreLab {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                4 * 2, 40,
                30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1024 * 10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        // 设置信号量同一时刻最大线程数为3
        final Semaphore semaphore = new Semaphore(3);
        // 模拟100个对账请求
        for (int index = 0; index < 100; index++) {
            final int serial = index;
            threadPool.execute(() -> {
                try {
                    // 使用acquire()获取许可
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "线程成功获取许可！请求序号: " + serial);
                    // 模拟数据库IO
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 临界资源访问结束后释放许可
                    semaphore.release();
                }
            });
        }
        // 关闭线程池资源
        threadPool.shutdown();
    }
}
