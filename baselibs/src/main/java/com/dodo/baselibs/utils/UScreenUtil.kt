package com.dodo.baselibs.utils

import android.content.Context
import android.os.Build
import android.graphics.Point
import android.view.WindowManager

object UScreenUtil {

    /**
     * 获取屏幕尺寸
     */
    @JvmStatic
    fun getScreenSize(context: Context): Point {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.defaultDisplay.getRealSize(point)
        } else {
            wm.defaultDisplay.getSize(point)
        }
        return point
    }

    /**
     * Return the width of screen, in pixel.
     *
     * @return the width of screen, in pixel
     */
    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        return getScreenSize(context).x
    }

    /**
     * Return the height of screen, in pixel.
     *
     * @return the height of screen, in pixel
     */
    @JvmStatic
    fun getScreenHeight(context: Context): Int {
        return getScreenSize(context).y
    }


    /**
     * 获取 app 使用的屏幕尺寸
     */
    @JvmStatic
    fun getAppScreenSize(context: Context): Point {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        wm.defaultDisplay.getSize(point)
        return point
    }

    /**
     * Return the application's width of screen, in pixel.
     *
     * @return the application's width of screen, in pixel
     */
    @JvmStatic
    fun getAppScreenWidth(context: Context): Int {
        return getAppScreenSize(context).x
    }

    /**
     * Return the application's height of screen, in pixel.
     *
     * @return the application's height of screen, in pixel
     */
    @JvmStatic
    fun getAppScreenHeight(context: Context): Int {
        return getAppScreenSize(context).y
    }


}