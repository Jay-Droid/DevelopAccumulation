package com.jay.java.泛型.defineGeneric.genericMethod;

/**
 * Author：Jay On 2019/5/10 10:46
 * <p>
 * Description: 泛型方法
 */
public class GenericMethod1 {

    private static int add(int a, int b) {
        System.out.println(a + "+" + b + "=" + (a + b));
        return a + b;
    }

    private static <T> T genericAdd(T a, T b) {
        System.out.println(a + "+" + b + "=" + a + b);
        return a;
    }

    public static void main(String[] args) {
        GenericMethod1.add(1, 2);
        GenericMethod1.<String>genericAdd("a", "b");
    }
}
