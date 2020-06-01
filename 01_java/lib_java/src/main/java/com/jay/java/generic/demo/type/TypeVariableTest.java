package com.jay.java.generic.demo.type;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TypeVariableTest
 * https://cloud.tencent.com/developer/article/1121266
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2020/5/18
 */
public class TypeVariableTest<T extends Number & Serializable & Comparable> {

    private T t = null;

    private List<T> list = null;
    private List<T>[] listArray = null;
    private Set<T> set = null;
    private Map<String, Integer> map = new HashMap<>();
    private List<Map<String, Integer>> listMap = null;
    private List<Map<T, T>> listMapGeneric = null;
    private Map.Entry<String, Integer> mapEntry = null;

    /**
     * 泛型变量(TypeVariable): 是各种类型变量的公共高级接口，如 T,K,V
     */
    public void testTypeVariable() throws NoSuchFieldException {
        System.out.println("----------------------------------- 泛型数组类型(TypeVariableType)");

        //反射获取成员变量的实例对象
        Field field = TypeVariableTest.class.getDeclaredField("t");
        //获取该属性的泛型类型
        Type typeField = field.getGenericType();
        //获取泛型类型的类名
        System.out.println(typeField);
        System.out.println(typeField.getClass().getName());
        // 运行结果：
        //T
        //sun.reflect.generics.reflectiveObjects.TypeVariableImpl


        // 在TypeVariable 接口中有三个成员方法。分别是 getBounds(), getGenericDeclaration(), getName(),
        System.out.println("---- 泛型数组类型(TypeVariable#getBounds())");
        testGetBounds();

        System.out.println("---- 泛型数组类型(TypeVariable#getGenericDeclaration())");
        testGetGenericDeclaration();

        System.out.println("---- 泛型数组类型(TypeVariable#getName())");
        testGetName();

    }

    /**
     * getBounds() 获得该类型变量的上限，也就是泛型中extend右边的值；例如 List<T extends Number> ，Number就是类型变量T的上限；
     * 如果我们只是简单的声明了List<T>（无显式定义extends），那么默认为Object；
     */
    public void testGetBounds() throws NoSuchFieldException {
        getBounds("t");
    }

    private void getBounds(String fieldName) throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field field = TypeVariableTest.class.getDeclaredField(fieldName);
        //获取该属性的泛型类型
        Type typeField = field.getGenericType();
        //将Type类型强转为 ParameterizedType
        if (typeField instanceof TypeVariable) {
            System.out.println(fieldName);
            TypeVariable typeVariable = (TypeVariable) typeField;
            //获取泛型中的实际类型的对象的数组
            Type[] types = typeVariable.getBounds();
            for (Type type : types) {
                System.out.println(type);
                System.out.println(type.getClass().getName());
                // 运行结果：

            }
        }
    }


    /**
     * getGenericDeclaration()
     * 获取声明该类型变量实体，也就是TypeVariableTest<T>中的TypeVariableTest；
     *
     * 说到TypeVariable类，就不得不提及Java-Type体系中另一个比较重要的接口---GenericDeclaration；
     * 含义为：声明类型变量的所有实体的公共接口；也就是说该接口定义了哪些地方可以定义类型变量（泛型）；
     * 通过查看源码发现，GenericDeclaration 下有三个子类，分别为Class、Method、Constructor；
     * 也就是说，我们定义泛型只能在一个类中这3个地方自定义泛型；
     */
    public void testGetGenericDeclaration() throws NoSuchFieldException {
        getGenericDeclaration("t");
        getGenericDeclaration("set");
        getGenericDeclaration("map");
        getGenericDeclaration("listMap");
        getGenericDeclaration("listMapGeneric");
        getGenericDeclaration("listArray");
    }


    private void getGenericDeclaration(String fieldName) throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field field = TypeVariableTest.class.getDeclaredField(fieldName);
        //获取该属性的泛型类型
        Type typeField = field.getGenericType();
        //将Type类型强转为 ParameterizedType
        if (typeField instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) typeField;
            //获取声明该类型变量T的实体
            GenericDeclaration declaration = typeVariable.getGenericDeclaration();
            System.out.println(declaration);
            System.out.println(declaration.getClass().getName());
            // 运行结果：
            //class com.jay.java.generic.demo.type.TypeVariableTest
            //java.lang.Class

        }
    }


    /**
     * getName()
     * 获取类型变量在源码中定义的名称；
     */
    public void testGetName() throws NoSuchFieldException {
        getName("t");

    }

    private void getName(String fieldName) throws NoSuchFieldException {
        //反射获取成员变量的实例对象
        Field field = TypeVariableTest.class.getDeclaredField(fieldName);
        //获取该属性的泛型类型
        Type typeField = field.getGenericType();
        //将Type类型强转为 ParameterizedType
        if (typeField instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) typeField;
            //获取声明该类型变量T的实体
            String name = typeVariable.getName();
            System.out.println(name);
            System.out.println(name.getClass().getName());
            // 运行结果：
            //T
            //java.lang.String

        }
    }

}