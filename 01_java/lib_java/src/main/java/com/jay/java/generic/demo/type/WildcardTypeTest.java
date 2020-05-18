package com.jay.java.generic.demo.type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * WildcardTypeTest
 * https://cloud.tencent.com/developer/article/1121266
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2020/5/18
 */
public class WildcardTypeTest {

    private List<? extends Number> listNum = null;
    private List<? extends String> listStr = null;
    private List<? super Integer> listInt = null;
    private List<? super Double> listDou = null;

    /**
     * 参数化类型(ParameterizedType): 就是用了泛型的类，如 List<T> 、Map<String,Integer>；
     */
    public void testWildcardType() throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field field = WildcardTypeTest.class.getDeclaredField("listNum");
        Type typeField = field.getGenericType();
        //将Type类型强转为 ParameterizedType
        if (typeField instanceof ParameterizedType) {
            ParameterizedType parameterizedTypeMap = (ParameterizedType) typeField;
            //获取泛型中的实际类型的对象的数组
            Type[] types = parameterizedTypeMap.getActualTypeArguments();
            for (Type type : types) {
                System.out.println(type);
                System.out.println(type.getClass().getName());
                // 运行结果
                //? extends java.lang.Number
                //sun.reflect.generics.reflectiveObjects.WildcardTypeImpl
            }
        }

        //在 WildcardType 接口中，有2个方法，分别为 getUpperBounds()、getLowerBounds();
        System.out.println("---- 参数化类型(WildcardType#getUpperBounds())");
        testGetUpperBounds();

        System.out.println("---- 参数化类型(WildcardType#getLowerBounds())");
        testGetLowerBounds();


    }

    /**
     * getUpperBounds()
     * 获取泛型变量的上边界（extends）
     */
    public void testGetUpperBounds() throws NoSuchFieldException {
        getUpperBounds("listNum");
        getUpperBounds("listStr");
        getUpperBounds("listInt");
        getUpperBounds("listDou");
    }

    private void getUpperBounds(String fieldName) throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field field = WildcardTypeTest.class.getDeclaredField(fieldName);
        //获取该属性的泛型类型
        Type typeField = field.getGenericType();
        //将Type类型强转为 ParameterizedType
        if (typeField instanceof ParameterizedType) {
            ParameterizedType parameterizedTypeMap = (ParameterizedType) typeField;
            //获取泛型中的实际类型的对象的数组
            Type[] types = parameterizedTypeMap.getActualTypeArguments();
            for (Type type : types) {
                if (type instanceof WildcardType) {
                    WildcardType wildcardType = (WildcardType) type;
                    Type[] upperBounds = wildcardType.getUpperBounds();
                    for (Type upperBound : upperBounds) {
                        System.out.println(fieldName);
                        System.out.println(upperBound);
                        System.out.println(upperBound.getClass().getName());
                        // 运行结果
                        //listNum
                        //class java.lang.Number
                        //java.lang.Class
                        //listStr
                        //class java.lang.String
                        //java.lang.Class
                        //listInt
                        //class java.lang.Object
                        //java.lang.Class
                        //listDou
                        //class java.lang.Object
                        //java.lang.Class
                    }

                }

            }
        }
    }

    /**
     * getLowerBounds
     * 获取泛型变量的下边界（super）
     */
    public void testGetLowerBounds() throws NoSuchFieldException {
        getLowerBounds("listInt");
        getLowerBounds("listDou");
        getLowerBounds("listNum");
        getLowerBounds("listStr");
    }

    private void getLowerBounds(String fieldName) throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field field = WildcardTypeTest.class.getDeclaredField(fieldName);
        //获取该属性的泛型类型
        Type typeField = field.getGenericType();
        //将Type类型强转为 ParameterizedType
        if (typeField instanceof ParameterizedType) {
            ParameterizedType parameterizedTypeMap = (ParameterizedType) typeField;
            //获取泛型中的实际类型的对象的数组
            Type[] types = parameterizedTypeMap.getActualTypeArguments();
            for (Type type : types) {
                if (type instanceof WildcardType) {
                    WildcardType wildcardType = (WildcardType) type;
                    Type[] lowerBounds = wildcardType.getLowerBounds();
                    for (Type upperBound : lowerBounds) {
                        System.out.println(fieldName);
                        System.out.println(upperBound);
                        System.out.println(upperBound.getClass().getName());
                        // 运行结果
                        //listInt
                        //class java.lang.Integer
                        //java.lang.Class
                        //listDou
                        //class java.lang.Double
                        //java.lang.Class
                    }

                }

            }
        }
    }


}