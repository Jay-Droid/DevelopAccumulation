package com.jay.java.设计模式.工厂方法模式;

/**
 * 步骤2. 步骤3： 创建具体产品类（继承抽象产品类）， 定义生产的具体产品；
 * 具体产品类A
 */
class ProductA extends Product {

    @Override
    public void Show() {
        System.out.println("生产出了产品A");
    }
}
