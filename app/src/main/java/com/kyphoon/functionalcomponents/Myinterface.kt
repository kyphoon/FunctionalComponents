package com.kyphoon.functionalcomponents

import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 *
 * @ProjectName: FunctionalComponents
 * @Package: com.kyphoon.functionalcomponents
 * @ClassName: Myinterface
 * @Description:
 * @Author: kyphoon
 * @CreateDate: 2020-07-30 15:52
 * @UpdateUser:
 * @UpdateDate: 2020-07-30 15:52
 * @UpdateRemark:
 * @Version: 1.0
 */
interface Myinterface{

    //用户登录
    @POST("/parking/pda/login/platLogin")
    fun login(@Body requestBody: RequestBody): Observable<String>
}