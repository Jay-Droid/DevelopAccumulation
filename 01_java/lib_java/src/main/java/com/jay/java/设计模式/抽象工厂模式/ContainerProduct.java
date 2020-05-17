package com.jay.java.设计模式.抽象工厂模式;

/**
 * 创建抽象产品类 ，定义具体产品的公共接口；
 * 容器产品抽象类
 */

abstract class ContainerProduct extends Product {
    @Override
    public abstract void Show();
}
