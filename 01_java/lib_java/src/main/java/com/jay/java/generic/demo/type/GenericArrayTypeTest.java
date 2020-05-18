package com.jay.java.generic.demo.type;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * GenericArrayType
 * https://cloud.tencent.com/developer/article/1121266
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2020/5/18
 */
public class GenericArrayTypeTest<T> {

    private List<T>[] listArray = null;
    private T[] t = null;

    /**
     * 参数化类型(ParameterizedType): 就是用了泛型的类，如 List<T> 、Map<String,Integer>；
     */
    public void testGenericArrayType() throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field fieldList = GenericArrayTypeTest.class.getDeclaredField("listArray");
        //获取该属性的泛型类型
        Type typeList = fieldList.getGenericType();
        //获取泛型类型的类名
        System.out.println(typeList);
        System.out.println(typeList.getClass().getName());

        // listArray 运行结果：
        //java.util.List<T>[]
        //sun.reflect.generics.reflectiveObjects.GenericArrayTypeImpl
        // t 运行结果：
        //T[]
        //sun.reflect.generics.reflectiveObjects.GenericArrayTypeImpl

        // 测试 GenericArrayType#getGenericComponentType()方法
        System.out.println("---- 参数化类型(GenericArrayType#getGenericComponentType())");
        testGetGenericComponentType();


    }

    /**
     * getGenericComponentType()
     * 返回泛型数组中元素的Type类型，即List<String>[] 中的 List<String>（ParameterizedTypeImpl）、T[] 中的T（TypeVariableImpl）；
     * 无论是几维数组，getGenericComponentType()方法都只会脱去最右边的[]，返回剩下的值；
     */
    public void testGetGenericComponentType() throws NoSuchFieldException {
        getActualTypeArguments("listArray");
        getActualTypeArguments("t");
    }

    private void getActualTypeArguments(String fieldName) throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field field = GenericArrayTypeTest.class.getDeclaredField(fieldName);
        //获取该属性的泛型类型
        Type typeField = field.getGenericType();
        //将Type类型强转为 GenericArrayType
        if (typeField instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) typeField;
            Type type = genericArrayType.getGenericComponentType();
            System.out.println(type);
            System.out.println(type.getClass().getName());
            //运行结果：
            //java.util.List<T>
            //sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl //参数化类型
            //T
            //sun.reflect.generics.reflectiveObjects.TypeVariableImpl //泛型变量类型
        }
    }


}