package com.jay.java;

import com.jay.java.Android相关.view.MeasureSpec;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ①②③④⑤⑥⑦⑧⑨⑩
 */
public class MyClass {
    public static void main(String[] args) {
//        testCallable();
//        testMeasureSpec();
//        UTCToCST("2017-11-27T03:16:03.944Z", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        UTCToCST("2019-06-29T16:19:52+08:00", "yyyy-MM-dd'T'HH:mm:ssZ");
//        dateChange();
        dateChange2();


    }
    public static void dateChange2() {
        String str = "2010-5-27 12:10:12";
        //日期输出格式
        String targetFormat = "yyyy-MM-dd HH:mm";
        //日期原始格式
        String origFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        String s = "1988-11-06T12:14:05.671+08:00";
        String s2 = "2019-06-29T16:19:52+08:00";
        String s3 = "2019-06-17T16:01:46.180000+08:00";
        //去掉时区部分的冒号
        System.out.println(s);
        s = s.replaceAll(":[^:]*$", "00");
        System.out.println(s);
        DateFormat format = new SimpleDateFormat(origFormat);
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat(targetFormat);
        String dateString = format.format(date);
        System.out.println(dateString);
    }

    public static void dateChange() {
        String str = "2010-5-27 12:10:12";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        long timeInMillis = calendar.getTimeInMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        System.out.println(df.format(timeInMillis));
    }



    public static void UTCToCST(String UTCStr, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        //2、创建时区对象utcZone，获取utc所在的时区
        TimeZone utcZone = TimeZone.getTimeZone("UTC");
        //3、设置utc时区，为转换做准备
        sdf.setTimeZone(utcZone);
        try {
            date = sdf.parse(UTCStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("北京时间: " + date);
    }


    private static void testMeasureSpec() {
        MeasureSpec measureSpec = new MeasureSpec();

        System.out.println("MODE_SHIFT=" + MeasureSpec.MODE_SHIFT);
        System.out.println("MODE_MASK=" + Integer.toBinaryString(MeasureSpec.MODE_MASK));
        System.out.println("AT_MOST=" + Integer.toBinaryString(MeasureSpec.AT_MOST));
        System.out.println("EXACTLY=" + Integer.toBinaryString(MeasureSpec.EXACTLY));
        System.out.println("UNSPECIFIED=" + Integer.toBinaryString(MeasureSpec.UNSPECIFIED));
    }

    private static void testCallable() {
        try {
            MyCallable myCallable = new MyCallable();
            FutureTask<String> futureTask = new FutureTask<>(myCallable);
            new Thread(futureTask).start();
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyCallable implements Callable<String> {
        @Override
        public String call() {
            return "Callable String";
        }
    }


}
