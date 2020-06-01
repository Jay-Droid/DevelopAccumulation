package com.jay.java.a_generic.demo.type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ParameterizedTypeTest
 * 参考：https://cloud.tencent.com/developer/article/1121266
 * @author wangxuejie
 * @version 1.0
 * @date 2020/5/18
 */
public class ParameterizedTypeTest<T> {

    private List<T> list = new ArrayList<>();
    private List<T>[] listArray = null;
    private Set<T> set = null;
    private ClassTest classTest = null;
    private Map<String, Integer> map = new HashMap<>();
    private List<Map<String, Integer>> listMap = null;
    private List<Map<T, T>> listMapGeneric = null;
    private Map.Entry<String, Integer> mapEntry = null;

    /**
     * 参数化类型(ParameterizedType): 就是用了泛型的类，如 List<T> 、Map<String,Integer>；
     */
    public void testParameterizedType() throws NoSuchFieldException {
        System.out.println("----------------------------------- 参数化类型(ParameterizedType)");
        //反射获取成员变量的实例对象
        Field fieldList = ParameterizedTypeTest.class.getDeclaredField("list");
        //获取该属性的泛型类型
        Type typeList = fieldList.getGenericType();
        System.out.println("List<T> 的泛型类型：");
        //获取泛型类型的类名
        System.out.println(typeList.getClass().getName());
        // 运行结果：
        //sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl

        // 在 ParameterizedType 接口中，有3个方法，分别为 getActualTypeArguments()、getRawType()、getOwnerType();
        System.out.println("---- 参数化类型(ParameterizedType#getActualTypeArguments())");
        testGetActualTypeArguments();

        System.out.println("---- 参数化类型(ParameterizedType#getRawType())");
        testGetRawType();

        System.out.println("---- 参数化类型(ParameterizedType#getOwnerType())");
        testGetOwnerType();

    }

    /**
     * Type[] getActualTypeArguments();
     * 获取泛型中的实际类型的对象的数组
     * getActualTypeArguments()方法永远都是脱去最外层的<>
     */
    public void testGetActualTypeArguments() throws NoSuchFieldException {
        getActualTypeArguments("classTest");
        getActualTypeArguments("list");
        getActualTypeArguments("set");
        getActualTypeArguments("map");
        getActualTypeArguments("mapEntry");
        getActualTypeArguments("listArray");
        getActualTypeArguments("listMap");
        getActualTypeArguments("listMapGeneric");
    }

    private void getActualTypeArguments(String fieldName) throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field field = ParameterizedTypeTest.class.getDeclaredField(fieldName);
        //获取该属性的泛型类型
        Type typeField = field.getGenericType();
        System.out.println("===== typeField: "+typeField);
        //将Type类型强转为 ParameterizedType
        if (typeField instanceof ParameterizedType) {
            ParameterizedType parameterizedTypeMap = (ParameterizedType) typeField;
            //获取泛型中的实际类型的对象的数组
            Type[] types = parameterizedTypeMap.getActualTypeArguments();
            System.out.println("fieldName "+fieldName);
            for (Type type : types) {
                System.out.println(type);
                System.out.println(type.getClass().getName());

                // Set<T> set 运行结果：
                // T
                // sun.reflect.generics.reflectiveObjects.TypeVariableImpl //泛型变量类型

                // Map<String, Integer> map 运行结果：
                // class java.lang.String
                // java.lang.Class //原始类型
                // class java.lang.Integer
                // java.lang.Class

                // List<Map<String, Integer>> listMap 运行结果：
                // java.util.Map<java.lang.String, java.lang.Integer>
                // sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl //参数化类型

                // List<Map<T, T>> listMapGeneric 运行结果：
                // java.util.Map<T, T>
                // sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
            }
        }
    }


    /**
     * getRawType(); 获取声明泛型的类或者接口，也就是泛型中<>前面的那个值；
     *
     * Type getOwnerType(); 获取泛型的拥有者，拥有者表示的含义是内部类的父类
     */
    public void testGetRawType() throws NoSuchFieldException {
        getRawType("set");
        getRawType("map");
        getRawType("listMap");
        getRawType("listMapGeneric");
        getRawType("listArray");
    }


    private void getRawType(String fieldName) throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field field = ParameterizedTypeTest.class.getDeclaredField(fieldName);
        //获取该属性的泛型类型
        Type typeField = field.getGenericType();
        //将Type类型强转为 ParameterizedType
        if (typeField instanceof ParameterizedType) {
            ParameterizedType parameterizedTypeMap = (ParameterizedType) typeField;
            //获取声明泛型的类或者接口
            Type type = parameterizedTypeMap.getRawType();
            System.out.println(type);
            System.out.println(type.getClass().getName());
            // 运行结果：
            //interface java.util.Set //
            //java.lang.Class //原始类型
            //interface java.util.Map
            //java.lang.Class
            //interface java.util.List
            //java.lang.Class
            //interface java.util.List
            //java.lang.Class
        }
    }


    /**
     * Type getOwnerType(); 获取泛型的拥有者，拥有者表示的含义是内部类的父类
     */
    public void testGetOwnerType() throws NoSuchFieldException {
        getOwnerType("set");
        getOwnerType("map");
        getOwnerType("listMap");
        getOwnerType("listMapGeneric");
        getOwnerType("listArray");
        getOwnerType("mapEntry");
    }

    private void getOwnerType(String fieldName) throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field field = ParameterizedTypeTest.class.getDeclaredField(fieldName);
        //获取该属性的泛型类型
        Type typeField = field.getGenericType();
        //将Type类型强转为 ParameterizedType
        if (typeField instanceof ParameterizedType) {
            ParameterizedType parameterizedTypeMap = (ParameterizedType) typeField;
            //获取声明泛型的类或者接口
            Type type = parameterizedTypeMap.getOwnerType();
            System.out.println(type); //Map.Entry<String, Integer> mapEntry = null;
            if (type == null) return;
            System.out.println(type.getClass().getName());
            // 运行结果：
            //interface java.util.Map //
            //java.lang.Class

        }
    }

}