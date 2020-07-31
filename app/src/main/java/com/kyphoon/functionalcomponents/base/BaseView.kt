package com.kyphoon.functionalcomponents.base

import android.app.Dialog
import com.cazaea.sweetalert.SweetAlertDialog

/**
 *
 * @ProjectName: functionalcomponents
 * @Package: com.kyphoon.functionalcomponents.base
 * @ClassName: BaseView
 * @Description:ui界面基础操作定义
 * @Author: kyphoon
 * @CreateDate: 2020-07-12 16:15
 * @UpdateUser:
 * @UpdateDate: 2020-07-12 16:15
 * @UpdateRemark:
 * @Version: 1.0
 */
interface BaseView {

    fun showloaddialog(msg : String = "加载中...") : Dialog?

    fun hidloaddialog()

    fun onBaseError(msg : String,title : String = "请求错误",canout : Boolean = true,
                    confirListen : SweetAlertDialog.OnSweetClickListener = object : SweetAlertDialog.OnSweetClickListener{
                        override fun onClick(sweetAlertDialog: SweetAlertDialog?) {
                            sweetAlertDialog?.dismiss()
                        }
                    })

    fun onBaseWarn(msg : String,title : String = "警告",canout : Boolean = true,
                   confirListen : SweetAlertDialog.OnSweetClickListener = object : SweetAlertDialog.OnSweetClickListener{
        override fun onClick(sweetAlertDialog: SweetAlertDialog?) {
            sweetAlertDialog?.dismiss()
        }
    })

    fun onBaseSuccess(msg : String,title : String = "操作成功",canout : Boolean = true,
                      confirListen : SweetAlertDialog.OnSweetClickListener = object : SweetAlertDialog.OnSweetClickListener{
                          override fun onClick(sweetAlertDialog: SweetAlertDialog?) {
                              sweetAlertDialog?.dismiss()
                          }
                      })
}