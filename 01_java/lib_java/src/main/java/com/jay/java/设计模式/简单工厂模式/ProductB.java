package com.jay.java.设计模式.简单工厂模式;

/**
 * 步骤2. 创建具体产品类（继承抽象产品类），定义生产的具体产品
 * 具体产品类B
 */
class ProductB extends Product {

    @Override
    public void Show() {
        System.out.println("生产出了产品B");
    }
}
