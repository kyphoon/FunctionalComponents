package com.kyphoon.functionalcomponents

import com.google.gson.Gson
import com.kyphoon.functionalcomponents.base.BasePresenter
import com.kyphoon.functionalcomponents.http.BaseObserver
import okhttp3.RequestBody

/**
 *
 * @ProjectName: IntelligentParking_huati
 * @Package: com.huati.intelligentparking.ui.presenter
 * @ClassName: LoginPresenter
 * @Description:
 * @Author: kyphoon
 * @CreateDate: 2020-05-06 15:49
 * @UpdateUser:
 * @UpdateDate: 2020-05-06 15:49
 * @UpdateRemark:
 * @Version: 1.0
 */
class LoginPresenter(private val view : ILoginView) : BasePresenter(){

    fun login(name : String,pwd : String){

//        view.loginSuccess(UserInfo())  "A4ttvjxjjU2ogay9386NZA=="

//        var builder = MultipartBody.Builder()
//            .addPart(MultipartBody.create(MediaType.get("application/json; charset=utf-8"),Gson().toJson(loginRequest)))

        var requestBody : RequestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8")
            , Gson().toJson(LoginRequest()))

        addDisposable(getWebApi("https://114.115.136.200:8447",Myinterface::class.java).login(requestBody),
            object : BaseObserver<String>(view,true){

            override fun Onsuccess(t: String) {

            }

            override fun Onerror(msg: String) {

            }

        })

    }

}