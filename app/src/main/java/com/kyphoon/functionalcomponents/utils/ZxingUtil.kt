package com.kyphoon.functionalcomponents.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kyphoon.functionalcomponents.R
import com.yanzhenjie.permission.AndPermission
import com.yzq.zxinglibrary.android.CaptureActivity
import com.yzq.zxinglibrary.bean.ZxingConfig
import com.yzq.zxinglibrary.encode.CodeCreator


/**
 *
 * @ProjectName: functionalcomponents
 * @Package: com.kyphoon.functionalcomponents.utils
 * @ClassName: ZxingUtil
 * @Description: 扫描工具类
 * @Author: kyphoon
 * @CreateDate: 2020-06-11 9:20
 * @UpdateUser:
 * @UpdateDate: 2020-06-11 9:20
 * @UpdateRemark:
 * @Version: 1.0
 */
class ZxingUtil {

    companion object{
        fun scan(activity: Activity,isVoice : Boolean = true,isVobord : Boolean = true,
                 isTXM : Boolean = true,isAll : Boolean = false){
            /*ZxingConfig是配置类
             *可以设置是否显示底部布局，闪光灯，相册，
             * 是否播放提示音  震动
             * 设置扫描框颜色等
             * 也可以不传这个参数
             * */

            /*ZxingConfig是配置类
             *可以设置是否显示底部布局，闪光灯，相册，
             * 是否播放提示音  震动
             * 设置扫描框颜色等
             * 也可以不传这个参数
             * */
            val config = ZxingConfig()
            config.isPlayBeep = isVoice //是否播放扫描声音 默认为true
            config.isShake = isVobord //是否震动  默认为true
            config.isDecodeBarCode = isTXM //是否扫描条形码 默认为true
            config.reactColor = R.color.colorAccent //设置扫描框四个角的颜色 默认为白色
            config.frameLineColor = R.color.colorAccent //设置扫描框边框颜色 默认无色
            config.scanLineColor = R.color.colorAccent //设置扫描线的颜色 默认白色
            config.isFullScreenScan = isAll //是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
            var intent : Intent = Intent(activity,CaptureActivity::class.java)
//            intent.putExtra(Constant.INTENT_ZXING_CONFIG, config)
            AndPermission.with(activity)
                .permission(Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                .onGranted {
                    activity.startActivityForResult(intent,1)
                }.start()
        }

        /*
            生成二维码
           * msg：字符串内容
           * w：图片的宽
           * h：图片的高
           * logo：不需要logo的话直接传null
        * */
        fun createQRcode(msg : String,context: Context,width : Int = 400,height : Int = 400,
                         bitmap: Bitmap ?= BitmapFactory.decodeResource(context.resources,R.mipmap.ic_launcher)) : Bitmap{
            return CodeCreator.createQRCode(msg,width,height,bitmap)
        }
    }

}