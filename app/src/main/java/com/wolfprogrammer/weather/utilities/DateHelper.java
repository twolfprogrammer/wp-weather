package com.wolfprogrammer.weather.utilities;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by thomas on 11/30/16.
 */

public class DateHelper {
    public static Boolean isTomorrow(Date date) {
        if(date == null) return false;
        return isSameDay(date, 1);
    }

    public static Boolean isToday(Date date) {
        if(date == null) return false;
        return isSameDay(date, 0);
    }

    public static Boolean isYesterday(Date date) {
        if(date == null) return false;
        return isSameDay(date, -1);
    }

    private static Boolean isSameDay(Date compareDate, int fromToday) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, fromToday);

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(compareDate);

        return calendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)
                && calendar.get(Calendar.DAY_OF_YEAR) == dateCalendar.get(Calendar.DAY_OF_YEAR);
    }
}
