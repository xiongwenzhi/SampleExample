package com.leolibrary.utils;

import android.graphics.Bitmap;
import android.util.LruCache;


/**
 * Created by leo on 16/8/17.
 * LruCache 图片缓存优化处理类
 */
public class LruCacheUtils extends LruCache<String, Bitmap> {
    private static int MAXMEMONRY = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private static LruCacheUtils cacheUtils;

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    private LruCacheUtils(int maxSize) {
        super(maxSize);
    }

    /**
     * 单例
     */
    public static LruCacheUtils getInstance() {
        if (cacheUtils == null) {
            cacheUtils = new LruCacheUtils(MAXMEMONRY / 5);
        }
        return cacheUtils;
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
    }

    /**
     * 清理缓存
     */
    public void clearCache() {
        if (cacheUtils.size() > 0) {
            cacheUtils.evictAll();
        }
    }


    /**
     * 添加缓存图片
     */
    public synchronized void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (cacheUtils.get(key) != null) {
            return;
        }
        if (!Strings.isEmpty(key) && bitmap != null) {
            cacheUtils.put(key, bitmap);
        }
    }


    /**
     * 获取缓存图片
     */
    public synchronized Bitmap getBitmapFromMemCache(String key) {
        if (Strings.isEmpty(key)) {
            return null;
        }
        Bitmap bm = cacheUtils.get(key);
        if (bm != null && !bm.isRecycled()) {
            return bm;
        }
        return null;
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public synchronized void removeImageCache(String key) {
        if (Strings.isEmpty(key)) {
            return;
        }
        Bitmap bm = cacheUtils.remove(key);
        if (bm != null && !bm.isRecycled()) {
            bm.recycle();
        }
    }

}
