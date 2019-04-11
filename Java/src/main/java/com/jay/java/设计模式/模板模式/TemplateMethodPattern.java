package com.jay.java.设计模式.模板模式;

/**
 * 客户端调用-炒菜了
 */
public class TemplateMethodPattern {

    public static void main(String[] args) {

        //炒 - 手撕包菜
        ConcreteClass_BaoCai baocai = new ConcreteClass_BaoCai();
        baocai.cookProcess();
        System.out.println("--------------------------------------");
        //炒 - 蒜蓉菜心
        ConcreteClass_CaiXin caixin = new ConcreteClass_CaiXin();
        caixin.cookProcess();
    }
}
