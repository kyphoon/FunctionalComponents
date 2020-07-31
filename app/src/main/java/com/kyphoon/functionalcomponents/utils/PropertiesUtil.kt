package com.kyphoon.functionalcomponents.utils

import android.content.Context
import java.util.*

/**
 *
 * @ProjectName: functionalcomponents
 * @Package: com.kyphoon.functionalcomponents.utils
 * @ClassName: PropertiesUtil
 * @Description: 配置获取工具类
 * @Author: kyphoon
 * @CreateDate: 2020-07-12 15:16
 * @UpdateUser:
 * @UpdateDate: 2020-07-12 15:16
 * @UpdateRemark:
 * @Version: 1.0
 */
object PropertiesUtil{

    val properties : Properties = Properties()

    fun getPropertiesMsg(context: Context,file : String,key : String) : String{
        properties.load(context.assets?.open(file))
        return properties.getProperty(key)
    }
}