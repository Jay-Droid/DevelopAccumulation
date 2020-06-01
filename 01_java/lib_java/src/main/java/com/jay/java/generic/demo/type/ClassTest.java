package com.jay.java.generic.demo.type;

import io.reactivex.annotations.Nullable;

/**
 * WildcardTypeTest
 * https://cloud.tencent.com/developer/article/
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2020/5/18
 */
public class ClassTest {

    private enum EnumTest {}

    private int[] array = {1};

    @Nullable
    private String annotation = "annotation";


    /**
     * 原始类型不仅仅包含我们平常所指的类，还包括枚举、数组、注解等,还有基本类型即int,float,double等
     *
     * @param classTest
     */
    public void testClass(ClassTest classTest) {
        System.out.println("----------------------------------- 原始类型(Class)");
        Class a = classTest.getClass();
        Class b = EnumTest.class;
        System.out.println(a);
        System.out.println(b);
        System.out.println(a == b);
        System.out.println("EnumTest");
        System.out.println(EnumTest.class.getName());
        System.out.println("int");
        System.out.println(int.class.getName());
        System.out.println("array");
        System.out.println(array.getClass().getName());
    }
}