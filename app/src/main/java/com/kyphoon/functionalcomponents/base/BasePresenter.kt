package com.kyphoon.functionalcomponents.base

import com.kyphoon.functionalcomponents.http.BaseObserver
import com.kyphoon.functionalcomponents.http.HttpServiceGenerator
import com.kyphoon.functionalcomponents.http.Okhttp3Factory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient

/**
 *
 * @ProjectName: functionalcomponents
 * @Package: com.kyphoon.functionalcomponents.base
 * @ClassName: BasePresenter
 * @Description: 控制基类，用于订阅事件的控制，使用时需要继承该类，并且通过getWebApi方法获取请求接口，并判断是否已经创建
 * @Author: kyphoon
 * @CreateDate: 2020-07-12 16:10
 * @UpdateUser:
 * @UpdateDate: 2020-07-12 16:10
 * @UpdateRemark:
 * @Version: 1.0
 */
open class BasePresenter {

    //管理订阅事件disposable，然后在acivity销毁的时候，调用compositeDisposable.dispose()就可以切断所有订阅事件，防止内存泄漏。
    var compositeDisposable : CompositeDisposable? = null

    //获取请求接口
    fun <T>getWebApi(host : String,web : Class<T>,client: OkHttpClient? = Okhttp3Factory.sharedclient()) : T{
        return HttpServiceGenerator.webApi(host,web,client)
    }

    //注销所有订阅事件
    fun detachView(){
        removeDisposable()
    }

    //添加管理订阅事件
    fun <T>addDisposable(observable : Observable<T>, observer : BaseObserver<T>) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer))
    }

    fun removeDisposable() {
        compositeDisposable?.dispose()
    }
}