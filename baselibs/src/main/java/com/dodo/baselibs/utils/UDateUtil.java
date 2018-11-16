package com.dodo.baselibs.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author martin
 */
public class UDateUtil {
    /**
     * 一天兑换成的毫秒值
     */
    public static final long MILLI_ONE_DAY = 24 * 60 * 60 * 1000;

    /**
     * 判断传入时间是否是今天
     *
     * @param when
     * @return
     */
    public static boolean isToday(long when) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int yesterdayYear = calendar.get(Calendar.YEAR);
        int yesterdayDay = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTimeInMillis(when);

        return yesterdayYear == calendar.get(Calendar.YEAR)
                && yesterdayDay == calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 判断传入时间是否是昨天
     *
     * @param when
     * @return
     */
    public static boolean isYesterday(long when) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(System.currentTimeMillis() - MILLI_ONE_DAY);
        int yesterdayYear = calendar.get(Calendar.YEAR);
        int yesterdayDay = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTimeInMillis(when);

        return yesterdayYear == calendar.get(Calendar.YEAR)
                && yesterdayDay == calendar.get(Calendar.DAY_OF_YEAR);
    }

}
