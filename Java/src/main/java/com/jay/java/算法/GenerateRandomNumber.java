package com.jay.java.算法;


import java.util.HashSet;
import java.util.Random;

/**
 * Java获取随机数获取制定范围指定个数不重复的随机数
 * <p>
 * JDK中提供的两种方式
 * <p>1.Math类中的random()方法 public static double random() 返回带正号的 double 值，该值大于等于 0.0 且小于 1.0
 * [0.0,1.0)。返回值是一个伪随机选择的数，在该范围内（近似）均匀分布。
 *
 * <p>2.Random类中的 nextInt()方法 public int nextInt() 返回一个随机数,所有 2的32次方个可能 int 值的生成概率（大致）相同。
 *
 * <p>public int nextInt(int n) 返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）和指定值（不包括）之间均匀分布的 int 值。 nextInt
 * 的常规协定是，伪随机地生成并返回指定范围中的一个 int 值。所有可能的 n 个 int 值的生成概率（大致）相同。
 *
 * <p>参数： n - 要返回的随机数的范围。必须为正数。 返回： 随机数生成器序列中 0（包括）和 n（不包括）之间 [0,n) 均匀分布的 int 值。 抛出：
 * IllegalArgumentException - 如果 n 不是正数
 *
 * <p>StackOverFlow总结的的经典的回答:
 *
 * <p>https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-12-19 16:19
 */
public class GenerateRandomNumber {

    public static void main(String[] args) {
        //一、JAVA中生成随机数的方式
        //①
        //int randomNumber = (int) Math.round(Math.random()*(max-min)+min);

        //②
        //long randomNum = System.currentTimeMillis();
        //int randomNumber = (int) randomNum%(max-min)+min;

        //③
        //Random random = new Random();
        //int randomNumber =  random.nextInt(max)%(max-min+1) + min;

//方法一：最简单最易理解的两重循环去重
        int[] reult1 = randomCommon(20, 50, 4);
        for (int i : reult1) {
            System.out.println(i);
        }

//方法二：利用HashSet的特征，只能存放不同的值
//        HashSet<Integer> set = new HashSet<Integer>();
//        randomSet(20, 50, 10, set);
//        for (int j : set) {
//            System.out.println(j);
//        }

//方法三：排除已随机到的数
//        int[] reult2 = randomArray(0, 20, 5);
//      for (int i : reult2) {
//          System.out.println(i);
//      }
    }


    /**
     * 方法一：最简单最易理解的两重循环去重
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    public static int[] randomCommon(int min, int max, int n) {
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

    /**
     * 方法二：利用HashSet的特征，只能存放不同的值
     * 随机指定范围内N个不重复的数
     * 利用HashSet的特征，只能存放不同的值
     *
     * @param min              指定范围最小值
     * @param max              指定范围最大值
     * @param n                随机数个数
     * @param HashSet<Integer> set 随机数结果集
     */
    public static void randomSet(int min, int max, int n, HashSet<Integer> set) {
        if (n > (max - min + 1) || max < min) {
            return;
        }
        for (int i = 0; i < n; i++) {
            // 调用Math.random()方法
            int num = (int) (Math.random() * (max - min)) + min;
            set.add(num);// 将不同的数存入HashSet中
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(min, max, n - setSize, set);// 递归
        }
    }

    /**
     * 方法三：排除已随机到的数
     * 随机指定范围内N个不重复的数
     * 在初始化的无重复待选数组中随机产生一个数放入结果中，
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
     * 然后从len-2里随机产生下一个随机数，如此类推
     *
     * @param max 指定范围最大值
     * @param min 指定范围最小值
     * @param n   随机数个数
     * @return int[] 随机数结果集
     */
    public static int[] randomArray(int min, int max, int n) {
        //min=20 max=40 n=10
        int len = max - min + 1;

        if (max < min || n > len) {
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }
        //source 初始化之后就是min~max(包括首尾) 之间的所有数字的一个数组.[20,21...,10]

        int[] result = new int[n];
        Random random = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            System.out.println("第" + i + "次循环");

            int randomInt = random.nextInt();
            int maxEffectiveArraySubScript = len--;//最大有效数组下标,数组中超过这个下标的值都没有用了...
            //待选数组0到(len-2)随机一个下标    除数(maxEffectiveArraySubScript) 取余 范围为(0~maxEffectiveArraySubScript-1)
            //即(0~maxEffectiveArraySubScript-1) <==> 0到(len-2)
            index = Math.abs(randomInt % maxEffectiveArraySubScript);

            System.out.println("randomInt:" + randomInt + ",原数组最大有效角标:" + maxEffectiveArraySubScript + ",index:" + index);
            System.out.println("原数组有效检索子数组:");
            printArray(source, maxEffectiveArraySubScript);
            System.out.println("");

            //将随机到的数放入结果集
            result[i] = source[index];
            System.out.println("找到的数为:" + result[i]);
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换 ,这样确保"有效检索子数组"中后面没有检索到的数据前移,下一次仍可能随机找到.
            //因为每一次循环 原数组的有效长度都是-1的...
            source[index] = source[len];

            System.out.println("原数组变成:");
            printArray(source);
            System.out.println("");
            System.out.println("目标数组为:");
            printArray(result);
            System.out.println("");
        }
        return result;
    }

    public static void printArray(int[] intArray) {
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + ",");
        }
    }

    public static void printArray(int[] intArray, int maxArrayNum) {
        for (int i = 0; i < maxArrayNum; i++) {
            System.out.print(intArray[i] + ",");
        }
    }
}