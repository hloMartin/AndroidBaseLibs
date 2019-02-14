package com.dodo.baselibs.utils

import java.util.Calendar
import java.util.GregorianCalendar

/**
 * @author martin
 */
object UDateUtil {
    /**
     * 一天兑换成的毫秒值
     */
    val MILLI_ONE_DAY = (24 * 60 * 60 * 1000).toLong()

    /**
     * 判断传入时间是否是今天
     *
     * @param when
     * @return
     */
    fun isToday(time: Long): Boolean {
        val calendar = GregorianCalendar()
        calendar.timeInMillis = System.currentTimeMillis()
        val yesterdayYear = calendar.get(Calendar.YEAR)
        val yesterdayDay = calendar.get(Calendar.DAY_OF_YEAR)

        calendar.timeInMillis = time

        return yesterdayYear == calendar.get(Calendar.YEAR) && yesterdayDay == calendar.get(Calendar.DAY_OF_YEAR)
    }

    /**
     * 判断传入时间是否是昨天
     *
     * @param when
     * @return
     */
    fun isYesterday(time: Long): Boolean {
        val calendar = GregorianCalendar()
        calendar.timeInMillis = System.currentTimeMillis() - MILLI_ONE_DAY
        val yesterdayYear = calendar.get(Calendar.YEAR)
        val yesterdayDay = calendar.get(Calendar.DAY_OF_YEAR)

        calendar.timeInMillis = time

        return yesterdayYear == calendar.get(Calendar.YEAR) && yesterdayDay == calendar.get(Calendar.DAY_OF_YEAR)
    }

}
