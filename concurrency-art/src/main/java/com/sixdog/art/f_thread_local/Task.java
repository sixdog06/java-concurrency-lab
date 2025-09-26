package com.sixdog.art.f_thread_local;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private final static ThreadLocal<DateFormat> THREAD_LOCAL = 
        ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) throws ParseException {
        Date date = THREAD_LOCAL.get().parse("2024-10-01 23:01:00");
        System.out.println(date);
    }
}
