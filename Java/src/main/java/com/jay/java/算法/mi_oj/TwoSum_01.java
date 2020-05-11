package com.jay.java.算法.mi_oj;

import java.math.BigDecimal;
import java.util.Scanner;

public class TwoSum_01 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            String[] array = line.split(" ");

            long sum = 0;
            for (String s : array) {
                long a = Long.parseLong(s);
                sum = sum + a;
            }
            System.out.println(sum);

//            BigDecimal sum2 = new BigDecimal(0);
//            for (int i = 0; i < array.length; i++) {
//                BigDecimal a = new BigDecimal(array[i]);
//                sum2 = sum2.add(a);
//            }
//            System.out.println(sum2);
        }
    }

    /**
     * 1.使用Character.isDigit(char)判断
     *
     * @param strNum 输入的字符
     * @return boolean
     */
    public static boolean isStrAllNumber1(String strNum) {
        //把字符串转换为字符数组
        char[] num = strNum.toCharArray();
        for (char c : num) {
            // 判断输入的数字是否为数字还是字符把字符串转换为字符
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 2.使用类型转换判断
     *
     * @param strNum 输入的字符
     * @return boolean
     */
    public static boolean isStrAllNumber2(String strNum) {
        try {
            //把字符串强制转换为数字
            int num = Integer.valueOf(strNum);
            //如果是数字，返回True
            return true;
        } catch (Exception e) {
            //如果抛出异常，返回False
            return false;
        }
    }

    /**
     * 3.使用正则表达式判断
     *
     * @param strNum 输入的字符
     * @return boolean
     */
    public static boolean isStrAllNumber3(String strNum) {
        return strNum.matches("[0-9]+");
    }

}
