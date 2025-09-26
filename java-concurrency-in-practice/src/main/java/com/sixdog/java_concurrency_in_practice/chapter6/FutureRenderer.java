package com.sixdog.java_concurrency_in_practice.chapter6;

import com.sixdog.java_concurrency_in_practice.chapter6.common.ImageData;
import com.sixdog.java_concurrency_in_practice.chapter6.common.ImageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.sixdog.java_concurrency_in_practice.chapter5.Preloader.launderThrowable;
import static com.sixdog.java_concurrency_in_practice.chapter6.common.ImageData.renderImage;
import static com.sixdog.java_concurrency_in_practice.chapter6.common.ImageInfo.renderText;
import static com.sixdog.java_concurrency_in_practice.chapter6.common.ImageInfo.scanForImageInfo;

/**
 * Future实现的异步图像渲染
 * @author sixdog
 * @since 2022/3/10
 */
public class FutureRenderer {
    
    private final ExecutorService executor = new ThreadPoolExecutor(
            5,
            10,
            100,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(5));
    
    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() {
                List<ImageData> result = new ArrayList<>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };
    
        Future<List<ImageData>> future = executor.submit(task);
        renderText(source);
        try {
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData) {
                renderImage(data);
            }
        } catch (InterruptedException e) {
            // Re-assert the thread’s interrupted status
            Thread.currentThread().interrupt();
            // We don’t need the result, so cancel the task too
            future.cancel(true);
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }
}
