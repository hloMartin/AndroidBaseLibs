package com.dodo.baselibs.utils

import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.Enumeration

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager

/**
 * @author martin
 */
object UNetUtil {

    /**
     * 获取手机的ip地址
     *
     * @return
     */
    //			log.error(ex);
    val localIpAddress: String?
        get() {
            try {
                val en = NetworkInterface
                        .getNetworkInterfaces()
                while (en.hasMoreElements()) {
                    val intf = en.nextElement()
                    val enumIpAddr = intf
                            .inetAddresses
                    while (enumIpAddr.hasMoreElements()) {
                        val inetAddress = enumIpAddr.nextElement()
                        if (!inetAddress.isLoopbackAddress) {
                            return inetAddress.hostAddress.toString()
                        }
                    }
                }
            } catch (ex: SocketException) {
            }
            return null
        }

    /**
     * 检查手机网络状况
     *
     * @param context
     * @return
     */
    fun checkNetStatus(context: Context): Boolean {
        try {
            val cm = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm != null) {
                val info = cm.activeNetworkInfo
                if (info != null && info.isConnected) {
                    if (info.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
        } catch (e: Exception) {
            //			log.error("CHECK NETWORK STATUS ERROR!", e);
        }

        return false
    }

    /**
     * 检查当前网络状况是否为WIFI或3G
     *
     * @param context
     * @return
     */
    fun checkWifiOr3gNet(context: Context): Boolean {
        try {

            val cm = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val tm = context
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val info = cm.activeNetworkInfo
            if (info == null || !cm.backgroundDataSetting) {
                return false
            }

            val netType = info.type
            val netSubtype = info.subtype

            return if (netType == ConnectivityManager.TYPE_WIFI) { // WIFI
                info.isConnected
            } else if (netType == ConnectivityManager.TYPE_MOBILE
                    && netSubtype == TelephonyManager.NETWORK_TYPE_UMTS
                    && !tm.isNetworkRoaming) { // 3G
                info.isConnected
            } else {
                false
            }
        } catch (e: Exception) {
            //			log.error("CHECK WIFI OR 3G NET ERROR!", e);
        }

        return false
    }

    /**
     * 判断是否为2G网络
     *
     * @param context
     * @return
     */
    fun is2gNet(context: Context): Boolean {
        try {
            val mConnectivity = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = mConnectivity.activeNetworkInfo
            if (info == null || !mConnectivity.backgroundDataSetting) {
                return false
            }
            val netType = info.type
            val netSubtype = info.subtype
            return if (netType == ConnectivityManager.TYPE_MOBILE && netSubtype != TelephonyManager.NETWORK_TYPE_UMTS) {
                info.isConnected
            } else {
                false
            }
        } catch (e: Exception) {
            //            log.error("CHECK 2g NET STATUS ERROR!", e);
        }

        return false
    }

    /**
     * 检查是否有wifi
     *
     * @param context
     * @return
     */
    fun isWifi(context: Context): Boolean {
        val mConnectivity = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // 检查网络连接，如果无网络可用，就不需要进行连网操作等
        val info = mConnectivity.activeNetworkInfo
        if (info == null || !mConnectivity.backgroundDataSetting) {
            return false
        }
        // 判断网络连接类型，只有在3G或wifi里进行一些数据更新。
        val netType = info.type
        return if (netType == ConnectivityManager.TYPE_WIFI) {
            info.isConnected
        } else {
            false
        }
    }


}

