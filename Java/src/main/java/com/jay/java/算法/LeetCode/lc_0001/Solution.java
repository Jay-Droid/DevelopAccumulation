package com.jay.java.算法.LeetCode.lc_0001;


import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-26 17:51
 */
public class Solution {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * <p>你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * <p>示例:
     * <p>给定 nums = [2, 7, 11, 15], target = 9
     * <p>因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {

        int solutionIndex = 2;

        switch (solutionIndex) {

            case 1: {
                // Solution1: 暴力法
//                for (int i = 0; i < 10; i++) {
//                    solution1();
//                }
                solution1();
                break;
            }
            case 2: {
                //Solution2: 两遍哈希表
                solution2();
                break;
            }
            case 3: {
                //Solution3:
                solution3();
                break;
            }
        }
    }

    /**
     * Solution1: 暴力法
     * 暴力法很简单，遍历每个元素 x，并查找是否存在一个值与 target - x 相等的目标元素。
     *
     * <p>
     * 复杂度分析：
     * 时间复杂度：O(n^2)
     * 对于每个元素，都需要通过遍历数组的其余元素进行计算来寻找目标元素，这将耗费 O(n) 的时间。
     * 因此总的时间复杂度为 O(n^2)。
     * 本环境测试 最低时间在 1000 纳秒左右
     * <p>
     * 空间复杂度：O(1)
     * 未使用其它额外的变量存储
     */
    private static void solution1() {
        System.out.println("-----Solution1-----\n");
        //测试数据
        //随机数组
        int[] numArray = getNumArray();
        //获取随机下标
        int[] targetIndex = getRandomNumArray(0, numArray.length - 1, 2);
        //生成target
        int target = numArray[targetIndex[0]] + numArray[targetIndex[1]];
        System.out.println("target:\n" + target);

        //暴力法测试
        //开始时间纳秒
        long sTime = System.nanoTime();
        //暴力法题解算法
        int[] resultIndex = new Solution().twoSum1(numArray, target);
        //结束时间纳秒
        long eTime = System.nanoTime();
        System.out.println("nanoTime:\n" + (eTime - sTime));

        printArray("resultIndex:", resultIndex);

    }

    /**
     * @param numArray 给定一个整数数组
     * @param target   给定一个目标值
     * @return 和为目标值的那两个整数的下标
     */
    public int[] twoSum1(int[] numArray, int target) {
        //条件检查
        if (numArray == null || numArray.length < 2) {
            return new int[2];
        }
        //数组中的每个元素和其它所有元素都计算一遍
        for (int i = 0; i < numArray.length; i++) {
            //j 从i+1开始可以减少不必要的时间复杂度，也避免重复
            for (int j = i + 1; j < numArray.length; j++) {
                //如果找到了和为目标值的那两个整数
                if (numArray[i] + numArray[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        //其它情况
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * Solution2: 两遍哈希表
     * <p>
     * 为了对运行时间复杂度进行优化，我们需要一种更有效的方法来检查数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     * 保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
     *
     * <p>通过以空间换取速度的方式，我们可以将查找时间从 O(n) 降低到 O(1)。哈希表正是为此目的而构建的，
     * 它支持以 近似恒定的时间进行快速查找。我用“近似”来描述，是因为一旦出现冲突，查找用时可能会退化到 O(n)。
     * 但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为 O(1)。
     *
     * <p>一个简单的实现使用了两次迭代。
     * 在第一次迭代中，我们将每个元素的值和它的索引添加到表中。
     * 在第二次迭代中，我们将检查每个元素所对应的目标元素（target -nums[i]）是否存在于表中。
     * 注意，该目标元素不能是 nums[i]本身！
     *
     * <p>作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-2/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private static void solution2() {
        System.out.println("-----Solution2-----\n");
        //测试数据
        //随机数组
        int[] numArray = getNumArray();
        //获取随机下标
        int[] targetIndex = getRandomNumArray(0, numArray.length - 1, 2);
        //生成target
        int target = numArray[targetIndex[0]] + numArray[targetIndex[1]];
        System.out.println("target:\n" + target);

        //暴力法测试
        //开始时间纳秒
        long sTime = System.nanoTime();
        //暴力法题解算法
        int[] resultIndex = new Solution().twoSum2(numArray, target);
        //结束时间纳秒
        long eTime = System.nanoTime();
        System.out.println("nanoTime:\n" + (eTime - sTime));

        printArray("resultIndex:", resultIndex);

    }

    /**
     * @param numArray 给定一个整数数组
     * @param target   给定一个目标值
     * @return 和为目标值的那两个整数的下标
     */
    public int[] twoSum2(int[] numArray, int target) {
        // 条件检查
        if (numArray == null || numArray.length < 2) {
            return new int[2];
        }
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < numArray.length; i++) {
            numMap.put(numArray[i], i);
        }

        for (int firstIndex = 0; firstIndex < numArray.length; firstIndex++) {
//            Integer secondIndex = numMap.get(target - numArray[firstIndex]);
//            if (secondIndex != null && secondIndex != firstIndex) {
//                return new int[]{firstIndex, secondIndex};
//            }
            int secondNum = target - numArray[firstIndex];
            if (numMap.containsKey(secondNum) && numMap.get(secondNum) != firstIndex) {
                return new int[]{firstIndex, numMap.get(secondNum)};

            }

        }

        //其它情况
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * Solution3:
     */
    private static void solution3() {
        System.out.println("-----Solution3-----\n\n");

    }


    /**
     * 获取数组
     * 数组元素为[0,100]的随机数
     *
     * @return numArray
     */
    private static int[] getNumArray() {
        //[0,100] 随机取5个数
        int[] numArray = getRandomNumArray(0, 100, 5);
        printArray("numArray:", numArray);
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
        for (int i : numArray) {
            System.out.print(i + ", ");
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
