package com.jay.java.算法.LeetCode;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * <p>
 * 你能不将整数转为字符串来解决这个问题吗？
 */
public class IsPalindrome {

    public static boolean isPalindrome(int number) {
        if (number < 0) {
            return false;
        }
        String numWrapper = String.valueOf(number);
        boolean isPalindrome = false;
        for (int i = 0; i < numWrapper.length(); i++) {
            char c1 = numWrapper.charAt(i);
            char c2 = numWrapper.charAt(numWrapper.length() - 1 - i);
            if (c1 == c2) {
                isPalindrome = true;
            } else {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    public static void main(String[] args) {
        int number = -1223221;
        System.out.println(IsPalindrome.isPalindrome(number));
    }
}
