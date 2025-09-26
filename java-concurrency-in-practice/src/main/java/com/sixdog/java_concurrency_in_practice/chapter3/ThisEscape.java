package com.sixdog.java_concurrency_in_practice.chapter3;

import com.sixdog.java_concurrency_in_practice.chapter3.common.Event;
import com.sixdog.java_concurrency_in_practice.chapter3.common.EventListener;
import com.sixdog.java_concurrency_in_practice.chapter3.common.EventSource;

/**
 * this溢出
 * @author sixdog
 * @since 2022/2/9
 */
public class ThisEscape {

    /**
     * 构造函数中, 包含对this的隐式引用, 所以当ThisEscape构造器发布EventListener时, this也会被发布.
     */
    public ThisEscape(EventSource source) {
        source.registerListener(
                new EventListener() {
                    @Override
                    public void onEvent(Event e) {
                        // 如果EventListener被发布, this溢出了, 但是ThisEscape并没有构造完成
                        System.out.println(this);
                    }
                }
        );
        System.out.println("do other thing");
    }

    public static void main(String[] args) {
        EventSource source = new EventSource();
        new ThisEscape(source);
        source.eventListener.onEvent(new Event());
    }
}
