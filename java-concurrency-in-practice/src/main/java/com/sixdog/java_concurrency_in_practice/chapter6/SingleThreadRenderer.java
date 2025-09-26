package com.sixdog.java_concurrency_in_practice.chapter6;

import com.sixdog.java_concurrency_in_practice.chapter6.common.ImageData;
import com.sixdog.java_concurrency_in_practice.chapter6.common.ImageInfo;

import java.util.ArrayList;
import java.util.List;

import static com.sixdog.java_concurrency_in_practice.chapter6.common.ImageData.renderImage;
import static com.sixdog.java_concurrency_in_practice.chapter6.common.ImageInfo.renderText;
import static com.sixdog.java_concurrency_in_practice.chapter6.common.ImageInfo.scanForImageInfo;

/**
 * 串行渲染器
 * @author sixdog
 * @since 2022/3/9
 */
public class SingleThreadRenderer {
    
    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for (ImageInfo imageInfo: scanForImageInfo(source)) {
            imageData.add(imageInfo.downloadImage());
            for (ImageData data : imageData) {
                renderImage(data);
            }
        }
    }
}
