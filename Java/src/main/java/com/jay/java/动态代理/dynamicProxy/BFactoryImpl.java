package com.jay.java.动态代理.dynamicProxy;

/**
 * Author：Jay On 2019/5/18 21:35
 * <p>
 * Description: B 工厂的实现类
 */
public class BFactoryImpl implements BFactory {
    @Override
    public void doBWork() {
        System.out.println("我在B工厂工作");
    }
}
