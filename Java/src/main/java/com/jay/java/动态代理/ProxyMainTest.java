package com.jay.java.动态代理;

/**
 * 演示动态代理的测试类
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-31 19:05
 */
public class ProxyMainTest {

    public static void main(String[] args) {

        int demoIndex = 1;

        switch (demoIndex) {

            case 1: {
                //Demo1:
                Demo1();
                break;
            }
            case 2: {
                //Demo2:
                Demo2();
                break;
            }
            case 3: {
                //Demo3:
                Demo3();
                break;
            }
        }
    }

    /**
     * Demo1:
     */
    private static void Demo1() {
        System.out.println("-----Demo1-----\n\n");

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
