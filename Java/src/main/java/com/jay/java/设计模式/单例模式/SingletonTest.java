package com.jay.java.设计模式.单例模式;

/**
 * 单例模式测试类
 */
public class SingletonTest {
    public static void main(String[] args) {
        //饿汉式-静态变量
//        System.out.println(HungrySingleton.getSingleInstanceByStaticConstant().hashCode());
        //饿汉式-静态代码块
//        System.out.println(HungrySingleton.getSingleInstanceByStaticBlock().hashCode());
        //懒汉式-线程不安全
//        System.out.println(LazySingleton.getLazySingletonByThreadUnsafety().hashCode());
        //懒汉式-线程安全-同步方法
//        System.out.println(LazySingleton.getLazySingletonByThreadSafetyMethod().hashCode());
        //懒汉式-线程安全-同步代码块
//        System.out.println(LazySingleton.getLazySingletonByThreadSafetyBlock().hashCode());
        //懒汉式-线程安全-DCL
//        System.out.println(LazySingleton.getLazySingletonByThreadSafetyDCL().hashCode());
        //静态内部类
//        System.out.println(StaticInnerClassSingleton.getInstance().hashCode());
        //枚举单例
//        System.out.println(EnumSingleton.SINGLEINSTANGE.hashCode());
        MapSingleton.registerInstance("single", new MapInstanceTest());
        //使用容器实现单例模式
        System.out.println(MapSingleton.getInstance("single").hashCode());
    }
}
