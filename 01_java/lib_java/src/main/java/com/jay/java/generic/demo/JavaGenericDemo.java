package com.jay.java.generic.demo;


import com.jay.java.generic.demo.type.ParameterizedTypeTest;

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-26 17:51
 */
public class JavaGenericDemo {

    public static void main(String[] args) throws NoSuchFieldException {

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
        }
    }

    /**
     * Demo1: 测试Java-Type体系
     * Type体系中类型的包括：
     * <p>
     * 原始类型(rawType)(原始类型不仅仅包含我们平常所指的类，还包括枚举、数组、注解等,还有基本类型即int,float,double等
     * 泛型数组类型(GenericArrayType): 表示一种元素类型是参数化类型或者类型变量的数组类型，如 GenericClass []；
     * 参数化类型(ParameterizedType): 就是用了泛型的类，如 List 、Map<String,Integer>；
     * 泛型变量(TypeVariable): 是各种类型变量的公共高级接口，如
     * 泛型通配符类型(WildcardType): 通配符类型, 如 <?>, <? extends T> ；
     */
    private static void Demo1() throws NoSuchFieldException {
        System.out.println("---- 参数化类型(ParameterizedType)");
        ParameterizedTypeTest<String> parameterizedTypeTest = new ParameterizedTypeTest<>();
        parameterizedTypeTest.testParameterizedType();
        System.out.println("---- 参数化类型(ParameterizedType#getActualTypeArguments())");
        parameterizedTypeTest.testGetActualTypeArguments();

    }

    /**
     * Demo2:
     */
    private static void Demo2() {

    }

    /**
     * Demo3:
     */
    private static void Demo3() {

    }

    /**
     * Demo4:
     */
    private static void Demo4() {

    }
}
