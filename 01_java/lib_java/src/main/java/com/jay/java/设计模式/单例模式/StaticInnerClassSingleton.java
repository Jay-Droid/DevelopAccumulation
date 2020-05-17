package com.jay.java.设计模式.单例模式;

/**
 * 静态内部类单例模式
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {
    }

    private static class StaticInnerClass {
        private static final StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return StaticInnerClass.staticInnerClassSingleton;
    }
}
