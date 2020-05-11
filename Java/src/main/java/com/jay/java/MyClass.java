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

import static com.jay.java.Test.IdCardCheckUtil.IDCardValidate;

/** ①②③④⑤⑥⑦⑧⑨⑩ */
public class MyClass {
  public static void main(String[] args) {
    //        testCallable();
    //        testMeasureSpec();
    //        UTCToCST("2017-11-27T03:16:03.944Z", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    //        UTCToCST("2019-06-29T16:19:52+08:00", "yyyy-MM-dd'T'HH:mm:ssZ");
    //        dateChange();
    //        dateChange2();
    //        testMediaType();

    //    testDate();
    testIdCard();
  }

  private static void testIdCard() {
    //    String IDNumber = "23088219970717527X";
    //    String IDNumber = "371102199105183522";
    //    String IDNumber = "371482198801194827";
    //    String IDNumber = "37030619920605053X";
    //    String IDNumber = "659001198812281621";
    String IDNumber = "371522920424872";
    //    System.out.println(IDNumber + ":" + isIDNumber(IDNumber));
    System.out.println(IDNumber + ":" + IDCardValidate(IDNumber));
  }

  public static boolean isIDNumber(String IDNumber) {
    if (IDNumber == null || "".equals(IDNumber)) {
      return false;
    }
    // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
    String regularExpression =
        "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|"
            + "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
    // 假设18位身份证号码:41000119910101123X  410001 19910101 123X
    // ^开头
    // [1-9] 第一位1-9中的一个      4
    // \\d{5} 五位数字           10001（前六位省市县地区）
    // (18|19|20)                19（现阶段可能取值范围18xx-20xx年）
    // \\d{2}                    91（年份）
    // ((0[1-9])|(10|11|12))     01（月份）
    // (([0-2][1-9])|10|20|30|31)01（日期）
    // \\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
    // [0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
    // $结尾

    // 假设15位身份证号码:410001910101123  410001 910101 123
    // ^开头
    // [1-9] 第一位1-9中的一个      4
    // \\d{5} 五位数字           10001（前六位省市县地区）
    // \\d{2}                    91（年份）
    // ((0[1-9])|(10|11|12))     01（月份）
    // (([0-2][1-9])|10|20|30|31)01（日期）
    // \\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
    // $结尾

    boolean matches = IDNumber.matches(regularExpression);

    // 判断第18位校验值
    if (matches) {

      if (IDNumber.length() == 18) {
        try {
          char[] charArray = IDNumber.toCharArray();
          // 前十七位加权因子
          int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
          // 这是除以11后，可能产生的11位余数对应的验证码
          String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
          int sum = 0;
          for (int i = 0; i < idCardWi.length; i++) {
            int current = Integer.parseInt(String.valueOf(charArray[i]));
            int count = current * idCardWi[i];
            sum += count;
          }
          char idCardLast = charArray[17];
          int idCardMod = sum % 11;
          if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
            return true;
          } else {
            System.out.println(
                "身份证最后一位:"
                    + String.valueOf(idCardLast).toUpperCase()
                    + "错误,正确的应该是:"
                    + idCardY[idCardMod].toUpperCase());
            return false;
          }

        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("异常:" + IDNumber);
          return false;
        }
      }
    }
    return matches;
  }

  private static void testDate() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.YEAR, -1);
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH) + 1;
    int startDay = c.get(Calendar.DAY_OF_MONTH);
    System.out.println("过去一年：" + startYear);
    System.out.println("过去一年：" + startMonth);
    System.out.println("过去一年：" + startDay);

    Calendar c2 = Calendar.getInstance();
    c2.setTime(new Date());
    c2.add(Calendar.DATE, 6);
    int endYear = c2.get(Calendar.YEAR);
    int endMonth = c2.get(Calendar.MONTH) + 1;
    int endDay = c2.get(Calendar.DAY_OF_MONTH);
    System.out.println("未来一周：" + endYear);
    System.out.println("未来一周：" + endMonth);
    System.out.println("未来一周：" + endDay);
  }

  private static void testMediaType() {

    String path =
        "http://boss-cdn-2018.o3cloud.cn/MP4_20200107_125738_PHOTOMOVIE-5e1446edce6d2a734dc4f0c6.mp4?e=1578466501&token=igCQPJ5b9IeJcnR0zLqoL0S5u2wd1yYLuj_DaG6n:rJTrnTDblMvxpVu8Li82XgqVVn8=";
    System.out.println(MediaFileUtil.isVideoFileType(path));
  }

  String pathvideo = "你的网络视频路径";

  public static void dateChange2() {
    String str = "2010-5-27 12:10:12";
    // 日期输出格式
    String targetFormat = "yyyy-MM-dd HH:mm";
    // 日期原始格式
    String origFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    String s = "1988-11-06T12:14:05.671+08:00";
    String s2 = "2019-06-29T16:19:52+08:00";
    String s3 = "2019-06-17T16:01:46.180000+08:00";
    // 去掉时区部分的冒号
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
    // 2、创建时区对象utcZone，获取utc所在的时区
    TimeZone utcZone = TimeZone.getTimeZone("UTC");
    // 3、设置utc时区，为转换做准备
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
