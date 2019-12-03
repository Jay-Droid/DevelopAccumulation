package com.jay.java.DynamicProxy.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import sun.misc.ProxyGenerator;

/**
 * 演示动态代理的测试类
 * 定义：
 * 代理模式给某一个对象提供一个代理对象，并由代理对象控制对原对象的引用。
 * <p>
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

        int demoIndex = 3;

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
                //Demo3:JDK动态代理原理分析
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
     * <p>
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
        //将被代理对象传入代理生成工厂类
        DynamicProxySubjectFactory proxySubjectFactory = new DynamicProxySubjectFactory(realSubject);
        //获取jdk动态生成的代理类
        Subject proxySubject = (Subject) proxySubjectFactory.getProxyInstance();
        //执行被代理类方法
        proxySubject.request("动态代理类调用了");
    }

    /**
     * 动态代理生成工厂类
     */
    public static class DynamicProxySubjectFactory implements InvocationHandler {

        /**
         * 持有的真实对象
         */
        private Subject realSubject;

        public DynamicProxySubjectFactory(Subject realSubject) {
            this.realSubject = realSubject;
        }

        /**
         * 通过Proxy获得动态代理对象
         */
        public Object getProxyInstance() {
//
            return Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),
                    realSubject.getClass().getInterfaces(), this);

//            return Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),
//                    new Class<?>[]{realSubject.getClass()}, this);
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

    }

    /**
     * Demo3:JDK动态代理原理分析
     * 通过调试模式我们发现，动态代理里，代理类的类名是这样的：
     * {$Proxy0@505}com.jay.java.动态代理.ProxyMainTest$RealSubject@3c679bde
     * 这个代理类为何是这个名字？它是如何执行被代理对象的相关方法呢？我们在java文件编译后的目录里其实找不到这个名为$Proxy0的class文件的。
     * 观察Proxy.newProxyInstance方法，与创建对象有关的代码主要有：
     * 1,获得代理类的class对象：
     * Class<?> cl = getProxyClass0(loader, intfs);
     * 2,获得代理类的构造器：
     * final Constructor<?> cons = cl.getConstructor(constructorParams);
     * 3,创建代理类的实例
     * return cons.newInstance(new Object[]{h});
     * <p>
     * 看来其中的关键点就是如何获得代理类的class对象，我们进入getProxyClass0方法，进而进入proxyClassCache.get方法，通过这个这个方法所在的类名，我们可以推测，JDK内部使用了某种机制缓存了我们的代理类的class对象，同时get方法接受的参数是被代理类的类加载器和类实现的的接口。
     * 在这个get方法中，除去和缓存相关的操作，同时用到了被代理类的类加载器和类实现的的接口这两个参数的是
     * Object subKey = Objects.requireNonNull(subKeyFactory.apply(key, parameter));
     * Generate the specified proxy class.
     * byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName,interfaces,accessFlags);
     * String proxyName = proxyPkg + proxyClassNamePrefix + num;
     * return defineClass0(loader, proxyName,proxyClassFile, 0, proxyClassFile.length);
     * 而最终生成代理类的class对象是defineClass0方法，但是这个方法是个native方法，所以我们不去也无法深究它，但是通过这个方法的参数我们可以明显看到它接收了上面所生成的byte数组。
     * private static native Class<?> defineClass0(ClassLoader loader, String name, byte[] b, int off, int len);
     */
    private static void Demo3() {
        System.out.println("-----Demo3-----\n\n");
        System.out.println("-----JDK动态代理原理分析-----");
        Subject realSubject = new RealSubject();
        //将被代理对象传入代理生成工厂类
        DynamicProxySubjectFactory proxySubjectFactory = new DynamicProxySubjectFactory(realSubject);
        //获取jdk动态生成的代理类
        Subject proxySubject = (Subject) proxySubjectFactory.getProxyInstance();
        //模拟动态生成代理类字节码文件并输出到本地，
        generateProxyClassFile(realSubject.getClass(), proxySubject.getClass().getSimpleName());
        //获取代理类的方法
        Method[] methods = proxySubject.getClass().getMethods();
        //打印方法名称
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    /**
     * 模拟动态生成代理类并输出到指定路径，路径不能包含中文字符
     * /DevelopNote/Java/build/classes/java/main/com/jay/java/DynamicProxy/$Proxy0.class
     */
    private static void generateProxyClassFile(Class clazz, String proxyName) {
        /*ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags);*/
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, new Class[]{clazz});
        String paths = clazz.getResource(".").getPath();
        System.out.println("生成地址：" + paths);
        System.out.println("代理类命：" + proxyName);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(paths + proxyName + ".class");
            out.write(proxyClassFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *  动态生成的代理类【$Proxy0】的 .class 文件
     *
     * //
     * // Source code recreated from a .class file by IntelliJ IDEA
     * // (powered by Fernflower decompiler)
     * //
     *
     * import com.jay.java.Dynamic.ProxyMainTest.RealSubject;
     * import java.lang.reflect.InvocationHandler;
     * import java.lang.reflect.Method;
     * import java.lang.reflect.Proxy;
     * import java.lang.reflect.UndeclaredThrowableException;
     *
     * public final class $Proxy0 extends Proxy implements RealSubject {
     *     private static Method m1;
     *     private static Method m8;
     *     private static Method m2;
     *     private static Method m3;
     *     private static Method m5;
     *     private static Method m4;
     *     private static Method m7;
     *     private static Method m9;
     *     private static Method m0;
     *     private static Method m6;
     *
     *     public $Proxy0(InvocationHandler var1) throws  {
     *         super(var1);
     *     }
     *
     *     public final boolean equals(Object var1) throws  {
     *         try {
     *             return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
     *         } catch (RuntimeException | Error var3) {
     *             throw var3;
     *         } catch (Throwable var4) {
     *             throw new UndeclaredThrowableException(var4);
     *         }
     *     }
     *
     *     public final void notify() throws  {
     *         try {
     *             super.h.invoke(this, m8, (Object[])null);
     *         } catch (RuntimeException | Error var2) {
     *             throw var2;
     *         } catch (Throwable var3) {
     *             throw new UndeclaredThrowableException(var3);
     *         }
     *     }
     *
     *     public final String toString() throws  {
     *         try {
     *             return (String)super.h.invoke(this, m2, (Object[])null);
     *         } catch (RuntimeException | Error var2) {
     *             throw var2;
     *         } catch (Throwable var3) {
     *             throw new UndeclaredThrowableException(var3);
     *         }
     *     }
     *
     *     public final void request(String var1) throws  {
     *         try {
     *             super.h.invoke(this, m3, new Object[]{var1});
     *         } catch (RuntimeException | Error var3) {
     *             throw var3;
     *         } catch (Throwable var4) {
     *             throw new UndeclaredThrowableException(var4);
     *         }
     *     }
     *
     *     public final void wait(long var1) throws InterruptedException {
     *         try {
     *             super.h.invoke(this, m5, new Object[]{var1});
     *         } catch (RuntimeException | InterruptedException | Error var4) {
     *             throw var4;
     *         } catch (Throwable var5) {
     *             throw new UndeclaredThrowableException(var5);
     *         }
     *     }
     *
     *     public final void wait(long var1, int var3) throws InterruptedException {
     *         try {
     *             super.h.invoke(this, m4, new Object[]{var1, var3});
     *         } catch (RuntimeException | InterruptedException | Error var5) {
     *             throw var5;
     *         } catch (Throwable var6) {
     *             throw new UndeclaredThrowableException(var6);
     *         }
     *     }
     *
     *     public final Class getClass() throws  {
     *         try {
     *             return (Class)super.h.invoke(this, m7, (Object[])null);
     *         } catch (RuntimeException | Error var2) {
     *             throw var2;
     *         } catch (Throwable var3) {
     *             throw new UndeclaredThrowableException(var3);
     *         }
     *     }
     *
     *     public final void notifyAll() throws  {
     *         try {
     *             super.h.invoke(this, m9, (Object[])null);
     *         } catch (RuntimeException | Error var2) {
     *             throw var2;
     *         } catch (Throwable var3) {
     *             throw new UndeclaredThrowableException(var3);
     *         }
     *     }
     *
     *     public final int hashCode() throws  {
     *         try {
     *             return (Integer)super.h.invoke(this, m0, (Object[])null);
     *         } catch (RuntimeException | Error var2) {
     *             throw var2;
     *         } catch (Throwable var3) {
     *             throw new UndeclaredThrowableException(var3);
     *         }
     *     }
     *
     *     public final void wait() throws InterruptedException {
     *         try {
     *             super.h.invoke(this, m6, (Object[])null);
     *         } catch (RuntimeException | InterruptedException | Error var2) {
     *             throw var2;
     *         } catch (Throwable var3) {
     *             throw new UndeclaredThrowableException(var3);
     *         }
     *     }
     *
     *     static {
     *         try {
     *             m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
     *             m8 = Class.forName("com.jay.java.Dynamic.ProxyMainTest$RealSubject").getMethod("notify");
     *             m2 = Class.forName("java.lang.Object").getMethod("toString");
     *             m3 = Class.forName("com.jay.java.Dynamic.ProxyMainTest$RealSubject").getMethod("request", Class.forName("java.lang.String"));
     *             m5 = Class.forName("com.jay.java.Dynamic.ProxyMainTest$RealSubject").getMethod("wait", Long.TYPE);
     *             m4 = Class.forName("com.jay.java.Dynamic.ProxyMainTest$RealSubject").getMethod("wait", Long.TYPE, Integer.TYPE);
     *             m7 = Class.forName("com.jay.java.Dynamic.ProxyMainTest$RealSubject").getMethod("getClass");
     *             m9 = Class.forName("com.jay.java.Dynamic.ProxyMainTest$RealSubject").getMethod("notifyAll");
     *             m0 = Class.forName("java.lang.Object").getMethod("hashCode");
     *             m6 = Class.forName("com.jay.java.Dynamic.ProxyMainTest$RealSubject").getMethod("wait");
     *         } catch (NoSuchMethodException var2) {
     *             throw new NoSuchMethodError(var2.getMessage());
     *         } catch (ClassNotFoundException var3) {
     *             throw new NoClassDefFoundError(var3.getMessage());
     *         }
     *     }
     * }
     */

}
