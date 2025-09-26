package com.sixdog.java_concurrency_in_practice.chapter5;

import java.util.concurrent.BlockingQueue;

/**
 * 恢复中断
 * @author sixdog
 * @since 2022/2/22
 */
public class TaskRunnable implements Runnable {

    BlockingQueue<String> queue;

    @Override
    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            // restore interrupted status
            Thread.currentThread().interrupt();
        }
    }

    private void processTask(String take) {
        // do something
    }
}
