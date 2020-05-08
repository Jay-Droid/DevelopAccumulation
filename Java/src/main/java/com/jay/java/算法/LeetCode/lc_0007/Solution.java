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
     * 题目：
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 示例 1:输入: 123 输出: 321
     * 示例 2:输入: -123 输出: -321
     * 示例 3:输入: 120 输出: 21
     * 注意:
     * 假设我们的环境只能存储得下 32 位的有符号整数(int类型)，则其数值范围为 [−2^31,  2^31−1]，即[-2147483648,2147483647]
     * 请根据这个假设，如果反转后整数溢出那么就返回 0。
     * 32位int最值
     * 正数（二进制符号位是0）：0~2147483647    / 01111111 11111111 11111111 11111111
     * 负数（二进制符号位是1）：-1~-2147483648  / 10000000 00000000 00000000 00000001
     * 正负都是2147483648个 只不过0放到正数那边
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 解题思路：
     * <P> Solution1: 弹出和推入数字 & 溢出前进行检查
     * 执行步骤：
     * 1，将目标数字/10操作进行减少位数的循环操作，直到目标数字=0
     * 2，弹出当前目标数字的个位数字 //int pop = x % 10;
     * 3，在将取出的个位数字追加到反转后到数字之前先对反转后的数字做溢出检查
     * 4，未发生溢出进行推入操作 //rev = rev * 10 + pop;
     * 5，执行 x/10 操作修改循环条件
     *
     * 复杂度分析：Time:O(n) Space:O(1)
     *
     *
     */
    public static void main(String[] args) {

        int solutionIndex = 2;

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
     * Solution1：弹出和推入数字 & 溢出前进行检查
     * 思路
     * 我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。
     * 反转整数的方法可以与反转字符串进行类比。
     * 我们想重复“弹出” x 的最后一位数字，并将它“推入”到 rev 的后面。最后，rev 将与 x 相反。
     * 要在没有辅助堆栈 / 数组的帮助下 “弹出” 和 “推入” 数字，我们可以使用数学方法。
     * //弹出操作:
     * pop = x % 10;
     * x /= 10;
     * //推入操作:
     * temp = rev * 10 + pop;
     * rev = temp;
     */
    private static void solution1() {
        System.out.println("-----Solution1-----\n");
        //随机生成的数字数组
        int[] numArray = getNumArray();
        //反转后的数字数组
        int[] reverseArray = new int[numArray.length];
        //获取反转后的数字
        for (int i = 0; i < numArray.length; i++) {
            //Solution1：弹出和推入数字 & 溢出前进行检查
            reverseArray[i] = reverse01(numArray[i]);
        }
        //打印反转后的数字
        printArray("反转后的数字数组:", reverseArray);
        System.out.println("--------- 益处边界值 ---------");
        System.out.println("MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("MIN_VALUE: " + Integer.MIN_VALUE);
    }

    /**
     * 整数反转解法一: 弹出和推入数字 & 溢出前进行检查
     * 执行步骤：
     * 1，将目标数字/10操作进行减少位数的循环操作，直到目标数字=0
     * 2，弹出当前目标数字的个位数字 //int pop = x % 10;
     * 3，在将取出的个位数字追加到反转后到数字之前先对反转后的数字做溢出检查
     * 4，未发生溢出进行推入操作 //rev = rev * 10 + pop;
     * 5，执行 x/10 操作修改循环条件
     *
     * 复杂度分析：Time:O(n) Space:O(1)
     * 时间复杂度：O(n)
     * 我们会循环目标数字对每一位上对数字 ，所以时间复杂度为线性O(n)。
     * 空间复杂度：O(1)
     * 在整个过程中未使用额外空间存取操作，所以空间复杂度为常数级别O(1)。
     *
     * @param x 要反转的整数
     * @return 反转后的整数
     */
    public static int reverse01(int x) { //x=123
        //反转后的结果值
        int rev = 0;
        //直到x整除到0才结束while循环
        while (x != 0) { // true
            //弹出个位上的数字
            int pop = x % 10; // pop=3
            //2147483647,反转后的数字超出了Int的最大值，分别检查十位以上的数位和个位
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10))
                return 0;
            //-2147483648，反转后的数字小于了Int的最小值，分别检查十位以上的数位和个位
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10))
                return 0;
            //推入反转后的每个数字
            rev = rev * 10 + pop; //3
            //x去整除个位数字以便进行下一次循环直到循环条件不成立
            x /= 10; //x=12
        }
        //反转完成返回反转后的数字
        return rev;
    }

    /**
     * 整数反转解法二: 弹出和推入数字 & 溢出前进行检查
     */
    private static void solution2() {
        System.out.println("-----Solution2-----\n");
        //随机生成的数字数组
        int[] numArray = getNumArray();
        //反转后的数字数组
        int[] reverseArray = new int[numArray.length];
        //获取反转后的数字
        for (int i = 0; i < numArray.length; i++) {
            //第二种方案
            reverseArray[i] = reverse02(numArray[i]);
        }
        //打印反转后的数字
        printArray("反转后的数字数组:", reverseArray);
        System.out.println("--------- 益处边界值 ---------");
        System.out.println("MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("MIN_VALUE: " + Integer.MIN_VALUE);
    }


    /**
     *
     * Int 类型的最小负数乘以-1 等于本身
     * 1000 0000 0000 0000
     * --------------- 取反
     * 0111 1111 1111 1111
     * --------------- + 1
     * 1000 0000 0000 0000
     *
     * @param x 要反转的整数
     * @return 反转后的整数
     */
    public static int reverse02(int x) { //x=123
        //反转后的结果值
        int rev = 0;
        //Int最小值取正会导致溢出，
        if (x == Integer.MIN_VALUE) return 0;
        //符号位
        int signBit = x < 0 ? -1 : 1; //1
        //取 x 对
        x *= signBit; //x=123
        while (x > 0) {
            int n = rev; // n=0,3
            n = n * 10 + x % 10; //n=3,32
            x /= 10; //x=12,1
            //todo 如何判断的益处
            if (n / 10 != rev) return 0; //3/10==0,32/10==3
            rev = n; //rev=3
        }
        return rev * signBit; //3*1
    }


    private static void solution3() {
        System.out.println("-----Solution3-----\n");
        int[] num = {-2147483648, 123, -123, 120, 2147483647};
        for (int n : num) {
            System.out.println("x=" + n + ", rev=" + reverse03(n));
        }

    }

    public static int reverse03(int x) {
        long rev = 0;
        if (x == Integer.MIN_VALUE) return 0;
        int signBit = x < 0 ? -1 : 1;
        x *= signBit;
        String xStr = Long.toString(x);
        rev = Long.valueOf(reverseToStr1(xStr));
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) (rev * signBit);
    }


    /**
     * 字符串反转：by StringBuffer
     *
     * @param str 要反转的字符串
     * @return 反转后的字符串
     */
    public static String reverseToStr1(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * 字符串反转：by toCharArray
     *
     * @param str 要反转的字符串
     * @return 反转后的字符串
     */
    public static String reverseToStr2(String str) {
        char[] chars = str.toCharArray();
        String reverse = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse += chars[i];
        }
        return reverse;
    }

    /**
     * 字符串反转：by charAt
     *
     * @param str 要反转的字符串
     * @return 反转后的字符串
     */
    public static String reverseToStr3(String str) {
        String reverse = "";
        int length = str.length();
        for (int i = 0; i < length; i++) {
            reverse = str.charAt(i) + reverse;
        }
        return reverse;
    }


    private static void solution4() {
        System.out.println("-----Solution4-----\n");
        int[] num = {-2147483648, 123, -123, 120, 2147483647};
        for (int n : num) {
            System.out.println("x=" + n + ", rev=" + reverse04(n));
        }

    }

    /**
     *
     * @param x
     * @return
     */
    public static int reverse04(int x) {
        int pop;
        // 用long类型判断溢出，不够稳妥，如果后期改成入参为long
        long rev = 0;
        while (x != 0) {
            pop = x % 10;
            x = x / 10;
            rev = rev * 10 + pop;
        }
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) rev;

    }

    /**
     * 获取随机产生的数字数组
     *
     * @return numArray
     */
    private static int[] getNumArray() {
        int[] numArray = getRandomNumArray(0, Integer.MAX_VALUE - 1, 10);
        printArray("随机产生的数字数组:", numArray);
        return numArray;
    }

    /**
     * 打印数组
     *
     * @param numArray numArray
     */
    private static void printArray(String info, int[] numArray) {
        if (!info.isEmpty()) {
            System.out.println(info);
        }
        String span = ", ";
        for (int i = 0; i < numArray.length; i++) {
            if (i == numArray.length - 1) {
                span = "";
            }
            System.out.print("[" + i + "] " + numArray[i] + span);
        }

        System.out.println();

    }


    /**
     * 随机指定范围内N个不重复的随机数
     * 最简单最易理解的两重循环去重
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    public static int[] getRandomNumArray(int min, int max, int n) {
        //条件校验
        if (n > (max - min + 1) || max < min) {
            return new int[0];
        }
        int[] result = new int[n];
        //记录随机数的个数
        int count = 0;
        while (count < n) {
            // Math.random() 返回带正号的 double 值，该值的区间为[0.0,1.0)
            // (max=10,min=1,random=0): num=1;
            // (max=10,min=1,random=0.5): num=4;
            int num = (int) (Math.random() * (max - min)) + min;
            // 标记是否有重复元素
            boolean flag = true;
            // 遍历整个数组查找重复项
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            // 当前随机数不包含在数组中，添加进数组
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }


}
