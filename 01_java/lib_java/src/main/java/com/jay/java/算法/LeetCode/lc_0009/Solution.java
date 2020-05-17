package com.jay.java.算法.LeetCode.lc_0009;

/**
 * 题目 9. 回文数
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-26 17:51
 */
public class Solution {

    /**
     * 题目描述：
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * 示例 1: 输入: 121 输出: true
     * 示例 2: 输入: -121 输出: false 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3: 输入: 10 输出: false 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     * 进阶: 你能不将整数转为字符串来解决这个问题吗？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {

        int solutionIndex = 3;

        switch (solutionIndex) {
            case 1: {
                // Solution1:反转一半数字
                solution1();
                break;
            }
            case 2: {
                // Solution2: 整数转为字符串，字符串反转后比较 （LeetCode不推荐）
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
     * Solution1：反转一半数字
     * 思路
     * 映入脑海的第一个想法是将数字转换为字符串，并检查字符串是否为回文。但是，这需要额外的非常量空间来创建问题描述中所不允许的字符串。
     * 第二个想法是将数字本身反转，然后将反转后的数字与原始数字进行比较，如果它们是相同的，那么这个数字就是回文。
     * 但是，如果反转后的数字大于 int.MAX，我们将遇到整数溢出问题。
     * 按照第二个想法，为了避免数字反转可能导致的溢出问题，为什么不考虑只反转 int 数字的一半？毕竟，如果该数字是回文，其后半部分反转后应该与原始数字的前半部分相同。
     * 例如，输入 1221，我们可以将数字 “1221” 的后半部分从 “21” 反转为 “12”，并将其与前半部分 “12” 进行比较，因为二者相同，我们得知数字 1221 是回文。
     */
    private static void solution1() {
        System.out.println("-----Solution1-----\n");
        int[] num = {1221, 12221, 454354, -2147483648, 2147483647};
        for (int x : num) {
            System.out.println("x：" + x + ", isPalindrome：" + new Solution().isPalindrome01(x));
        }
    }

    /**
     * 判断一个整数是否是回文数
     * 首先，我们应该处理一些临界情况。所有负数都不可能是回文，例如：-123 不是回文，因为 - 不等于 3。所以我们可以对所有负数返回 false。
     * <p>
     * 现在，让我们来考虑如何反转后半部分的数字。
     * 对于数字 1221，如果执行 1221 % 10，我们将得到最后一位数字 1，
     * 要得到倒数第二位数字，我们可以先通过除以 10 把最后一位数字从 1221 中移除，1221 / 10 = 122，
     * 再求出上一步结果除以 10 的余数，122 % 10 = 2，就可以得到倒数第二位数字。
     * 如果我们把最后一位数字乘以 10，再加上倒数第二位数字，1 * 10 + 2 = 12，就得到了我们想要的反转后的数字。
     * 如果继续这个过程，我们将得到更多位数的反转数字。
     * <p>
     * 现在的问题是，我们如何知道反转数字的位数已经达到原始数字位数的一半？
     * 我们将原始数字除以 10，然后给反转后的数字乘上 10，
     * 所以，当原始数字小于反转后的数字时，就意味着我们已经处理了一半位数的数字。
     *
     * @param x 要判断的目标数字
     * @return 是否是回文数
     */
    private Boolean isPalindrome01(int x) {
        //负数和个位是0的非0数字都不是回文数，可以直接排除
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        //用于接收反转一半数字的变量
        int revertedNumber = 0;
        //反转一半数字的值大于原数字前一半的时候表示已经反转了一半，结束循环
        while (x > revertedNumber) {
            //从个位开始反转原数字
            revertedNumber = revertedNumber * 10 + x % 10;
            //原数剔除个位上的数字以便继续下一次循环
            x /= 10;
        }
        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中间的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    /**
     * Solution2：整数转为字符串，字符串反转后比较
     */
    private static void solution2() {
        System.out.println("-----Solution2-----\n");
        int[] num = {1221, 12221, 454354, -2147483648, 2147483647};
        for (int x : num) {
            System.out.println("x：" + x + ", isPalindrome：" + new Solution().isPalindrome02(x));
        }
    }

    /**
     * 判断一个数字是否是回文数
     * StringBuilder(x).reverse()
     *
     * @param x target
     * @return boolean
     */
    public boolean isPalindrome02(int x) {
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }

    /**
     * Solution3：左右指针比较
     */
    private static void solution3() {
        System.out.println("-----Solution3-----\n");
        int[] num = {121,1221, 12221, 454354, -2147483648, 2147483647};
        for (int x : num) {
            System.out.println("x：" + x + ", isPalindrome：" + new Solution().isPalindrome03(x));
        }
    }

    public boolean isPalindrome03(int x) { //x=123
        //排除负数
        if (x < 0) return false;
        //权数的变量
        int div = 1;
        //获取x的权数 x=123,div=100
        while (x / div >= 10) div *= 10; //div=100
        //x>0进入循环
        while (x > 0) {
            // 获取最高位的数字
            int left = x / div; //left=1
            // 获取个位的数字
            int right = x % 10; // right=3
            // 最高位和最低位上的数字有一个不同就不是水仙花数
            if (left != right) return false;
            // X去头去尾 获取中间的数位
            x = (x % div) / 10; // x=2
            // 权数减少两位
            div /= 100; // div=1
        }
        return true;
    }


    /**
     * Solution4：
     */
    private static void solution4() {
        System.out.println("-----Solution4-----\n");
    }
}
