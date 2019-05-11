package com.jay.java.算法.LeetCode;

public class ReverseInteger {
    public static int reverse(int x) {
        boolean isNegative = x < 0;
        StringBuilder stringBuilder = new StringBuilder();
        String intMap = String.valueOf(x);
        if (isNegative) {
            intMap = intMap.replaceAll("-", "");
        }
        stringBuilder.append(intMap);
        stringBuilder.reverse();
        int num = 0;
        try {
            num = Integer.parseInt(isNegative ? stringBuilder.insert(0,"-").toString() : stringBuilder.toString());
        } catch (Exception e) {
        }
        return num;
    }

    public static void main(String[] args) {
        int a = 153423646;
        int b = 2147483647;
        int result = ReverseInteger.reverse(a);
        System.out.println(result);
    }
}
