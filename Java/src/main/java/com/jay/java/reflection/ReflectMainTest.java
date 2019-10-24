package com.jay.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 演示反射的主入口
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-23 14:28
 */
public class ReflectMainTest {
    /**
     * 为了看清楚Java反射部分代码，所有异常我都最后抛出来给虚拟机处理！
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException {

        int demoIndex = 6;

        switch (demoIndex) {
            case 0: {
                //Demo0.  反射的一般使用
                Demo0();
                break;
            }
            case 1: {
                //Demo1.  通过Java反射机制得到类的包名和类名
                Demo1();
                break;
            }
            case 2: {
                //Demo2.  验证所有的类都是Class类的实例对象
                Demo2();
                break;
            }
            case 3: {
                //Demo3.  通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]，无参构造
                Demo3();
                break;
            }
            case 4: {
                //Demo4:  通过Java反射机制得到一个类的【构造函数】，并实现构造带参实例对象
                Demo4();
                break;
            }
            case 5: {
                //Demo5:  通过Java反射机制操作【成员变量】, set 和 get
                Demo5();
                break;
            }
            case 6: {
                //Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
                Demo6();
                break;
            }
            case 7: {
                //Demo7: 通过Java反射机制调用类中【方法】
                Demo7();
                break;
            }
            case 8: {
                //Demo8: 通过Java反射机制获得【类加载器】
                Demo8();
                break;
            }
        }

    }

    private static void Demo0() {
        //实例化对象的标准用法
        Person person1 = new Person();
        person1.setName("张三");

        //通过反射实例化对象
        Class personClass1 = Person.class;
        Class personClass2 = person1.getClass();
        try {
            //静态内部类的全类名是：Package + outerClass + ”$“+ innerClass
            Class personClass3 = Class.forName("com.jay.java.reflection.ReflectMainTest$Person");
            Person person2 = (Person) personClass3.newInstance();
            person2.setName("李四");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Demo1: 通过Java反射机制得到类的包名和类名
     */
    public static void Demo1() {
        Person person = new Person();
        System.out.println("Demo1: Person类的包名: " + person.getClass().getPackage().getName());
        System.out.println("Demo1: Person类的完整类名: " + person.getClass().getName());

        Child child = new Child();
        System.out.println("Demo1: Child类的包名: " + child.getClass().getPackage().getName());
        System.out.println("Demo1: Child类的完整类名: " + child.getClass().getName());
    }

    /**
     * Demo2: 验证所有的类都是Class类的实例对象
     *
     * @throws ClassNotFoundException
     */
    public static void Demo2() throws ClassNotFoundException {
        //定义两个类型都未知的Class , 设置初值为null, 看看如何给它们赋值成Person类
        Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;

        //写法1, 可能抛出 ClassNotFoundException [多用这个写法]
        class1 = Class.forName(Person.CLASS_NAME);
        System.out.println("--------写法1：Class.forName(class name)");
        System.out.println("Demo2: Class1类的包名: " + class1.getClass().getPackage().getName());
        System.out.println("Demo2: Class1类的完整类名: " + class1.getClass().getName());

        //写法1, 可能抛出 ClassNotFoundException [多用这个写法]
        class1 = Class.forName(Child.CLASS_NAME);
        System.out.println("--------写法1：Class.forName(class name)");
        System.out.println("Demo2: Class1类的包名: " + class1.getClass().getPackage().getName());
        System.out.println("Demo2: Class1类的完整类名: " + class1.getClass().getName());

        //写法2
        class2 = Person.class;
        System.out.println("--------写法2：Person.class");
        System.out.println("Demo2: Class2类的包名: " + class2.getPackage().getName());
        System.out.println("Demo2: Class2类的完整类名: " + class2.getClass().getName());

        //写法3
        Person personInstance = new Person();
        class3 = personInstance.getClass();
        System.out.println("--------写法3： personInstance.getClass()");
        System.out.println("Demo2: Class3类的包名: " + class3.getPackage().getName());
        System.out.println("Demo2: Class3类的完整类名: " + class3.getClass().getName());
    }

    /**
     * Demo3: 通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void Demo3() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> class1 = null;
        Class<?> class2 = null;
        //写法1
        class1 = Class.forName(Person.CLASS_NAME);
        //由于这里不能带参数，所以你要实例化的这个类Person，一定要有无参构造函数
        Person person = (Person) class1.newInstance();
        person.setAge(20);
        person.setName("王五");
        System.out.println("Demo3: " + person.getName() + " : " + person.getAge());

        //写法2
        class2 = Person.class;
        Person person2 = (Person) class2.newInstance();
        person2.setAge(30);
        person2.setName("赵六");
        System.out.println("Demo3: " + person.getName() + " : " + person.getAge());
    }

    /**
     * Demo4: 通过Java反射机制得到一个类的构造函数，并实现创建带参实例对象
     *
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IllegalArgumentException
     */
    public static void Demo4() throws ClassNotFoundException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class<?> class1 = null;
        Person person1 = null;
        Person person2 = null;

        class1 = Class.forName(Person.CLASS_NAME);
        //得到一系列构造函数集合
        Constructor<?>[] constructors = class1.getConstructors();
        System.out.println("-----获取全部Constructor对象-----");
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("-----从构造器集合中获取某一个Constructor 对象，----");
        Constructor<?> constructor2 = constructors[0];
        System.out.println(constructor2);

        System.out.println("-----调用构造器的 newInstance() 方法创建对象-----");
        //集合中构造器的顺序是按照类中的构造器的前后顺序
        person1 = (Person) constructor2.newInstance();
        person1.setAge(30);
        person1.setName("Jay");
        System.out.println("Demo4: person1: " + person1.getName() + " : " + person1.getAge());


        System.out.println("-----获取某一个Constructor 对象，需要参数列表----");
        Constructor<?> constructor
                = class1.getConstructor(String.class, int.class);
        System.out.println(constructor);

        System.out.println("-----调用构造器的 newInstance() 方法创建对象-----");
        person2 = (Person) constructor.newInstance("JayDroid", 20);
        System.out.println("Demo4: person2: " + person2.getName() + " : " + person2.getAge());

    }

    /**
     * Demo5: 通过Java反射机制操作成员变量, set 和 get
     *
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static void Demo5() throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException, InstantiationException, ClassNotFoundException {
        System.out.println("-----Demo5-----\n\n");
        Class<?> class1 = null;
        Class<?> class2 = null;
        class1 = Class.forName(Person.CLASS_NAME);
        class2 = Class.forName(Child.CLASS_NAME);
        Person person = (Person) class1.newInstance();

        System.out.println("-----获取公用和私有的所有字段，但不能获取父类字段-----");
        //getDeclaredFields()返回Class中所有的字段，包括私有字段，不能获取父类的任何字段
        //getFields()只返回公有字段，即有public修饰的字段,包括父类中的所有共有字段
        Field[] fields = class1.getDeclaredFields();
        for (Field field : fields) {
            System.out.print(" " + field.getName());
        }
        System.out.println();

        System.out.println("-----获取指定字段-----");
        Field field = class1.getDeclaredField("name");
        System.out.println("字段名：" + field.getName());

        System.out.println("-----获取指定字段的值-----");
        person.setAge(30);
        person.setName("赵六");
        // 获取name字段的值
        Object val = field.get(person);
        System.out.println(field.getName() + "=" + val);

        System.out.println("-----设置指定对象指定字段的值-----");
        //设置name字段的值
        field.set(person, "Jay");
        System.out.println(field.getName() + "=" + person.getName());

        System.out.println("-----字段是私有的，不管是读值还是写值，都必须先调用setAccessible（true）方法-----");
        //比如Person类中，字段name字段是非私有的，age是私有的
        field = class1.getDeclaredField("age");
        field.setAccessible(true);
        //获取私有属性的值
        Object val2 = field.get(person);
        System.out.println(field.getName() + "=" + val2);
        //设置私有属性的值
        field.set(person, 100);
        System.out.println(field.getName() + "=" + person.getAge());

    }


    /**
     * Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
     *
     * @throws ClassNotFoundException
     */
    public static void Demo6() throws ClassNotFoundException {
        System.out.println("-----Demo6-----\n\n");

        Class<?> class1 = null;
        class1 = Class.forName(Child.CLASS_NAME);

        System.out.println("-----取得父类名称-----");
        Class<?> superClass = class1.getSuperclass();
        System.out.println("Child类的父类名: " + superClass.getName());

        System.out.println("-----取得类中的成员----");
        Field[] fields = class1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("类中的成员: " + fields[i]);
        }

        System.out.println("-----取得类方法----");
        Method[] methods = class1.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("函数名：" + methods[i].getName());
            System.out.println("函数返回类型：" + methods[i].getReturnType());
            System.out.println("函数访问修饰符：" + Modifier.toString(methods[i].getModifiers()));
            System.out.println("函数代码写法： " + methods[i]);
            System.out.println("         -----------------");
        }

        System.out.println("-----取得类实现的接口----");
        //取得类实现的接口,因为接口类也属于Class,所以得到接口中的方法也是一样的方法得到
        Class<?> interfaces[] = class1.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println("实现的接口类名: " + interfaces[i].getName());
            for (int j = 0; j < interfaces[i].getDeclaredMethods().length; j++) {
                System.out.print("函数代码写法： " + methods[i] + ",");
            }
        }

    }

    /**
     * Demo7: 通过Java反射机制调用类方法
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     */
    public static void Demo7() throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> class1 = null;
        class1 = Class.forName("cn.lee.demo.SuperMan");

        System.out.println("Demo7: \n调用无参方法fly()：");
        Method method = class1.getMethod("fly");
        method.invoke(class1.newInstance());

        System.out.println("调用有参方法walk(int m)：");
        method = class1.getMethod("walk", int.class);
        method.invoke(class1.newInstance(), 100);
    }

    /**
     * Demo8: 通过Java反射机制得到类加载器信息
     * <p>
     * 在java中有三种类类加载器。[这段资料网上截取]
     * <p>
     * 1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
     * <p>
     * 2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
     * <p>
     * 3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。
     *
     * @throws ClassNotFoundException
     */
    public static void Demo8() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("cn.lee.demo.SuperMan");
        String nameString = class1.getClassLoader().getClass().getName();

        System.out.println("Demo8: 类加载器类名: " + nameString);
    }


    /**
     * 用于演示反射的测试类
     */
    public static class Person {

        public static final String CLASS_NAME = "com.jay.java.reflection.ReflectMainTest$Person";

        public String name;

        //私有属性
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            System.out.println("我的名字是：" + name);
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
            System.out.println("我的年龄是：" + age);
        }

        public Person() {

        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            System.out.println("我的名字是：" + name + " ，我的年龄是" + age);
        }

        //私有方法
        private void privateMethod() {
            System.out.println("我是私有构造方法");
        }
    }

    public static class Child extends Person implements FunctionInterface {

        public static final String CLASS_NAME = "com.jay.java.reflection.ReflectMainTest$Child";

        Child() {

        }

        private boolean toy;

        public boolean isToy() {
            return toy;
        }

        public void setToy(boolean toy) {
            this.toy = toy;
        }

        public void climb() {
            System.out.println("小孩子在爬");
        }

        @Override
        public void walk(int m) {
            System.out.println("小孩会走了，走了" + m + "米");
        }

        @Override
        public void sing(String song) {
            System.out.println("小孩唱歌了，唱了一首" + song);

        }
    }

    interface FunctionInterface {

        public void walk(int m);

        public void sing(String song);
    }
}
