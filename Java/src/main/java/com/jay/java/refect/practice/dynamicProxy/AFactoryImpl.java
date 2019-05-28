package com.jay.java.refect.practice.dynamicProxy;

/**
 * Author：Jay On 2019/5/18 21:35
 * <p>
 * Description: A 工厂的实现类
 */
public class AFactoryImpl implements AFactory {
    @Override
    public void doAWork() {
        System.out.println("我在A工厂工作");
    }
}
