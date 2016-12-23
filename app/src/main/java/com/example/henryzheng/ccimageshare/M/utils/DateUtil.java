package com.example.henryzheng.ccimageshare.M.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by henryzheng on 2016/12/23.
 */
public class DateUtil {
    /**
     * date格式 2015-4-23
     *
     * @param date
     * @return
     */
    public  static String[] getFormDateFromDate(String date) {
        int month=0;
        int dayOfMonth=0;
        int dayOfWeek=0;
        String monthStr="", dayOfWeekStr="",dayOfMonthStr="";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(date));
            month = c.get(Calendar.MONTH);
            dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        switch (c.get(Calendar.DAY_OF_WEEK))
        {
            case 1:
                dayOfWeekStr = "SunDay";
                break;
            case 2:
                dayOfWeekStr = "Monday";
                break;
            case 3:
                dayOfWeekStr = "Tuesday";
                break;
            case 4:
                dayOfWeekStr = "Wednesday";
                break;
            case 5:
                dayOfWeekStr = "Thursday";
                break;
            case 6:
                dayOfWeekStr = "Friday";
                break;
            case 7:
                dayOfWeekStr = "Saturday";
                break;
        }

        switch (c.get(Calendar.MONTH)) {
            case 0:
                monthStr = "January";
                break;
            case 1:
                monthStr = "February";
                break;
            case 2:
                monthStr = "March";
                break;
            case 3:
                monthStr = "April";
                break;
            case 4:
                monthStr = "May";
                break;
            case 5:
                monthStr = "June";
                break;
            case 6:
                monthStr = "July";
                break;
            case 7:
                monthStr = "August";
                break;
            case 8:
                monthStr = "September";
                break;
            case 9:
                monthStr = "October";
                break;
            case 10:
                monthStr = "November";
                break;
            case 11:
                monthStr = "December";
                break;
        }
        if (dayOfMonth<10){
            dayOfMonthStr="0"+dayOfMonth;
        }else
            dayOfMonthStr=dayOfMonth+"";
        return new String[]{monthStr,dayOfMonthStr,dayOfWeekStr};
    }

}
