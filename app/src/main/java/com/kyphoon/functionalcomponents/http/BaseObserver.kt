package com.kyphoon.functionalcomponents.http

import com.kyphoon.functionalcomponents.base.BaseView
import io.reactivex.observers.DisposableObserver

/**
 *
 * @ProjectName: functionalcomponents
 * @Package: com.kyphoon.functionalcomponents.http
 * @ClassName: BaseObserver
 * @Description:http请求返回封装，作为弹窗显示，进度条显示的基础类
 * @Author: kyphoon
 * @CreateDate: 2020-07-12 16:12
 * @UpdateUser:
 * @UpdateDate: 2020-07-12 16:12
 * @UpdateRemark:
 * @Version: 1.0
 */
abstract class BaseObserver<T>(private val baseview : BaseView, private val show : Boolean = true) : DisposableObserver<T>() {

    override fun onStart() {
        if(show && baseview != null){
            baseview.showloaddialog()
        }
    }

    override fun onComplete() {
        if(show && baseview != null){
            baseview.hidloaddialog()
        }
    }

    override fun onNext(t: T) {
        if(show && baseview != null){
            baseview.hidloaddialog()
        }
        Onsuccess(t)
    }

    override fun onError(e: Throwable) {
        if(show && baseview != null){
            baseview.hidloaddialog()
            if(e.toString().indexOf("ConnectException") != -1) {
                baseview.onBaseError("网络已断开，请重新连接网络后重试！")
            }else if(e.toString().indexOf("SocketTimeOut") != -1){
                baseview.onBaseError("连接超时，请检查网络设置！")
            }else{
                baseview.onBaseError(e.toString())
            }
        }
        Onerror(e.toString())
    }

    //访问成功之后调用
    abstract fun Onsuccess(t : T)

    //用于处理系统异常
    abstract fun Onerror(msg : String)
}