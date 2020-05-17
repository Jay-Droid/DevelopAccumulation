package com.jay.java.设计模式.抽象工厂模式;

/**
 * 创建抽象产品类 ，定义具体产品的公共接口；
 * 模具产品抽象类
 */

abstract class MouldProduct extends Product {
    @Override
    public abstract void Show();
}
