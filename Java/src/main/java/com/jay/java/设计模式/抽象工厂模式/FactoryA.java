package com.jay.java.设计模式.抽象工厂模式;

/**
 * 创建具体工厂类（继承抽象工厂类），定义创建对应具体产品实例的方法；
 * A厂 - 生产模具+容器产品
 */
class FactoryA extends Factory {

    @Override
    public Product ManufactureContainer() {
        return new ContainerProductA();
    }

    @Override
    public Product ManufactureMould() {
        return new MouldProductA();
    }
}

