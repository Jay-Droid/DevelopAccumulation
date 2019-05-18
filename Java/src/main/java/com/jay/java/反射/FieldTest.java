package com.jay.java.反射;

import java.lang.reflect.Field;

/**
 * Author：Jay On 2019/5/18 17:35
 * <p>
 * Description: 反射获取成员属性测试类
 */
public class FieldTest {

    public static void testField() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        String className = "com.jay.java.反射.Person";
        Class<Person> personClass = (Class<Person>) Class.forName(className);

        //获取所有字段，不包括父类的字段, getFields获取不到
        Field[] fields = personClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
            //运行结果：
            //private java.lang.String com.jay.java.反射.Person.name
            //private int com.jay.java.反射.Person.age
        }

        //获取指定字段
        Field nameField = personClass.getDeclaredField("name");
        Field ageField = personClass.getDeclaredField("age");
        System.out.println(nameField.getName());
        System.out.println(ageField.getName());
        //运行结果：
        //name
        //age

        //获取指定字段的值
        Person person = new Person("Jay", 18);
        String name = (String) nameField.get(person);
        System.out.println(name);
        //运行结果：
        //调用了两个参构造方法
        //Jay

        //设置指定字段的值
        nameField.set(person, "JayDroid");
        String newName = (String) nameField.get(person);
        System.out.println(newName);
        //运行结果：
        //JayDroid

        //获取私有字段的值,需要设置setAccessible(true)
        //FieldTest can not access a member of class com.jay.java.反射.Person with modifiers "private"
        ageField.setAccessible(true);
        ageField.set(person,28);
        int age = (Integer) ageField.get(person);
        System.out.println(age);
        //运行结果:
        //28

    }

    public static void main(String[] args) {
        try {
            FieldTest.testField();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
