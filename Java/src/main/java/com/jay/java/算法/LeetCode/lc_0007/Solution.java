package com.jay.java.算法.LeetCode.lc_0007;


/**
 * 7. 整数反转
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-26 17:51
 */
public class Solution {

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 123
     * 输出: 321
     * 示例 2:
     * <p>
     * 输入: -123
     * 输出: -321
     * 示例 3:
     * <p>
     * 输入: 120
     * 输出: 21
     * 注意:
     * <p>
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     * [-2147483648,2147483647]
     * 32位int
     * 正数（符号位是0）：0~2147483647
     * 负数（符号位是1）：-1~2147483648
     * 正负都是2147483648个 只不过0放到正数（符号位为0）那边
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {

        int solutionIndex = 1;

        switch (solutionIndex) {

            case 1: {
                //Solution1: 弹出和推入数字 & 溢出前进行检查
                solution1();
                break;
            }
            case 2: {
                //Solution2: 两遍哈希表
                solution2();
                break;
            }
            case 3: {
                //Solution3: 一遍哈希表
                solution3();
                break;
            }
            case 4: {
                // Solution4: 排序+指针
                solution4();
            }
        }
    }

    /**
     * 思路
     * 我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。
     * 反转整数的方法可以与反转字符串进行类比。
     * 我们想重复“弹出” x 的最后一位数字，并将它“推入”到 rev 的后面。最后，rev 将与 x 相反。
     * 要在没有辅助堆栈 / 数组的帮助下 “弹出” 和 “推入” 数字，我们可以使用数学方法。
     * //pop operation:
     * pop = x % 10;
     * x /= 10;
     * //push operation:
     * temp = rev * 10 + pop;
     * rev = temp;
     */
    private static void solution1() {
        System.out.println("-----Solution1-----\n");
        int[] num = {122, 43434, 45454, -2147483648, 2147483647};
        for (int n : num) {
            System.out.println("x=" + n + ", rev=" + reverse01(n));
        }

    }

    /**
     * 整数反转解法一
     *
     * @param x 要反转的整数
     * @return 反转后的整数
     */
    public static int reverse01(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    private static void solution2() {
        System.out.println("-----Solution2-----\n");


    }


    private static void solution3() {
        System.out.println("-----Solution3-----\n\n");

    }


    private static void solution4() {
        System.out.println("-----Solution4-----\n\n");


    }


}
