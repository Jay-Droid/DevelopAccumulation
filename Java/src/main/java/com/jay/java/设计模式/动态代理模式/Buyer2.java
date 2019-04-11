package com.jay.java.设计模式.动态代理模式;

// 小何，真正的想买iPhone的对象 = 目标对象 = 被代理的对象
// 实现抽象目标对象的接口
public class Buyer2 implements Subject  {

    @Override
    public void buybuybuy() {
        System.out.println("小何要买iPhone");
    }

}
