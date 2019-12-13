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

        String request(String data);

        int response(int value);
    }

    public interface Subject2 {

        String request(String data);

        int response(int value);
    }

    /**
     * 真实角色：需要实现抽象角色接口，定义了真实角色所要实现的业务逻辑，以便供代理角色调用。也就是真正的业务逻辑在此。
     */
    public static class RealSubject implements Subject, Subject2 {

        @Override
        public String request(String data) {
            System.out.println("RealSubject-data=" + data);
            return data;
        }

        @Override
        public int response(int value) {
            return value;
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
        public String request(String data) {
            doSthBefore();
            realSubject.request(data);
            doSthAfter();
            return data;
        }

        @Override
        public int response(int value) {
            return value;
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
        //创建真实对象实例
        Subject realSubject = new RealSubject();
        //将真实对象传入代理生产工厂类中
        DynamicProxySubjectFactory proxySubjectFactory = new DynamicProxySubjectFactory(realSubject);
        //获取jdk动态生成的代理类
        Subject proxySubject = (Subject) proxySubjectFactory.getProxyInstance();
        //执行真实对象的方法
        proxySubject.request("动态代理类调用了");
        proxySubject.response(521);
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
            System.out.println("---------------------getProxyInstance-----------------------");
            /**
             * Returns an instance of a proxy class for the specified interfaces * that dispatches method
             * invocations to the specified invocation handler. 返回一个实现了抽象接口的代理对象，该实例将方法调用委托给invocation
             * handler来调用。
             *
             * @param loader the class loader to define the proxy class loader 类加载器用于定义代理类
             * @param interfaces the list of interfaces for the proxy class to implement interfaces
             *        代理类要实现的接口列表
             * @param h the invocation handler to dispatch method invocations to
             *        InvocationHandler委托调用代理对象的方法
             * @return a proxy instance with the specified invocation handler of a * proxy class that is
             *        defined by the specified class loader * and that implements the specified interfaces
             *        返回一个具有包含InvocationHandler的并由指定类加载器定义且实现了指定接口的代理对象
             */
            System.out.println("---getProxyInstance-realSubject.getClass()=");
            System.out.println(realSubject.getClass());//class com.jay.java.DynamicProxy.test.ProxyMainTest$RealSubject
            System.out.println("---getProxyInstance-realSubject.getClass().getClassLoader()=");
            System.out.println(realSubject.getClass().getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2
            System.out.println("---getProxyInstance-realSubject.getClass().getInterfaces()[0]=");
            for (Class<?> anInterface : realSubject.getClass().getInterfaces()) {
                System.out.println(anInterface);//interface com.jay.java.DynamicProxy.test.ProxyMainTest$Subject
            }


            //获取代理对象
            Object proxyInstance = Proxy.newProxyInstance(
                    realSubject.getClass().getClassLoader(),
                    realSubject.getClass().getInterfaces(),
                    this);
            System.out.println("---getProxyInstance-代理对象proxyInstance=");
            System.out.println(proxyInstance.getClass().getName());//com.sun.proxy.$Proxy0
            return proxyInstance;
        }

        /**
         * Processes a method invocation on a proxy instance and returns * the result. This method will
         * be invoked on an invocation handler * when a method is invoked on a proxy instance that it is
         * * associated with.
         *
         * 对代理对象执行方法调用并返回结果。 该方法会被和代理对象关联对InvocationHandler接口对象调用。 如：在代理对象中request方法
         * super.h.invoke(this, m3, new Object[]{var1});
         *
         * @param proxy  调用该方法对代理对象
         * @param method the {@code Method} instance corresponding to * the interface method invoked on
         *               the proxy instance. The declaring * class of the {@code Method} object will be the
         *               interface that * the method was declared in, which may be a superinterface of the * proxy
         *               interface that the proxy class inherits the method through.
         *               与在代理对象上调用的接口方法相对应的Method实例，Method实例是在声明该方法的接口中被声明对，这个接口也可能是代理类实现对代理接口的超接口
         * @param args   an array of objects containing the values of the * arguments passed in the method
         *               invocation on the proxy instance, * or {@code null} if interface method takes no
         *               arguments. * Arguments of primitive types are wrapped in instances of the * appropriate
         *               primitive wrapper class, such as * {@code java.lang.Integer} or {@code
         *               java.lang.Boolean}. 代理对象调用真实对象的方法所需要传递的参数值的对象数组；如果接口方法不接受参数，则为null。
         *               基本类型的参数将被自动装箱为包装类型，例如Integer，Boolean。
         * @return the value to return from the method invocation on the * proxy instance. If the
         * declared return type of the interface * method is a primitive type, then the value
         * returned by * this method must be an instance of the corresponding primitive * wrapper
         * class; otherwise, it must be a type assignable to the * declared return type. If the
         * value returned by this method is * {@code null} and the interface method's return type is
         * * primitive, then a {@code NullPointerException} will be * thrown by the method
         * invocation on the proxy instance. If the * value returned by this method is otherwise not
         * compatible with * the interface method's declared return type as described above, * a
         * {@code ClassCastException} will be thrown by the method * invocation on the proxy
         * instance.
         * 代理实例上的方法调用返回的值。 如果接口方法的声明的返回类型是原始类型，则此方法返回的值必须是对应的原始包装器类的实例；
         * 否则，它必须是可分配给声明的返回类型的类型。
         * 如果此方法返回的值为 null，并且接口方法的返回类型为原始类型，则在代理实例上的method调用将抛出NullPointerException。
         * 如果此方法返回的*值与上述接口方法的声明的返回类型不兼容，则proxyinstance上的调用方法引发ClassCastException。
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // super.h.invoke(this, m3, new Object[]{var1});
            System.out.println("---------------------invoke-----------------------");

            System.out.println("---invoke-代理对象实例proxy=" + proxy.getClass().getSimpleName());//invoke-proxy=$Proxy0
            System.out.println("---invoke-执行的方法method=" + method.getName());//invoke-method=request
            for (Object arg : args) {
                System.out.println("---invoke-方法参数值args[i]=");
                System.out.println(arg);//动态代理类调用了
                System.out.println("---invoke-方法参数类型args[i].getClass().getSimpleName()=");
                System.out.println(arg.getClass().getSimpleName());//String
            }

            //通过动态代理对象方法进行增强
            doSthAfter();
            //反射执行真实对象对应的method
            Object result = method.invoke(realSubject, args);
            doSthBefore();
            System.out.println("---invoke-方法返回值result=" + result);//result=动态代理类调用了
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
