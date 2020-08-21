package com.csdn.xs.exhausts.trafficmodoule.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author YJJ
 * @Date: Created in 16:03 2020-06-05
 */
public class DateUtils {
    public static String parseDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String beforeOneHourToNowDateString(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());

    }

    public static String parseDateToSimpleString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static Date beforeOneHourToNowDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        return calendar.getTime();
    }

    public static Date afterOneHourToDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
        return calendar.getTime();
    }

    public static Date getHourTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
}
