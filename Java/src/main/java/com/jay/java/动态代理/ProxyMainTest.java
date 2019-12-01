package com.jay.java.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 演示动态代理的测试类
 * 定义：
 * 代理模式给某一个对象提供一个代理对象，并由代理对象控制对原对象的引用。
 *
 * 作用：
 * 通过引入代理对象的方式来间接访问目标对象，防止直接访问目标对象给系统带来的不必要复杂性；
 * 通过代理对象对原有的业务增强；
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-31 19:05
 */
public class ProxyMainTest {

    public static void main(String[] args) {

        int demoIndex = 2;

        switch (demoIndex) {

            case 1: {
                //Demo1:静态代理的实现
                Demo1();
                break;
            }
            case 2: {
                //Demo2:JDK动态代理的实现
                Demo2();
                break;
            }
            case 3: {
                //Demo3:
                Demo3();
                break;
            }
        }
    }

    /**
     * Demo1:静态代理的实现
     * 静态代理在使用时,需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者是继承相同父类。
     * 一般来说，被代理对象和代理对象是一对一的关系，当然一个代理对象对应多个被代理对象也是可以的。
     * 静态代理，一对一则会出现时静态代理对象量多、代码量大，从而导致代码复杂，可维护性差的问题，
     * 一对多则代理对象会出现扩展能力差的问题。
     */
    private static void Demo1() {
        System.out.println("-----Demo1-----\n\n");
        System.out.println("-----静态代理的实现-----");
        Subject realSubject = new RealSubject();
        Subject proxySubject = new ProxySubject(realSubject);
        proxySubject.request("静态代理类调用了");

    }

    /**
     * 抽象角色：指代理角色和真实角色对外提供的公共方法，一般为一个接口
     */
    public interface Subject {
        void request(String data);
    }

    /**
     * 真实角色：需要实现抽象角色接口，定义了真实角色所要实现的业务逻辑，以便供代理角色调用。也就是真正的业务逻辑在此。
     */
    public static class RealSubject implements Subject {

        @Override
        public void request(String data) {
            System.out.println("RealSubject-data=" + data);
        }
    }

    /**
     * 代理角色：需要实现抽象角色接口，是真实角色的代理，通过真实角色的业务逻辑方法来实现抽象方法，并可以附加自己的操作。将统一的流程控制都放到代理角色中处理！
     */
    public static class ProxySubject implements Subject {

        private Subject realSubject;

        public ProxySubject(Subject realSubject) {
            this.realSubject = realSubject;
        }

        @Override
        public void request(String data) {
            doSthBefore();
            realSubject.request(data);
            doSthAfter();
        }

        /**
         * 前置处理器
         */
        private void doSthAfter() {
            System.out.println("调用真实对象之后");
        }

        /**
         * 后置处理器
         */
        private void doSthBefore() {
            System.out.println("调用真实对象之前");
        }

    }


    /**
     * Demo2:JDK动态代理的实现
     *
     * 动态代理是指在使用时再创建代理类和实例
     * 优点
     * 只需要1个动态代理类就可以解决创建多个静态代理的问题，避免重复、多余代码更强的灵活性
     * 缺点
     * 效率低，相比静态代理中 直接调用目标对象方法，动态代理则需要先通过Java反射机制 从而 间接调用目标对象方法
     * 应用场景局限，因为 Java 的单继承特性（每个代理类都继承了 Proxy 类），即只能针对接口 创建 代理类，不能针对类创建代理类。
     * 在java的动态代理机制中，有两个重要的类或接口，一个是InvocationHandler接口、另一个则是 Proxy类，这个类和接口是实现我们动态代理所必须用到的。
     * InvocationHandler接口是给动态代理类实现的，负责处理被代理对象的操作的，
     * Proxy是用来创建动态代理类实例对象的，因为只有得到了这个对象我们才能调用那些需要代理的方法。
     */
    private static void Demo2() {
        System.out.println("-----Demo2-----\n\n");
        System.out.println("-----JDK动态代理的实现-----");

        Subject realSubject = new RealSubject();
        DynamicProxySubject proxySubject = new DynamicProxySubject(realSubject);
        proxySubject.invokeSubject("动态代理类调用了");
    }

    /**
     * 动态代理角色
     */
    public static class DynamicProxySubject implements InvocationHandler {

        /**
         * 持有的真实对象
         */
        private Subject realSubject;

        public DynamicProxySubject(Subject realSubject) {
            this.realSubject = realSubject;
        }

        /**
         * 通过Proxy获得动态代理对象
         */
        public Object getProxyInstance() {
            return Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),
                    realSubject.getClass().getInterfaces(), this);
        }

        /**
         * 通过动态代理对象方法进行增强
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            doSthAfter();
            Object result = method.invoke(realSubject, args);
            doSthBefore();
            return result;
        }

        /**
         * 前置处理
         */
        private void doSthAfter() {
            System.out.println("调用真实对象之后");
        }

        /**
         * 后置处理
         */
        private void doSthBefore() {
            System.out.println("调用真实对象之前");
        }

        /**
         * 获取代理对象并执行
         */
        void invokeSubject(String data) {
            ((Subject) getProxyInstance()).request(data);
        }
    }

    /**
     * Demo3:
     */
    private static void Demo3() {
        System.out.println("-----Demo3-----\n\n");

    }
}
