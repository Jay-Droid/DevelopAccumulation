package com.jay.java.refect;

/**
 * Author：Jay On 2019/5/18 15:54
 * <p>
 * Description: 类加载器测试类
 */
public class ClassLoaderTest {

    public static void getClassLoader() {

        //获取一个系统的类加载器(可以获取)
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println("获取一个系统的类加载器: " + classLoader);
        //sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取系统类加载器的父类加载器即扩展类加载器，可以获取
        classLoader = classLoader.getParent();
        System.out.println("获取系统类加载器的父类加载器: " + classLoader);
        //sun.misc.Launcher$ExtClassLoader@232204a1

        //获取扩展类加载器的父类加载器即引导类加载器，不可获取
        classLoader = classLoader.getParent();
        System.out.println("获取扩展类加载器的父类加载器: " + classLoader);
        //null

        //测试当前类的类加载器, 系统类加载器
        try {
            classLoader = Class.forName("com.jay.java.refect.ClassLoaderTest").getClassLoader();
            System.out.println("获取当前类的类加载器: " + classLoader);
            //sun.misc.Launcher$AppClassLoader@18b4aac2
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //测试Object类加载器, 引导类类加载器
        try {
            classLoader = Class.forName("java.lang.Object").getClassLoader();
            System.out.println("Object类的类加载器: " + classLoader);
            //null
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClassLoaderTest.getClassLoader();
    }

}
