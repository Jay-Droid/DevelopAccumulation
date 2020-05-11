package com.jay.java.算法.LeetCode.lc_0013;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目: 13. 罗马数字转整数
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-26 17:51
 */
public class Solution {

    /**
     * 题目描述：给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。
     * 12 写做 XII ，即为 X + II 。
     * 27 写做  XXVII, 即为 XX + V + II 。
     * 通常情况下，罗马数字中小的数字在大的数字的右边。
     * 但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。
     * 这个特殊的规则只适用于以下六种情况：
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 示例 1: 输入: "III" 输出: 3
     * 示例 2: 输入: "IV" 输出: 4
     * 示例 3: 输入: "IX"  输出: 9
     * 示例 4: 输入: "LVIII" 输出: 58 解释: L = 50, V= 5, III = 3.
     * 示例 5: 输入: "MCMXCIV" 输出: 1994 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/roman-to-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {

        int solutionIndex = 3;

        switch (solutionIndex) {
            case 1: {
                // Solution1: 先判断左右值，再执行左减右加操作
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
     * Solution1：先判断左右值，再执行左减右加操作
     * 按照题目的描述，可以总结如下规则：
     * 罗马数字由 I,V,X,L,C,D,M 构成；
     * 当小值在大值的左边，则减小值，如 IV=5-1=4；
     * 当小值在大值的右边，则加小值，如 VI=5+1=6；
     * 把一个小值放在大值的左边，就是做减法，否则为加法。
     *
     */
    private static void solution1() {
        System.out.println("-----Solution1-----\n");
        String[] romans = {"III", "IV", "IX", "LVIII", "MCMXCIV"};
        for (String roman : romans) {
            System.out.println("roman：" + roman + ", romanToInt：" + new Solution().romanToInt01(roman));
        }
    }

    /**
     * Solution2：
     */
    private static void solution2() {
        System.out.println("-----Solution2-----\n");
        String[] romans = {"III", "IV", "IX", "LVIII", "MCMXCIV"};
        for (String roman : romans) {
            System.out.println("roman：" + roman + ", romanToInt：" + new Solution().romanToInt02(roman));
        }
    }

    /**
     * Solution3：
     */
    private static void solution3() {
        System.out.println("-----Solution3-----\n");
        String[] romans = {"III", "IV", "IX", "LVIII", "MCMXCIV"};
        for (String roman : romans) {
            System.out.println("roman：" + roman + ", romanToInt：" + new Solution().romanToInt03(roman));
        }
    }

    public int romanToInt01(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        return sum += preNum;

    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public int romanToInt02(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int ans = 0;
        for (int i = 0; i < s.length(); ) {
            if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                ans += map.get(s.substring(i, i + 2));
                i += 2;
            } else {
                ans += map.get(s.substring(i, i + 1));
                i++;
            }
        }
        return ans;
    }

    /**
     * 思路：
     * 输入确保在 1~3999 范围内。
     * 由题找出阿拉伯数字和罗马数字之间的对应关系，从大到小逐步替换。
     * (贪心算法：找出当前每一步的最优解。)
     */
    public int romanToInt03(String s) {
        // 把阿拉伯数字与罗马数字可能出现的所有对应关系，从大到小放在两个数组中。
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int ans = 0;
        int index = 0;
        while (s.length() > 0) {
            while (s.startsWith(romans[index])) {
                ans += nums[index];
                s = s.substring(romans[index].length());
            }
            index++;
        }
        return ans;
    }


    /**
     * Solution4：
     */
    private static void solution4() {
        System.out.println("-----Solution4-----\n");
    }
}
