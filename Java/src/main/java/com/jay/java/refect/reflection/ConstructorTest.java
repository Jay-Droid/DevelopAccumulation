package com.jay.java.refect.reflection;

import com.jay.java.refect.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author：Jay On 2019/5/18 16:23
 * <p>
 * Description: 反射获取构造方法测试类
 */
public class ConstructorTest {

    public static void testConstruct() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String className = "com.jay.java.反射.Person";
        Class<Person> personClass = (Class<Person>) Class.forName(className);

        //获取全部构造器实例
        System.out.println("获取全部构造器实例");
        Constructor<Person>[] constructors = (Constructor<Person>[]) personClass.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            System.out.println(constructors[i]);
            //运行结果：
            //获取全部构造器实例
            //public com.jay.java.反射.Person(java.lang.String,int)
            //public com.jay.java.反射.Person()
        }

        //获取某一个构造器实例，需要参数列表
        //int.class -->public Person(String name, int age) {
        System.out.println("获取某一个构造器实例");
        Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
        System.out.println(constructor);
        //运行结果：
        //获取某一个构造器实例
        //public com.jay.java.反射.Person(java.lang.String,int)

        //通过构造器创建对象
        System.out.println("通过反射获取的构造器获取类对象");
        Person person = constructor.newInstance("Jay",18);
        System.out.println(person.getName());
        //运行结果
        //通过反射获取的构造器获取类对象
        //调用了两个参构造方法
        //调用了getName()方法
        //Jay


    }

    public static void main(String[] args) {
        try {
            ConstructorTest.testConstruct();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
