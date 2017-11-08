package com.groupfive.www.travel;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by Administrator on 2017/11/8.
 */

@GlideModule
public class MyAppGlideModule extends AppGlideModule{



    /**
     * 设置内存缓存及磁盘缓存
     * @param context
     * @param builder
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        //TODO （1） 设置内存缓存
        MemorySizeCalculator mMemorySizeCalculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2)
                .build();
        builder.setMemoryCache(new LruResourceCache(mMemorySizeCalculator.getMemoryCacheSize()));


        //TODO (2) 设置外存缓存
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context));



    }

}
