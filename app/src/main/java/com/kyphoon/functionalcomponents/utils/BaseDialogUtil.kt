package com.kyphoon.functionalcomponents.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.cazaea.sweetalert.SweetAlertDialog
import com.kyphoon.functionalcomponents.R

/**
 *
 * @ProjectName: FunctionalComponents
 * @Package: com.kyphoon.functionalcomponents.utils
 * @ClassName: BaseDialogUtil
 * @Description: 网络请求加载提示
 * @Author: kyphoon
 * @CreateDate: 2020-07-30 14:44
 * @UpdateUser:
 * @UpdateDate: 2020-07-30 14:44
 * @UpdateRemark:
 * @Version: 1.0
 */
class BaseDialogUtil {

    companion object{

        fun getLoadingDialog(context: Context, msg : String) : Dialog {
            val inflater = LayoutInflater.from(context)
            val view: View = inflater.inflate(R.layout.dialog_loading, null)
            var text : TextView = view.findViewById(R.id.tv_desc)
            text.text = msg
            var mDialog : Dialog = Dialog(context, R.style.ProgressStyle)
            mDialog.setContentView(view)
            mDialog.setCanceledOnTouchOutside(false)
            return mDialog
        }

        fun getBaseErrorDialog(context: Context,msg: String,
                               title : String,
                               canout : Boolean,
                               confirListen : SweetAlertDialog.OnSweetClickListener) : SweetAlertDialog{
            var errorDailog : SweetAlertDialog = SweetAlertDialog(context,SweetAlertDialog.ERROR_TYPE)
            errorDailog.titleText = title
            errorDailog.contentText = msg
            errorDailog.setCanceledOnTouchOutside(canout)
            errorDailog.confirmText = "确定"
            errorDailog.setConfirmClickListener(confirListen)
            return errorDailog
        }

        fun getBaseWarnDialog(context: Context,msg: String,
                              title : String,
                              canout : Boolean,
                              confirListen : SweetAlertDialog.OnSweetClickListener) : SweetAlertDialog{
            var warmDailog : SweetAlertDialog = SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE)
            warmDailog.titleText = title
            warmDailog.contentText = msg
            warmDailog.setCanceledOnTouchOutside(canout)
            warmDailog.confirmText = "确定"
            warmDailog.setConfirmClickListener(confirListen)
            return warmDailog
        }

        fun getBaseSuccessDialog(context: Context,msg: String,
                                 title : String,
                                 canout : Boolean,
                                 confirListen : SweetAlertDialog.OnSweetClickListener) : SweetAlertDialog{
            var successDailog : SweetAlertDialog = SweetAlertDialog(context,SweetAlertDialog.SUCCESS_TYPE)
            successDailog.titleText = title
            successDailog.contentText = msg
            successDailog.setCanceledOnTouchOutside(canout)
            successDailog.confirmText = "确定"
            successDailog.setConfirmClickListener(confirListen)
            return successDailog
        }
    }
}