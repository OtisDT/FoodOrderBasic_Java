package com.example.foodorder.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GetDataTime {
    private static final String DEFAULT_FORMAT_DATE = "hh:mm a, dd-MM-yyyy";
    public static String getDateAndTime(){
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT_DATE, Locale.ENGLISH);
        String date = sdf.format(Calendar.getInstance().getTime());
        return date;
    }

}
