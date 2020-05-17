package com.jay.java.设计模式.静态代理模式;

/**
 * 客户购买
 */
public class ProxyPattern {

    public static void main(String[] args) {
        Subject proxy = new ProxySubject();
        proxy.buyMac();
    }

}
