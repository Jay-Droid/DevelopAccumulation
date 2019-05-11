package com.jay.java.算法.LeetCode;

public class TwoStringSum {
    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            if (i >= 0) carry += Integer.parseInt(String.valueOf(num1.charAt(i--)));
            if (j >= 0) carry += Integer.parseInt(String.valueOf(num2.charAt(j--)));
            sb.append(carry % 10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String numberA = "159";
        String numberB = "371";
        System.out.println(TwoStringSum.addStrings(numberA, numberB));
    }
}
