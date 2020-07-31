package com.kyphoon.functionalcomponents.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.yanzhenjie.permission.Action
import com.yanzhenjie.permission.AndPermission

/**
 *
 * @ProjectName: IntelligentParking_huati
 * @Package: com.huati.intelligentparking.utils
 * @ClassName: IMEIUtil
 * @Description:Android中设备唯一码有很多，如：MAC地址、IMEI号（DeviceId）、IMSI号、ANDROID_ID、序列号（SerialNumber）等，
 *              但并不是所有设备上都能稳定获取到这些值
 *              IMEI号（国际移动设备身份码）、IMSI号（国际移动设备识别码）这两个是有电话功能的移动设备才具有，
 *              也就是说某些没有电话功能的平板是获取不到IMEI和IMSI号的。且在某些设备上getDeviceId()会返回垃圾数据
 *              设备刷机wipe数据或恢复出厂设置时ANDROID_ID值会被重置。
                ②.现在网上已有修改设备ANDROID_ID值的APP应用。
                ③.某些厂商定制的系统可能会导致不同的设备产生相同的ANDROID_ID。
                ④.某些厂商定制的系统可能导致设备返回ANDROID_ID值为空。
                ⑤.CDMA设备，ANDROID_ID和DeviceId返回的值相同
                SerialNumber在某些设备上此方法会返回垃圾数据

                一种比较折衷的办法，在获取MAC地址之前先判断当前WiFi状态，若开启了Wifi，则直接获取MAC地址，
                若没开启Wifi，则用代码开启Wifi，然后马上关闭，再获取MAC地址。
 * @Author: kyphoon
 * @CreateDate: 2020-07-21 8:59
 * @UpdateUser:
 * @UpdateDate: 2020-07-21 8:59
 * @UpdateRemark:
 * @Version: 1.0
 */
class IMEIUtil {
    companion object{
        fun getIMEI(context: Context) : String{
            var mTelephonyMgr : TelephonyManager = (context.getSystemService(Context.TELEPHONY_SERVICE))
                    as TelephonyManager
            var msg : String = ""
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
            }else{
                msg = mTelephonyMgr.getDeviceId()//获取IMEI号
            }

            //            mTelephonyMgr.getSubscriberId()//获取IMSI号
            return msg
        }

        fun getMac(context: Context) : String{
            var macAddress  : String = ""
            AndPermission.with(context)
                .permission(
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.WAKE_LOCK)
                .onGranted(Action { permissions: List<String?>? ->
                    var wifiManager : WifiManager = (context.getSystemService(Context.WIFI_SERVICE))
                            as WifiManager
                    var info : WifiInfo = wifiManager.getConnectionInfo()
                    if(!wifiManager.isWifiEnabled()) {
                        //必须先打开，才能获取到MAC地址
                        wifiManager.setWifiEnabled(true)
                        wifiManager.setWifiEnabled(false)
                    }
                    if(null!= info) {
                        macAddress = info.getMacAddress()
                    }
                }) // 用户拒绝权限，包括不再显示权限弹窗也在此列
                .onDenied(Action { permissions: List<String?>? ->
                    Toast.makeText(context,"权限获取失败",Toast.LENGTH_SHORT).show()
                })
                .start()
            return macAddress
        }

        fun getDiviceId(context: Context) : String{
            val ANDROID_ID: String =
                Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID)
            return ANDROID_ID
        }
    }
}