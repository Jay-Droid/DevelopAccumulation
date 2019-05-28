package com.jay.java.refect.practice.dynamicProxy;

import java.lang.reflect.Method;

/**
 * Author：Jay On 2019/5/18 21:28
 * <p>
 * Description: 动态代理测试类
 */
public class ProxyTest {

    public static void main(String[] args) {
        //A工厂的代理
        //创建A的真实对象
        AFactory aFactory = new AFactoryImpl();
        //创建代理类工厂
        ProxyFactory proxyFactoryA = new ProxyFactory();
        //设置真实对象
        proxyFactoryA.setRealObject(aFactory);
        //通过动态代理创建代理类对象
        AFactory proxyInstanceA = (AFactory) proxyFactoryA.getProxyInstance();
        //执行代理类的对应方法
        proxyInstanceA.doAWork();
        //我在A工厂工作

        //B工厂的代理
        BFactory bFactory = new BFactoryImpl();
        ProxyFactory proxyFactoryB = new ProxyFactory();
        proxyFactoryB.setRealObject(bFactory);
        BFactory proxyInstanceB = (BFactory) proxyFactoryB.getProxyInstance();
        proxyInstanceB.doBWork();
        //我在B工厂工作

        //探究代理类的创建过程
        ProxyUtils.generateClassFile(aFactory.getClass(),
                proxyInstanceA.getClass().getSimpleName());
        ProxyUtils.generateClassFile(bFactory.getClass(),
                proxyInstanceB.getClass().getSimpleName());
        Method[] methods = aFactory.getClass().getMethods();
        for(Method method:methods) {
            //打印代理类的所有方法包括父类
            System.out.println(method.getName());
        }

    }

}
