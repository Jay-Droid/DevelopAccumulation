package com.jay.java.设计模式.简单工厂模式;

/**
 * 步骤4. 外界通过调用工厂类的静态方法，传入不同参数从而创建不同具体产品类的实例
 * 工厂产品生产流程
 */
public class SimpleFactoryPattern {

    public static void main(String[] args) {
        Factory mFactory = new Factory();
        //客户要产品A
        try {
            //调用工厂类的静态方法 & 传入不同参数从而创建产品实例
            mFactory.Manufacture("A").Show();
        } catch (NullPointerException e) {
            System.out.println("没有这一类产品");
        }

        //客户要产品B
        try {
            mFactory.Manufacture("B").Show();
        } catch (NullPointerException e) {
            System.out.println("没有这一类产品");
        }

        //客户要产品C
        try {
            mFactory.Manufacture("C").Show();
        } catch (NullPointerException e) {
            System.out.println("没有这一类产品");
        }

        //客户要产品D
        try {
            mFactory.Manufacture("D").Show();
        } catch (NullPointerException e) {
            System.out.println("没有这一类产品");
        }
    }
}
