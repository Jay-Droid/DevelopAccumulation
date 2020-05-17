package com.jay.java.设计模式.静态代理模式;

/**
 * 创建真实对象类（RealSubject）,即”我“
 */
public class RealSubject implements Subject {
    @Override
    public void buyMac() {
        System.out.println("买一台MAC");
    }
}

