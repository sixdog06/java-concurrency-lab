package com.sixdog.java_concurrency_in_practice.chapter3;

import com.sixdog.java_concurrency_in_practice.chapter3.common.Event;
import com.sixdog.java_concurrency_in_practice.chapter3.common.EventListener;
import com.sixdog.java_concurrency_in_practice.chapter3.common.EventSource;

/**
 * Using a factory method to prevent the this reference from escaping during construction
 * 让构造器无法被外部直接调用
 * @author sixdog
 * @since 2022/2/9
 */
public class SafeListener {

    private final EventListener listener;

    private SafeListener() {
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                // 不允许这个时候的状态被外部访问
            }
        };

        System.out.println("do other thing");
    }

    public static SafeListener newInstance(EventSource source) {
        // SafeListener构造完成后, 再用registerListener去注册
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }
}
