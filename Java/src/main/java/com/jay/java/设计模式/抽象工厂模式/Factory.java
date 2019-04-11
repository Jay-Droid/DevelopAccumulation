package com.jay.java.设计模式.抽象工厂模式;

/**
 * 创建抽象工厂类，定义具体工厂的公共接口
 */
abstract class Factory {
    public abstract Product ManufactureContainer();

    public abstract Product ManufactureMould();
}

