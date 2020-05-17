package com.jay.java.设计模式.外观模式;

/**
 * 客户端调用：爷爷使用智能遥控器的时候
 */

public class FacadePattern {

    public static void main(String[] args) {
        //实例化电器类
        SubSystemA_Light light = new SubSystemA_Light();
        SubSystemB_Television television = new SubSystemB_Television();
        SubSystemC_Aircondition airCondition = new SubSystemC_Aircondition();

        //传参
        Facade facade = new Facade(light, television, airCondition);

        //客户端直接与外观对象进行交互
        facade.on();
        System.out.println("可以看电视了");
        facade.off();
        System.out.println("可以睡觉了");
    }
}