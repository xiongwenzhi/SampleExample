package com.leo.example.info;

import java.io.Serializable;

/**
 * Created by leo on 16/5/19.
 */
public class ImageInfo implements Serializable {
    private String imageUrl;
    private String title;

    public ImageInfo(String title,String imageUrl) {
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
