package com.gmy.borrow.controller;

import java.util.Calendar;
import java.util.Date;

public class testDate {
    public static void main(String[] args) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
//        System.out.println(cal);
        System.out.println(date);

        cal.add(Calendar.MONTH, 2);
        Date newDate = cal.getTime();
        System.out.println(newDate);
        System.out.println(newDate.after(date));
    }
}
