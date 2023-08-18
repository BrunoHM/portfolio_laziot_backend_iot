package com.senai.laziot.customObjects.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TimeConverter {

    public int convertStrTimeToInt(String timeMysql){
        String hours = timeMysql.substring(0, timeMysql.indexOf(":"));
        String minutes = timeMysql.substring(timeMysql.indexOf(":")+1, timeMysql.lastIndexOf(":"));
        String seconds = timeMysql.substring(timeMysql.lastIndexOf(":")+1);
        int miliTrigger = (Integer.valueOf(hours)*3600000) + (Integer.valueOf(minutes)*60000) + (Integer.valueOf(seconds)*1000);
        return miliTrigger;
    }
}
