package com.alice.springboot.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 获取当前日期所在月天数
     * @param date 日期
     * @return 所在月天数
     */
    public static int getMonthMaxDays(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 增加指定天数
     * @param date 当前日期
     * @param days 增加的天数
     * @return 增加天数之后的日期
     */
    public static Date addDays(Date date, int days)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_YEAR, days);

        return calendar.getTime();
    }

    /**
     * 获取俩个日期之间间隔的天数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 相差天数
     */
    public static int getIntervalDays(Date startDate, Date endDate)
    {
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        endCalendar.setTime(endDate);

        int startYear = startCalendar.get(Calendar.YEAR);
        int endYear = startCalendar.get(Calendar.YEAR);
        int startDaysInYear = startCalendar.get(Calendar.DAY_OF_YEAR);
        int endDaysInYear = endCalendar.get(Calendar.DAY_OF_YEAR);

        if (startYear == endYear)
        {
            return endDaysInYear - startDaysInYear + 1;
        }
        else
        {
            int startYearTotalDays = startCalendar.getActualMaximum(Calendar.DAY_OF_YEAR);
            return endDaysInYear + startYearTotalDays - startDaysInYear + 1;
        }

    }
}
