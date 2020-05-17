package com.jay.java.设计模式.单例模式;

public class ThreadTest extends Thread {
    @Override
    public void run() {
        super.run();
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
        //使用容器实现单例模式
//        System.out.println(MapSingleton.getInstance("single").hashCode());
    }

    public static void main(String[] args) {
        //使用容器实现单例模式
//        MapSingleton.registerInstance("single", new MapInstanceTest());
        ThreadTest[] threadTests = new ThreadTest[1000];
        for (int i = 0; i < threadTests.length; i++) {
            threadTests[i] = new ThreadTest();
        }

        for (ThreadTest threadTest : threadTests) {
            threadTest.start();
        }
    }

}
