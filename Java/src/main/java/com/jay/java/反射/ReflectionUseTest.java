package com.jay.java.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author：Jay On 2019/5/18 15:13
 * <p>
 * Description: 反射的使用测试类
 */
public class ReflectionUseTest {

    public static void main(String[] args) {
        //实例化对象的标准用法即“正”
        Person person1 = new Person();
        person1.setName("Jay-Normal");
        System.out.println(person1.getName());

        //通过反射方式实例化
        try {
            //获取Class对象的三种方式
            Class personClass1 = Person.class;
            Class personClass2 = person1.getClass();
            Class personClass3 = Class.forName("com.jay.java.反射.Person");//需要捕获ClassNotFoundException异常
            //通过Class对象实例化Person实例
            Person person2 = (Person) personClass3.newInstance();//需要捕获IllegalAccessException和InstantiationException异常
            person2.setName("Jay-Reflect-Class");
            System.out.println(person2.getName());
            //反射获取构造器，通过构造器获取类实例
            Constructor<Person> constructor = personClass1.getConstructor(String.class, int.class); //需要捕获NoSuchMethodException异常
            Person person = constructor.newInstance(); //需要捕获InvocationTargetException异常
            person.setName("Jay-Reflect-Constructor");
            System.out.println(person.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
