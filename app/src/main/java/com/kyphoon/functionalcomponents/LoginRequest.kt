package com.kyphoon.functionalcomponents

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 * @ProjectName: IntelligentParking_huati
 * @Package: com.huati.intelligentparking.gson.request
 * @ClassName: LoginRequest
 * @Description:
 * @Author: kyphoon
 * @CreateDate: 2020-05-26 10:34
 * @UpdateUser:
 * @UpdateDate: 2020-05-26 10:34
 * @UpdateRemark:
 * @Version: 1.0
 */
@Parcelize
data class LoginRequest (
    var pdaAccount : String ?= "",
    var passWord : String ?= "",
    var imei : String ?= ""
) : Parcelable
