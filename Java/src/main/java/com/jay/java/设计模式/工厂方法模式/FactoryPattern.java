package com.jay.java.设计模式.工厂方法模式;

/**
 * 步骤5：外界通过调用具体工厂类的方法，从而创建不同具体产品类的实例
 * 生产工作流程
 */

public class FactoryPattern {
    public static void main(String[] args){
        //客户要产品A
        FactoryA mFactoryA = new FactoryA();
        mFactoryA.Manufacture().Show();

        //客户要产品B
        FactoryB mFactoryB = new FactoryB();
        mFactoryB.Manufacture().Show();

        //客户要产品C
        FactoryC mFactoryC= new FactoryC();
        mFactoryC.Manufacture().Show();
    }
}
