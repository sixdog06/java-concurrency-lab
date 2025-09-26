package com.sixdog.art;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sixdog
 * @since 2024/7/31
 */
public class AQSLab {

    /**
     * 直接看注释
     */
    private AbstractQueuedSynchronizer synchronizer;

    /**
     * FairSync
     * NonfairSync
     */
    private ReentrantLock reentrantLock;

    public static void main(String[] args) {
    }

}
