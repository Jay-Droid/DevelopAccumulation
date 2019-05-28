package com.jay.java.refect;

/**
 * Author：Jay On 2019/5/18 15:40
 * <p>
 * Description: Person测试类
 */
public class Person {
    public String name;
    private int age;

    public Person() {
        System.out.println("调用了无参构造方法");
    }

    public Person(String name, int age) {
        System.out.println("调用了两个参构造方法");
        this.age = age;
        this.name = name;
    }

    private Person(String name) {
        System.out.println("调用了私有构造方法");
        this.name = name;
    }

    private void privateMethod() {
        System.out.println("调用了私有方法");
    }

    public static void staticMethod() {
        System.out.println("调用了静态方法");
    }

    public String getName() {
        System.out.println("调用了getName()方法");
        return name;
    }

    public void setName(String name) {
        System.out.println("调用了setName()方法");
        this.name = name;
    }

    public int getAge() {
        System.out.println("调用了getAge()方法");
        return age;
    }

    public void setAge(int age) {
        System.out.println("调用了setAge()方法");
        this.age = age;
    }
}
