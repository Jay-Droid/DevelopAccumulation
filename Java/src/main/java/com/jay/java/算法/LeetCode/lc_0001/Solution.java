package com.jay.java.算法.LeetCode.lc_0001;


import java.util.Arrays;
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
        //数组长度5万：5 079 573 ，1 369 711，1 307 594，519 817
        int solutionIndex = 1;

        switch (solutionIndex) {

            case 1: {
                //Solution1: 暴力法
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
     * Solution1: 暴力法
     * 暴力法很简单，遍历每个元素 x，并查找是否存在一个值与 target - x 相等的目标元素。
     *
     * <p>
     * 复杂度分析：
     * 时间复杂度：O(n^2)
     * 对于每个元素，都需要通过遍历数组的其余元素进行计算来寻找目标元素，这将耗费 O(n) 的时间。
     * 因此总的时间复杂度为 O(n^2)。
     *
     * 空间复杂度：O(1)
     * 未使用其它额外的变量存储
     *
     * 实际测试值：
     * 本环境测试：数组长度5：1000 ，数组长度5万：5 079 573
     * LeetCode：时间 22 ms	内存 36.5 MB
     */
    private static void solution1() {
        System.out.println("-----Solution1-----\n");
        //测试数据
        //随机数组
        int[] numArray = getNumArray();
        //获取随机下标
        int[] targetIndex = getTargetIndexFromArray(numArray);
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
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 我们把包含有 n 个元素的列表遍历两次。由于哈希表将查找时间缩短到 O(1) ，所以时间复杂度为O(n)。
     * 空间复杂度：O(n)
     * 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 n个元素
     *
     * 实际测试值：
     * 本环境测试：数组长度5：60000 ，数组长度5万：1 369 711
     * LeetCode：时间 4 ms	内存 36.6 MB
     *
     */
    private static void solution2() {
        System.out.println("-----Solution2-----\n");
        //测试数据
        //随机数组
        int[] numArray = getNumArray();
        //获取随机下标
        int[] targetIndex = getTargetIndexFromArray(numArray);
        //生成target
        int target = numArray[targetIndex[0]] + numArray[targetIndex[1]];
        System.out.println("target:\n" + target);

        //开始时间
        long sTime = System.nanoTime();
        //两遍哈希表题解算法
        int[] resultIndex = new Solution().twoSum2(numArray, target);
        //结束时间
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
        //初始化一个HashMap
        Map<Integer, Integer> numMap = new HashMap<>();
        //将数组中<元素,下标> 转化为Map中的<key,value>
        for (int i = 0; i < numArray.length; i++) {
            numMap.put(numArray[i], i);
        }
        //遍历整个数组，找到每个元素
        for (int firstIndex = 0; firstIndex < numArray.length; firstIndex++) {
//            //方式一 secondIndex
//            //根据两数之和为target尝试获取secondIndex
//            Integer secondIndex = numMap.get(target - numArray[firstIndex]);
//            //如果找到了secondIndex并且secondIndex != firstIndex
//            if (secondIndex != null && secondIndex != firstIndex) {
//                return new int[]{firstIndex, secondIndex};
//            }

            //方式二 secondNum
            //根据两数之和为target获取 secondNum
            int secondNum = target - numArray[firstIndex];
            //如果map中包含secondNum并且 secondNum != firstNum
            if (numMap.containsKey(secondNum) && numMap.get(secondNum) != firstIndex) {
                return new int[]{firstIndex, numMap.get(secondNum)};
            }

        }

        //其它情况
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * Solution3: 一遍哈希表
     * 事实证明，我们可以一次完成。在进行迭代并将元素插入到表中的同时，
     * 我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。
     * 如果它存在，那我们已经找到了对应解，并立即将其返回。
     *
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 我们只遍历了包含有 nn 个元素的列表一次。在表中进行的每次查找只花费 O(1)O(1) 的时间
     * 空间复杂度：O(n)
     * 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 n个元素
     *
     * 实际测试值：
     * 本环境测试：数组长度5：50000 ，数组长度5万：1 307 594
     * LeetCode：时间 3 ms	内存 37.8 MB
     */
    private static void solution3() {
        System.out.println("-----Solution3-----\n\n");
        //测试数据
        //随机数组
        int[] numArray = getNumArray();
        //获取随机下标
        int[] targetIndex = getTargetIndexFromArray(numArray);
        //生成target
        int target = numArray[targetIndex[0]] + numArray[targetIndex[1]];
//        int target = 6;
        System.out.println("target:\n" + target);

        //开始时间
        long sTime = System.nanoTime();
        //一遍哈希表题解算法
        int[] resultIndex = new Solution().twoSum3(numArray, target);
        //结束时间
        long eTime = System.nanoTime();
        System.out.println("nanoTime:\n" + (eTime - sTime));

        printArray("resultIndex:", resultIndex);

    }

    /**
     * @param numArray 给定一个整数数组
     * @param target   给定一个目标值
     * @return 和为目标值的那两个整数的下标
     */
    public int[] twoSum3(int[] numArray, int target) {
        // 条件检查
        if (numArray == null || numArray.length < 2) {
            return new int[2];
        }
        //初始化一个HashMap
        Map<Integer, Integer> numMap = new HashMap<>();
        //将数组中<元素,下标> 转化为Map中的<key,value>
        for (int firstIndex = 0; firstIndex < numArray.length; firstIndex++) {
            //方式一 secondIndex
//            //根据两数之和为target尝试获取secondIndex
//            Integer secondIndex = numMap.get(target - numArray[firstIndex]);
//            //如果找到了secondIndex 此时的secondIndex肯定不会和firstIndex相等的
//            if (secondIndex != null) {
//                return new int[]{firstIndex, secondIndex};
//            }

            //方式二 secondNum
            //根据两数之和为target获取 secondNum
            int secondNum = target - numArray[firstIndex];
            //如果map中包含secondNum
            if (numMap.containsKey(secondNum) && numMap.get(secondNum) != firstIndex) {
                return new int[]{firstIndex, numMap.get(secondNum)};
            }

            numMap.put(numArray[firstIndex], firstIndex);

        }

        //其它情况
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * Solution4: 排序+指针 （该方案不能通过LeetCode，下标错乱）
     * 如果给定的数据是已经排序的,我们可以通过移动指针来寻找和为目标值的那两个元素，使用指针可以避免使用额外的内存开销
     * <p>
     * 复杂度分析：
     * 时间复杂度：O(n)
     * 最坏的情况我们的while循环会遍历所有，所以时间复杂度为O(n)
     * 空间复杂度：O(1)
     * 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 n个元素
     * <p>
     * 实际测试值：
     * 本环境测试：数组长度5：10535 ，数组长度5万：47 321
     */
    private static void solution4() {
        System.out.println("-----Solution4-----\n\n");
        //测试数据
        //随机数组
        int[] numArray = getNumArray();
        //获取随机下标
        int[] targetIndex = getTargetIndexFromArray(numArray);
        //生成target
        int target = numArray[targetIndex[0]] + numArray[targetIndex[1]];
//        int target = 6;
        System.out.println("target:\n" + target);

        //对数组排序
        Arrays.sort(numArray);
        printArray("sorted numArray:", numArray);

        //开始时间
        long sTime = System.nanoTime();
        //排序+指针题解算法
        int[] resultIndex = new Solution().twoSum4(numArray, target);
        //结束时间
        long eTime = System.nanoTime();
        System.out.println("nanoTime:\n" + (eTime - sTime));

        printArray("resultIndex:", resultIndex);

    }

    /**
     * @param numArray 给定一个整数数组
     * @param target   给定一个目标值
     * @return 和为目标值的那两个整数的下标
     */
    public int[] twoSum4(int[] numArray, int target) {
        // 条件检查
        if (numArray == null || numArray.length < 2) {
            return new int[2];
        }

        //左指针初始指向第一个元素
        int left = 0;
        //右指针初始指向最后一个元素
        int right = numArray.length - 1;
        //如果左指针未超过右指针就进入循环
        while ((left > -1 && left < numArray.length) && (right > -1 && right < numArray.length)) {
            //计算当前左右指针所指向的元素的和
            int tempTarget = numArray[left] + numArray[right];
            //当前得到的tempTarget小于target，右指针是最大了，所以需要将左指针向右增大移动
            if (tempTarget < target) {
                left++;
            }
            //当前得到的tempTarget大于target，左指针是最小了，所以需要将右指针向左减小移动
            else if (tempTarget > target) {
                right--;
            }
            //tempTarget等于target，找到了目标值
            else {
                return new int[]{left, right};
            }

        }

        //其它情况
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * 获取数组
     * 数组元素为[0,100]的随机数
     *
     * @return numArray
     */
    private static int[] getNumArray() {
        //[0,100] 随机取5个数
        int[] numArray = getRandomNumArray(0, 1000000, 50000);
//        int[] numArray = getRandomNumArray(0, 100, 5);
//        int[] numArray = new int[]{3, 3};
        printArray("numArray:", numArray);
        return numArray;
    }

    /**
     * 随机获取数组的两个元素的下标
     *
     * @param numArray numArray
     * @return targetIndex
     */
    private static int[] getTargetIndexFromArray(int[] numArray) {
        return getRandomNumArray(0, numArray.length - 1, 2);
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
            System.out.print(numArray[i] + span);
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
