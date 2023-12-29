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
}
