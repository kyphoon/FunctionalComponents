package com.kyphoon.functionalcomponents.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 *
 * @ProjectName: functionalcomponents
 * @Package: com.kyphoon.functionalcomponents.http
 * @ClassName: HttpServiceGenerator
 * @Description: http请求类
 * @Author: kyphoon
 * @CreateDate: 2020-07-12 17:03
 * @UpdateUser:
 * @UpdateDate: 2020-07-12 17:03
 * @UpdateRemark:
 * @Version: 1.0
 */
object HttpServiceGenerator {

    @Synchronized
    fun <T>webApi(host : String,web : Class<T>,client : OkHttpClient?): T {
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl(host)
        retrofitBuilder.client(client)
        retrofitBuilder.addConverterFactory(JsonOrXmlConverterFactory.create())
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        val retrofit = retrofitBuilder.build()
        return retrofit.create(web)
    }
}