package com.jay.java.设计模式.单例模式;

/**
 * 懒汉式单例模式（线程不安全）
 */
public class LazySingleton {

    private static LazySingleton lazySingleton;

    //volatile 目的禁止JVM的指令重排序，内存屏障
    private static volatile LazySingleton volatileLazySingleton;

    private LazySingleton() {
    }

    /**
     * 懒汉式-线程不安全
     *
     * @return LazySingleton
     */
    public static LazySingleton getLazySingletonByThreadUnsafety() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    /**
     * 懒汉式-线程安全-同步方法
     *
     * @return LazySingleton
     */
    public static synchronized LazySingleton getLazySingletonByThreadSafetyMethod() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    /**
     * 懒汉式-线程安全-同步代码块
     *
     * @return LazySingleton
     */
    public static LazySingleton getLazySingletonByThreadSafetyBlock() {
        synchronized (LazySingleton.class) {
            if (lazySingleton == null) {
                lazySingleton = new LazySingleton();
            }
        }
        return lazySingleton;
    }

    /**
     * 懒汉式-双重检查（DCL）
     *
     * @return LazySingleton
     */
    public static LazySingleton getLazySingletonByThreadSafetyDCL() {
        if (volatileLazySingleton == null) {
            synchronized (LazySingleton.class) {
                if (volatileLazySingleton == null) {
                    //new LazySingleton() 这个操作不是原子操作，意味着JVM在执行的时候它的时候会执行这三步骤
                    //1, 为LazySingleton 这个对象分配内存空间
                    //2, 调用LazySingleton构造方法
                    //3，将引用指向内存空间的地址
                    //由于JVM的指令重排序，所以也有可能导致线程不安全，所以需要为变量添加volatile关键字
                    volatileLazySingleton = new LazySingleton();
                }
            }
        }
        return volatileLazySingleton;
    }

}
