package com.jay.java.设计模式.抽象工厂模式;

/**
 * 创建具体产品类（继承抽象产品类）， 定义生产的具体产品；
 * 容器产品B类
 */
class ContainerProductB extends ContainerProduct {
    @Override
    public void Show() {
        System.out.println("生产出了容器产品B");
    }
}
