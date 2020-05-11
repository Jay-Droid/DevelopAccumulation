package com.jay.java.集合.Map.HashMap;


import java.util.HashMap;

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-26 17:51
 */
public class HashMapMainTest {

    public static void main(String[] args) {

        int demoIndex = 1;

        switch (demoIndex) {

            case 1: {
                //Demo1: HashMap 基本使用
                Demo1();
                break;
            }
            case 2: {
                //Demo2:
                Demo2();
                break;
            }
        }
    }

    /**
     * Demo1: HashMap 基本使用
     */
    private static void Demo1() {
        System.out.println("-----Demo1-----\n\n");
        HashMap<String, String> map = new HashMap<>();
        // 增
        System.out.println("---------- HashMap 增加元素 ---------- ");
        map.put("小明", "荒野行动");
        map.put("小刚", "绝地求生");
        System.out.println(map);
        // 删
        System.out.println("---------- HashMap 删除元素 ---------- ");
        map.remove("小明");
        System.out.println(map);
        // 改
        System.out.println("---------- HashMap 修改元素 ---------- ");
        map.replace("小刚", "小米枪战");
        map.put("小刚", "小米战神");
        System.out.println(map);
        // 查
        System.out.println("---------- HashMap 查找元素 ---------- ");
        String value = map.get("小刚");
        System.out.println(value);
        // HashMap 允许 null value
        System.out.println("---------- HashMap 允许 null value ---------- ");
        map.put("小花", null);
        map.put("小红", null);
        System.out.println(map);
        // HashMap 允许 null key
        System.out.println("---------- HashMap 允许 null key ---------- ");
        map.put(null, "英雄联盟");
        map.put(null, "王者荣耀");
        System.out.println(map);


    }

    /**
     * Demo2:
     */
    private static void Demo2() {
        System.out.println("-----Demo2-----\n\n");

    }

    /**
     * Demo3:
     */
    private static void Demo3() {
        System.out.println("-----Demo3-----\n\n");

    }
}
