package com.jay.java.generic.demo;


import com.jay.java.generic.demo.type.ClassTest;
import com.jay.java.generic.demo.type.GenericArrayTypeTest;
import com.jay.java.generic.demo.type.ParameterizedTypeTest;
import com.jay.java.generic.demo.type.TypeVariableTest;
import com.jay.java.generic.demo.type.WildcardTypeTest;

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
                //Demo1:泛型的来源-测试Java Type体系
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
     * 原始类型(RawType)(原始类型不仅仅包含我们平常所指的类，还包括枚举、数组、注解等,还有基本类型即int,float,double等
     * 泛型数组类型(GenericArrayType): 表示一种元素类型是参数化类型或者类型变量的数组类型，如 GenericClass []；
     * 参数化类型(ParameterizedType): 就是用了泛型的类，如 List<T>,Map<String,Integer>；
     * 泛型变量(TypeVariable): 是各种类型变量的公共高级接口，如 T,K,V
     * 泛型通配符类型(WildcardType): 通配符类型, 如 <?>, <? extends T> ；
     */
    private static void Demo1() throws NoSuchFieldException {
        // 原始类型(Class)
        ClassTest classTest = new ClassTest();
        classTest.testClass(classTest);
        // 参数化类型(ParameterizedType)
        ParameterizedTypeTest<String> parameterizedTypeTest = new ParameterizedTypeTest<>();
        parameterizedTypeTest.testParameterizedType();
        // 泛型数组类型(GenericArrayType)
        GenericArrayTypeTest<String> genericArrayTypeTest = new GenericArrayTypeTest<>();
        genericArrayTypeTest.testGenericArrayType();
        // 泛型变量(TypeVariable)
        TypeVariableTest<Integer> typeVariableTest = new TypeVariableTest<>();
        typeVariableTest.testTypeVariable();
        // 泛型通配符类型(WildcardType)
        WildcardTypeTest wildcardTypeTest = new WildcardTypeTest();
        wildcardTypeTest.testWildcardType();


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
