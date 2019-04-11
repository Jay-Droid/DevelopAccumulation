package com.jay.java.设计模式.工厂方法模式;

/**
 * 步骤4：创建具体工厂类（继承抽象工厂类），定义创建对应具体产品实例的方法；
 * 工厂B类 - 生产B类产品
 */
class FactoryB extends Factory {
    @Override
    public Product Manufacture() {
        return new ProductB();
    }
}
