package com.sixdog.art;

/**
 * synchronized使用方式
 * @author sixdog
 * @since 2024/7/29
 */
public class SynchronizedLab {
    private final Object customLock = new Object();
    private static final Object staticLock = new Object();

    // 使用实例方法的对象锁
    public synchronized void instanceMethod() {
        // synchronized(this);
        System.out.println("Instance method");
    }

    // 使用静态方法的类锁
    public static synchronized void staticMethod() {
        // synchronized(LockExample.class)
    }

    // 使用自定义对象锁
    public void customLockMethod() {
        synchronized (customLock) {
            System.out.println("Custom lock method");
        }
    }

    // 使用类对象锁
    public void classLockMethod() {
        synchronized (SynchronizedLab.class) {
            System.out.println("Class lock method");
        }
    }
}
