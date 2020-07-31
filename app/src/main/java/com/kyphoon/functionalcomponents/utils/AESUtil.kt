package com.kyphoon.functionalcomponents.utils

import android.annotation.SuppressLint
import android.os.Build
import android.text.TextUtils
import com.kyphoon.functionalcomponents.weight.Base64Encoder
import java.nio.charset.Charset
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


/**
 *
 * @ProjectName: functionalcomponents
 * @Package: com.kyphoon.functionalcomponents.utils
 * @ClassName: AESUtil
 * @Description: AES加密工具类
 * @Author: kyphoon
 * @CreateDate: 2020-06-16 9:22
 * @UpdateUser:
 * @UpdateDate: 2020-06-16 9:22
 * @UpdateRemark:
 * @Version: 1.0
 */
class AESUtil {

    companion object{

        var CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding" //AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
        var AES = "AES" //AES 加密
        var SHA1PRNG = "SHA1PRNG" //// SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法
        var KEY = "799126a605235a45"
        var XL = "ef70bd83506f4907"

        fun encrypt(content : String) : String{
            if (TextUtils.isEmpty(content)) {
                return content
            }
            try {
                val result: ByteArray? = encrypt(KEY,content.toByteArray(Charset.forName("utf-8")))
                return Base64Encoder.encode(result)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return ""
        }

        /*
    * 加密
    */
        @Throws(Exception::class)
        private fun encrypt(key: String, clear: ByteArray): ByteArray? {
            val raw: ByteArray? = key.toByteArray(Charset.forName("utf-8"))
            val skeySpec = SecretKeySpec(raw, AES)
            val cipher: Cipher = Cipher.getInstance(CBC_PKCS5_PADDING)
            cipher.init(
                Cipher.ENCRYPT_MODE,
                skeySpec,
                IvParameterSpec(XL.toByteArray(Charset.forName("utf-8")))// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
//                IvParameterSpec(ByteArray(cipher.getBlockSize()))
            )
            return cipher.doFinal(clear)
        }

        // 对密钥进行处理
        @SuppressLint("DeletedProvider")
        @Throws(java.lang.Exception::class)
        private fun getRawKey(seed: ByteArray): ByteArray? {
            val kgen: KeyGenerator = KeyGenerator.getInstance(AES)
            //for android
            var sr: SecureRandom? = null
            // 在4.2以上版本中，SecureRandom获取方式发生了改变
            sr = if (Build.VERSION.SDK_INT >= 17) {
                SecureRandom.getInstance(SHA1PRNG, "Crypto")
            } else {
                SecureRandom.getInstance(SHA1PRNG)
            }
            // for Java
            // secureRandom = SecureRandom.getInstance(SHA1PRNG);
            sr.setSeed(seed)
            kgen.init(128, sr) //256 bits or 128 bits,192bits
            //AES中128位密钥版本有10个加密循环，192比特密钥版本有12个加密循环，256比特密钥版本则有14个加密循环。
            val skey: SecretKey = kgen.generateKey()
            return skey.getEncoded()
        }
    }


}