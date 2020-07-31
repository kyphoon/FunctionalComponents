package com.kyphoon.functionalcomponents.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cazaea.sweetalert.SweetAlertDialog
import com.gyf.immersionbar.ImmersionBar
import com.kyphoon.functionalcomponents.utils.BaseDialogUtil

/**
 *
 * @ProjectName: functionalcomponents
 * @Package: com.kyphoon.functionalcomponents.base
 * @ClassName: BaseActivity
 * @Description: activity基类，用于封装提示框等
 * @Author: kyphoon
 * @CreateDate: 2020-07-12 16:03
 * @UpdateUser:
 * @UpdateDate: 2020-07-12 16:03
 * @UpdateRemark:
 * @Version: 1.0
 */
abstract class BaseActivity <T : BasePresenter> : AppCompatActivity(), BaseView, View.OnClickListener {

    protected lateinit var presenter : T

    protected abstract fun createPresenter() : T

    protected abstract fun getLayoutId() : Int

    protected abstract fun  initView()

    protected abstract fun initData()

    private var loadDialog : Dialog ?= null
    private var errorDialog : SweetAlertDialog ?= null
    private var successDialog : SweetAlertDialog ?= null
    private var warmDailog : SweetAlertDialog ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LayoutInflater.from(this).inflate(getLayoutId(), null))

        presenter = createPresenter()

        initView()
        initData()
    }

    override fun onClick(v: View?) {

    }


    override fun onDestroy() {
        super.onDestroy()
        //销毁网络请求
        presenter?.detachView()
        loadDialog?.dismiss()
        errorDialog?.dismiss()
        warmDailog?.dismiss()
        successDialog?.dismiss()
    }

    //加载弹窗
    override fun showloaddialog(msg: String): Dialog? {
        if(loadDialog == null){
            loadDialog = BaseDialogUtil.getLoadingDialog(this,msg)
        }
        loadDialog?.show()
        return loadDialog
    }

    override fun hidloaddialog() {
        loadDialog?.dismiss()
    }

    //警告弹窗
    override fun onBaseWarn(
        msg: String,
        title : String,
        canout : Boolean,
        confirListen : SweetAlertDialog.OnSweetClickListener) {
        if(warmDailog == null){
            warmDailog = BaseDialogUtil.getBaseWarnDialog(this,msg,title,canout,confirListen)
        }
        warmDailog?.show()
    }

    //请求错误的弹窗
    override fun onBaseError(
        msg: String,
        title: String,
        canout : Boolean,
        confirListen: SweetAlertDialog.OnSweetClickListener
    ) {
        if(errorDialog == null){
            errorDialog = BaseDialogUtil.getBaseErrorDialog(this,msg,title,canout,confirListen)
        }
        errorDialog?.show()
    }

    //请求成功之后的弹窗
    override fun onBaseSuccess(
        msg: String,
        title: String,
        canout : Boolean,
        confirListen: SweetAlertDialog.OnSweetClickListener
    ) {
        if(successDialog == null){
            successDialog = BaseDialogUtil.getBaseSuccessDialog(this,msg,title,canout,confirListen)
        }
        successDialog?.show()
    }

    //设置状态栏颜色
    protected fun setStateBarColor(id : Int){
        ImmersionBar.with(this)
            .autoStatusBarDarkModeEnable(true, 0.2f)
            .statusBarColor(id)
            .fitsSystemWindows(true)
            .init()
    }
}