package com.sixdog.art;

/**
 * 利用volatile写线程安全的单例. 实际就是信号量
 * @author sixdog
 * @since 2024/7/28
 */
public class SafeSingleton {

    private volatile static SafeSingleton singleton;

    private SafeSingleton() {

    }

    public static SafeSingleton getInstance() {
        // 没实例化才需要加锁实例化, 避免重复加锁操作
        if (singleton == null) {
            synchronized (SafeSingleton.class) {
                if (singleton == null) {
                    singleton = new SafeSingleton();
                }
            }
        }
        return singleton;
    }
}
