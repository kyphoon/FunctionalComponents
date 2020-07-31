package com.kyphoon.functionalcomponents

import android.util.Log
import com.cazaea.sweetalert.SweetAlertDialog
import com.kyphoon.functionalcomponents.base.BaseActivity
import com.kyphoon.functionalcomponents.utils.GlideUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<LoginPresenter>(),ILoginView {
    override fun createPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {
        GlideUtil.loadImg(this,R.drawable.ic_back,img)
        presenter.login("","")
    }


}
