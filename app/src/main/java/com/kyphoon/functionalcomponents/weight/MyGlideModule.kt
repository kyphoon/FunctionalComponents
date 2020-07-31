package com.kyphoon.functionalcomponents.weight

import android.content.Context
import android.os.Environment
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.module.GlideModule

/**
 *
 * @ProjectName: FunctionalComponents
 * @Package: com.kyphoon.functionalcomponents.weight
 * @ClassName: MyGlideModule
 * @Description: 设置glide磁盘缓存地址和大小
 * @Author: kyphoon
 * @CreateDate: 2020-07-31 15:40
 * @UpdateUser:
 * @UpdateDate: 2020-07-31 15:40
 * @UpdateRemark:
 * @Version: 1.0
 */

/**
 * setMemoryCache()
 * 用于配置Glide的内存缓存策略，默认配置是LruResourceCache。
 * <p>
 * setBitmapPool()
 * 用于配置Glide的Bitmap缓存池，默认配置是LruBitmapPool。
 * <p>
 * setDiskCache()
 * 用于配置Glide的硬盘缓存策略，默认配置是InternalCacheDiskCacheFactory。
 * <p>
 * setDiskCacheService()
 * 用于配置Glide读取缓存中图片的异步执行器，默认配置是FifoPriorityThreadPoolExecutor，
 * 也就是先入先出原则。
 * <p>
 * setResizeService()
 * 用于配置Glide读取非缓存中图片的异步执行器，默认配置也是FifoPriorityThreadPoolExecutor。
 * <p>
 * setDecodeFormat()
 * 用于配置Glide加载图片的解码模式，默认配置是RGB_565。
 */

class MyGlideModule(var size : Long = 500 * 1024 * 1024,
                    var path : String = Environment.getExternalStorageDirectory().getPath().toString()+"/glide") : GlideModule {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
            builder.setDiskCache(DiskLruCacheFactory(path, size))
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {

    }
}