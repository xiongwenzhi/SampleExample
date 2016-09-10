package com.leo.example.info;

import java.io.Serializable;

/**
 * Created by leo on 16/9/11.
 */

public class ImageSizeInfo implements Serializable {
    private int width;
    private int height;

    public ImageSizeInfo() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ImageSizeInfo{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
