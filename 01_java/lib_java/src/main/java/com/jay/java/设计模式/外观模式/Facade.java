package com.jay.java.设计模式.外观模式;

/**
 * 外观类：智能遥控器
 */
public class Facade {

    private SubSystemA_Light light;
    private SubSystemB_Television television;
    private SubSystemC_Aircondition airCondition;

    //传参
    public Facade(SubSystemA_Light light, SubSystemB_Television television, SubSystemC_Aircondition aircondition) {
        this.light = light;
        this.television = television;
        this.airCondition = aircondition;
    }

    //起床后一键开电器
    public void on() {
        System.out.println("起床了");
        light.on();
        television.on();
        airCondition.on();
    }

    //睡觉时一键关电器
    public void off() {
        System.out.println("睡觉了");
        light.off();
        television.off();
        airCondition.off();
    }
}



