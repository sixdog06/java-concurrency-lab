package com.sixdog.java_concurrency_in_practice.chapter5;


import com.sixdog.java_concurrency_in_practice.chapter5.common.Computable;

import java.util.Map;
import java.util.concurrent.*;

import static com.sixdog.java_concurrency_in_practice.chapter5.Preloader.launderThrowable;


/**
 *
 * @author sixdog
 * @since 2022/3/1
 */
public class Memoizer3<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        // 这里的if非原子, 所以两个线程可能依然会算相同的值(概率比Memoizer2小), 因为这个check-then-act复合操作不原子, 两
        // 个线程很有可能拿到相同的值, 重复计算
        if (f == null) {
            Callable<V> eval = new Callable<V>() {

                @Override
                public V call() throws InterruptedException {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run(); // call to c.compute happens here
        }
        try {
            // 计算结束了, 直接返回. 若未计算结束, 则等这个线程计算结果
            return f.get();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }
}
