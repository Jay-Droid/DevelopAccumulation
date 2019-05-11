package com.jay.java.算法.LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSumTest {
    /*
    思路一：最直接的思维，两次遍历查询，时间复杂度O(N*N)。
     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            //2 (7,11,15)
            //7 (11,15)
            //11 (15)
            for (int i1 = i + 1; i1 < nums.length; i1++) {
                if (nums[i] + nums[i1] == target) {
                    result[0] = i;
                    result[1] = i1;
                    break;
                }
            }
        }
        return result;
    }

    /*
    思路二：先排序，然后两个指针i,j，i从前开始，j从后开始查找，
    当nums[i]+nums[j]>target时，j--；当nums[i]+nums[j]<target时，i++；
    注意排序后，之前的下标数字已经变化了。时间复杂度O(N*Log2N)
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] label = new int[2];
        int[] tmpArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmpArr[i] = nums[i];
        }
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                label[0] = nums[i];
                label[1] = nums[j];
                break;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        for (int k = 0; k < tmpArr.length; k++) {
            if (tmpArr[k] == label[0]) {
                label[0] = k;
            }
            if (tmpArr[k] == label[1]) {
                label[1] = k;
            }
        }
        return label;
    }

    /*
    思路三：利用空间换时间方式，用hashmap存储数组结构，key为值，value为下标。时间复杂度O(N)。
     */
    public static int[] twoSum3(int[] nums, int target) {
        int[] label = new int[2];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i]) && hashMap.get(target - nums[i]) != i) {
                label[0] = i;
                label[1] = hashMap.get(target - nums[i]);
                break;
            }
        }
        return label;
    }

    public static int[] twoSum33(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {3, 3};
        int target = 6;
//        int[] result = TwoSumTest.twoSum1(nums, target);
//        int[] result = TwoSumTest.twoSum2(nums, target);
//        int[] result = TwoSumTest.twoSum3(nums, target);
        int[] result = TwoSumTest.twoSum33(nums, target);
        System.out.println("[" + result[0] + " " + result[1] + "]");
    }
}
