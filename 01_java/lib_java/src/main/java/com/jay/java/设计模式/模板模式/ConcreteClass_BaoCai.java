package com.jay.java.设计模式.模板模式;

/**
 * 创建具体模板（Concrete Class）,即”手撕包菜“和”蒜蓉炒菜心“的具体步骤
 * 炒手撕包菜的类
 */
public class ConcreteClass_BaoCai extends AbstractClass {

    @Override
    public void pourVegetable() {
        System.out.println("下锅的蔬菜是包菜");
    }

    @Override
    public void pourSauce() {
        System.out.println("下锅的酱料是辣椒");
    }

}
