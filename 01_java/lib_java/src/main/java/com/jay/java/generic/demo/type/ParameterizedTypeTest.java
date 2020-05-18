package com.jay.java.generic.demo.type;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ParameterizedTypeTest
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2020/5/18
 */
public class ParameterizedTypeTest<T> {

    private List<T> list = null;
    private List<T>[] listArray = null;
    private Set<T> set = null;
    private Map<String, Integer> map = new HashMap<>();
    private List<Map<String, Integer>> listMap = null;
    private List<Map<T, T>> listMapGeneric = null;

    /**
     * testParameterizedType
     */
    public void testParameterizedType() throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field fieldList = ParameterizedTypeTest.class.getDeclaredField("list");
        //获取该属性的泛型类型
        Type typeList = fieldList.getGenericType();
        System.out.println("List<T> 的类型：");
        //获取泛型类型的类名
        System.out.println(typeList.getClass().getName());
        // 运行结果：
        //sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl

    }

    /**
     * Type[] getActualTypeArguments();//获取泛型中的实际类型的对象的数组
     * Type getRawType();
     * Type getOwnerType();
     */
    public void testGetActualTypeArguments() throws NoSuchFieldException {
        System.out.println(map.getClass().getName());
        getActualTypeArguments("set");
        getActualTypeArguments("map");
        getActualTypeArguments("listMap");
        getActualTypeArguments("listMapGeneric");
        getActualTypeArguments("listArray");
    }

    private void getActualTypeArguments(String fieldName) throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field field = ParameterizedTypeTest.class.getDeclaredField(fieldName);
        //获取该属性的泛型类型
        Type typeField = field.getGenericType();
        //将Type类型强转为 ParameterizedType
        if (typeField instanceof ParameterizedType) {
            ParameterizedType parameterizedTypeMap = (ParameterizedType) typeField;
            //获取泛型中的实际类型的对象的数组
            Type[] types = parameterizedTypeMap.getActualTypeArguments();
            for (Type type : types) {
                System.out.println(type);
                System.out.println(type.getClass().getName());

                // set 运行结果：
                // T
                // sun.reflect.generics.reflectiveObjects.TypeVariableImpl //泛型变量类型

                // map 运行结果：
                // class java.lang.String
                // java.lang.Class //原始类型
                // class java.lang.Integer
                // java.lang.Class

                // listMap 运行结果：
                // java.util.Map<java.lang.String, java.lang.Integer>
                // sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl //参数化类型

                // listMapGeneric 运行结果：
                // java.util.Map<T, T>
                // sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
            }
        } else if (typeField instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) typeField;
            Type type = genericArrayType.getGenericComponentType();
            System.out.println(type);
            System.out.println(type.getClass().getName());
        }

    }
}