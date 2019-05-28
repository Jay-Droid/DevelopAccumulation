package com.jay.java.refect.practice.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Author：Jay On 2019/5/18 21:18
 * <p>
 * Description: 用于生产代理对象的工厂类
 */
public class ProxyFactory implements InvocationHandler {

    //持有的真实对象
    private Object realObject;

    public Object getRealObject() {
        return realObject;
    }

    public void setRealObject(Object realObject) {
        this.realObject = realObject;
    }

    public Object getProxyInstance() {
        /**
         public static Object newProxyInstance(
         ClassLoader loader, 真实对象的类加载器
         Class<?>[] interfaces, 真实对象所实现的接口
         InvocationHandler h) InvocationHandler的实例
         */
        return Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        return method.invoke(realObject, args);
    }
}
