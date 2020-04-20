package com.jay.java.算法.LeetCode.lc_0014;

/**
 * 题目 14. 最长公共前缀
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-26 17:51
 */
public class Solution {

    /**
     * 题目描述：
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * 示例 1: 输入: ["flower","flow","flight"] 输出: "fl"
     * 示例 2: 输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。
     * 说明: 所有输入只包含小写字母 a-z 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-common-prefix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {

        int solutionIndex = 1;

        switch (solutionIndex) {
            case 1: {
                // Solution1:
                solution1();
                break;
            }
            case 2: {
                // Solution2:
                solution2();
                break;
            }
            case 3: {
                // Solution3:
                solution3();
                break;
            }
            case 4: {
                // Solution4:
                solution4();
            }
        }
    }

    /**
     * Solution1：
     */
    private static void solution1() {
        System.out.println("-----Solution1-----\n");
        String[] s1 = {"flower", "flow", "flight"};
        String[] s2 = {"aa", "cc", "dd"};
        String[][] prefixArray = {s1, s2};
        for (String[] str : prefixArray) {
            System.out.println("str length：" + str.length + ", prefix：" + new Solution().longestCommonPrefix01(str));
        }

    }

    /**
     * Solution2：
     */
    private static void solution2() {
        System.out.println("-----Solution2-----\n");
    }

    /**
     * Solution3：
     */
    private static void solution3() {
        System.out.println("-----Solution3-----\n");
    }

    /**
     * Solution4：
     */
    private static void solution4() {
        System.out.println("-----Solution4-----\n");
    }

    public String longestCommonPrefix01(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String strI = strs[i];
            int indexI = strI.indexOf(prefix);
            while (strI.indexOf(prefix) != 0) {
                int len = prefix.length() - 1;
                prefix = prefix.substring(0, len);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }
}
