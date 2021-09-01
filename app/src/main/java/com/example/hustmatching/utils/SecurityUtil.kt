package com.example.hustmatching.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object SecurityUtil {

    fun getEncryptedSharedPreferences(context: Context) = EncryptedSharedPreferences.create("data", MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC), context, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

    fun encode(password: String): String {
        try {
            //获取md5加密对象
            val instance: MessageDigest = MessageDigest.getInstance("MD5")
            //对字符串加密，返回字节数组
            val digest: ByteArray = instance.digest(password.toByteArray())
            val sb = StringBuffer()
            for (b in digest) {
                //获取低八位有效值
                //将整数转化为16进制
                val hexString = Integer.toHexString(0xFF and b.toInt())
                if (hexString.length == 1) {
                    //如果是一位的话，补0
                    sb.append("0")
                }
                sb.append(hexString)
            }
            return sb.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}