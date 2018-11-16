package com.dodo.baselibs.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * @author martin
 */
public class UNetUtil {

    /**
     * 检查手机网络状况
     *
     * @param context
     * @return
     */
    public static boolean checkNetStatus(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo info = cm.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
//			log.error("CHECK NETWORK STATUS ERROR!", e);
        }
        return false;
    }

    /**
     * 检查当前网络状况是否为WIFI或3G
     *
     * @param context
     * @return
     */
    public static boolean checkWifiOr3gNet(Context context) {
        try {

            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null || !cm.getBackgroundDataSetting()) {
                return false;
            }

            int netType = info.getType();
            int netSubtype = info.getSubtype();

            if (netType == ConnectivityManager.TYPE_WIFI) { // WIFI
                return info.isConnected();
            } else if (netType == ConnectivityManager.TYPE_MOBILE
                    && netSubtype == TelephonyManager.NETWORK_TYPE_UMTS
                    && !tm.isNetworkRoaming()) { // 3G
                return info.isConnected();
            } else {
                return false;
            }
        } catch (Exception e) {
//			log.error("CHECK WIFI OR 3G NET ERROR!", e);
        }
        return false;
    }

    /**
     * 判断是否为2G网络
     *
     * @param context
     * @return
     */
    public static boolean is2gNet(Context context) {
        try {
            ConnectivityManager mConnectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = mConnectivity.getActiveNetworkInfo();
            if (info == null || !mConnectivity.getBackgroundDataSetting()) {
                return false;
            }
            int netType = info.getType();
            int netSubtype = info.getSubtype();
            if (netType == ConnectivityManager.TYPE_MOBILE
                    && netSubtype != TelephonyManager.NETWORK_TYPE_UMTS) {
                return info.isConnected();
            } else {
                return false;
            }
        } catch (Exception e) {
//            log.error("CHECK 2g NET STATUS ERROR!", e);
        }
        return false;
    }

    /**
     * 检查是否有wifi
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager mConnectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // 检查网络连接，如果无网络可用，就不需要进行连网操作等
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        if (info == null || !mConnectivity.getBackgroundDataSetting()) {
            return false;
        }
        // 判断网络连接类型，只有在3G或wifi里进行一些数据更新。
        int netType = info.getType();
        if (netType == ConnectivityManager.TYPE_WIFI) {
            return info.isConnected();
        } else {
            return false;
        }
    }

    /**
     * 获取手机的ip地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
//			log.error(ex);
        }
        return null;
    }


}

