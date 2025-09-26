package com.sixdog.art.g_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolLab {

    private static ExecutorService fixedThreadPool;

    public static void main(String[] args) {
        fixedThreadPool = Executors.newFixedThreadPool(5);
        
        
    }
}
