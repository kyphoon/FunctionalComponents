package com.kyphoon.functionalcomponents.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kyphoon.functionalcomponents.R
import retrofit2.http.Url
import java.io.File

/**
 *
 * @ProjectName: FunctionalComponents
 * @Package: com.kyphoon.functionalcomponents.utils
 * @ClassName: GlideUtil
 * @Description: 图片处理工具类
 * @Author: kyphoon
 * @CreateDate: 2020-07-31 11:41
 * @UpdateUser:
 * @UpdateDate: 2020-07-31 11:41
 * @UpdateRemark:
 * @Version: 1.0
 */
class GlideUtil {

    companion object{
        fun loadImg(context: Context,uri: Uri,view: ImageView,
                    placeid : Int = R.drawable.ic_loading,
                    errorid : Int = R.drawable.bg_img_error,
                    fallbackid : Int = R.drawable.bg_img_error){
            Glide.with(context).load(uri)
                //DiskCacheStrategy.NONE 什么都不缓存
                //DiskCacheStrategy.SOURCE 只缓存最高解析图的image
                //DiskCacheStrategy.RESULT 缓存最后一次那个image,比如有可能你对image做了转化
                //DiskCacheStrategy.ALL image的所有版本都会缓存
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(placeid) //占位图片
                .error(errorid) //加载错误时
                .fallback(fallbackid)//空指针时
//                .fitCenter() //裁剪，尺寸不够时周围留白
                .centerCrop() //裁剪尺寸不够时周围不留白
                .override(100,100) //调整图片大小
                .thumbnail(0.1f)//设置略缩图
                .into(view)
        }

        fun loadImg(context: Context,url: Url,view: ImageView,
                    placeid : Int = R.drawable.ic_loading,
                    errorid : Int = R.drawable.bg_img_error,
                    fallbackid : Int = R.drawable.bg_img_error){
            Glide.with(context).load(url)
                //DiskCacheStrategy.NONE 什么都不缓存
                //DiskCacheStrategy.SOURCE 只缓存最高解析图的image
                //DiskCacheStrategy.RESULT 缓存最后一次那个image,比如有可能你对image做了转化
                //DiskCacheStrategy.ALL image的所有版本都会缓存
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(placeid) //占位图片
                .error(errorid) //加载错误时
                .fallback(fallbackid)//空指针时
//                .fitCenter() //裁剪，尺寸不够时周围留白
                .centerCrop() //裁剪尺寸不够时周围不留白
                .override(100,100) //调整图片大小
                .thumbnail(0.1f)//设置略缩图
                .into(view)
        }

        fun loadImg(context: Context,id: Int,view: ImageView,
                    placeid : Int = R.drawable.ic_loading,
                    errorid : Int = R.drawable.bg_img_error,
                    fallbackid : Int = R.drawable.bg_img_error){
            Glide.with(context).load(id)
                //DiskCacheStrategy.NONE 什么都不缓存
                //DiskCacheStrategy.SOURCE 只缓存最高解析图的image
                //DiskCacheStrategy.RESULT 缓存最后一次那个image,比如有可能你对image做了转化
                //DiskCacheStrategy.ALL image的所有版本都会缓存
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(placeid) //占位图片
                .error(errorid) //加载错误时
                .fallback(fallbackid)//空指针时
//                .fitCenter() //裁剪，尺寸不够时周围留白
                .centerCrop() //裁剪尺寸不够时周围不留白
//                .override(300,300) //调整图片大小
                .thumbnail(0.1f)//设置略缩图
                .into(view)
        }

        fun loadImg(context: Context,file: File,view: ImageView,
                    placeid : Int = R.drawable.ic_loading,
                    errorid : Int = R.drawable.bg_img_error,
                    fallbackid : Int = R.drawable.bg_img_error){
            Glide.with(context).load(file)
                //DiskCacheStrategy.NONE 什么都不缓存
                //DiskCacheStrategy.SOURCE 只缓存最高解析图的image
                //DiskCacheStrategy.RESULT 缓存最后一次那个image,比如有可能你对image做了转化
                //DiskCacheStrategy.ALL image的所有版本都会缓存
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(placeid) //占位图片
                .error(errorid) //加载错误时
                .fallback(fallbackid)//空指针时
//                .fitCenter() //裁剪，尺寸不够时周围留白
                .centerCrop() //裁剪尺寸不够时周围不留白
                .override(100,100) //调整图片大小
                .thumbnail(0.1f)//设置略缩图
                .into(view)
        }

        fun loadImg(context: Context,path: String,view: ImageView,
                    placeid : Int = R.drawable.ic_loading,
                    errorid : Int = R.drawable.bg_img_error,
                    fallbackid : Int = R.drawable.bg_img_error){
            Glide.with(context).load(path)
                //DiskCacheStrategy.NONE 什么都不缓存
                //DiskCacheStrategy.SOURCE 只缓存最高解析图的image
                //DiskCacheStrategy.RESULT 缓存最后一次那个image,比如有可能你对image做了转化
                //DiskCacheStrategy.ALL image的所有版本都会缓存
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(placeid) //占位图片
                .error(errorid) //加载错误时
                .fallback(fallbackid)//空指针时
//                .fitCenter() //裁剪，尺寸不够时周围留白
                .centerCrop() //裁剪尺寸不够时周围不留白
                .override(100,100) //调整图片大小
                .thumbnail(0.1f)//设置略缩图
                .into(view)
        }
    }

}