package com.jay.java.设计模式.单例模式;

/**
 * 饿汉式单例模式
 */
public class HungrySingleton {
    /**
     * 饿汉式（静态代码块）
     */
    private static final HungrySingleton hungrySingletonByStaticBlock;

    static {
        hungrySingletonByStaticBlock = new HungrySingleton();
    }

    static HungrySingleton getSingleInstanceByStaticBlock() {
        return hungrySingletonByStaticBlock;
    }

    /**
     * 饿汉式（静态常量）
     */
    private static final HungrySingleton hungrySingletonByStaticConstant = new HungrySingleton();

    static HungrySingleton getSingleInstanceByStaticConstant() {
        return hungrySingletonByStaticConstant;
    }

    private HungrySingleton() {
    }
}
