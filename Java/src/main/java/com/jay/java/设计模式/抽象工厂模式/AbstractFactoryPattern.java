package com.jay.java.设计模式.抽象工厂模式;

/**
 * 客户端通过实例化具体的工厂类，并调用其创建不同目标产品的方法创建不同具体产品类的实例
 * 生产工作流程
 */
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        FactoryA mFactoryA = new FactoryA();
        FactoryB mFactoryB = new FactoryB();

        //A厂当地客户需要容器产品A
        mFactoryA.ManufactureContainer().Show();
        //A厂当地客户需要模具产品A
        mFactoryA.ManufactureMould().Show();

        //B厂当地客户需要容器产品B
        mFactoryB.ManufactureContainer().Show();
        //B厂当地客户需要模具产品B
        mFactoryB.ManufactureMould().Show();

    }
}
