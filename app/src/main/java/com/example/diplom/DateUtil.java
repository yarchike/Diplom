package com.example.diplom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

;

public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy  HH:mm", Locale.US);


    public static String DateToString(Date date){
        String dateFormatted = null;
        if (date != null) {
            dateFormatted = dateFormat.format(date);
        }
        return dateFormatted;
    }

    public static Date StringToDate(String dateString){
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }
    public static Date clockReset(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        Date outputDate = calendar.getTime();

        return outputDate;
    }



}
