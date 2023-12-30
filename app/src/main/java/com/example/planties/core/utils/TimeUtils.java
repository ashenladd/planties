package com.example.planties.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    public static String TimeNow() {
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(currentTime);
    }
    public static int TimeRandom60to180() {
        int min = 60;
        int max = 180;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static int TimeConvertMinToHour(int minutes) {
        return (int) Math.ceil(minutes / 60.0);
    }
}
