package com.jay.java.泛型.test;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 演示Java范型机制的测试类
 * <p>
 * 泛型，即“参数化类型”。就是将类型由原来的具体的类型参数化，
 * 类似于方法中的变量参数，此时类型也定义成参数形式（可以称之为类型形参），
 * 然后在使用/调用时传入具体的类型（类型实参）。
 * 例如：GenericClass<T>{}
 * <p>
 * 一些常用的泛型类型变量：
 * E：元素（Element），多用于java集合框架
 * K：键（Key）
 * N：数字（Number）
 * T：类型（Type）
 * V：值（Value）
 * S：第二类型
 * U：第三类型
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-26 17:51
 */
public class GenericMainTest {

    public static void main(String[] args) {
        int demoIndex = 9;

        switch (demoIndex) {

            case 1: {
                //Demo1:演示范型的使用以及存在的必要性
                Demo1();
                break;
            }
            case 2: {
                //Demo2:泛型类
                Demo2();
                break;
            }
            case 3: {
                //Demo3:泛型接口
                Demo3();
                break;
            }
            case 4: {
                //Demo4:泛型方法
                Demo4();
                break;
            }
            case 5: {
                //Demo5:泛型中的约束和局限性
                Demo5();
                break;
            }
            case 6: {
                //Demo6:泛型类型继承规则
                Demo6();
                break;
            }
            case 7: {
                //Demo7:通配符类型
                Demo7();
                break;
            }
            case 8: {
                //Demo8:获取泛型的参数类型
                Demo8();
                break;
            }
            case 9: {
                //Demo9:虚拟机是如何实现泛型的-类型擦除
                Demo9();
                break;
            }
        }
    }

    /**
     * Demo1:演示范型的使用以及存在的必要性
     * 范型的好处：
     * 1，适用于多种数据类型执行相同的代码（代码复用）
     * 2，泛型中的类型在使用时指定，不需要强制类型转换（类型安全，编译器会检查类型）
     */
    public static void Demo1() {
        System.out.println("-----Demo1-----\n\n");
        System.out.println("----- 泛型与方法参数类型 -----");
        GenericMainTest.<Integer>add(1, 2);
        GenericMainTest.<Float>add(1f, 2f);
        GenericMainTest.<Double>add(1d, 2d);

        System.out.println("----- 泛型与集合元素类型 -----");
        List list = new ArrayList();
        list.add("A");
        list.add("B");
        list.add(100);
        //1.当我们将一个对象放入集合中，集合不会记住此对象的类型，
        //当再次从集合中取出此对象时，该对象的编译类型变成了Object类型，
        //但其运行时类型任然为其本身类型。
        //2.因此，取出集合元素时需要人为的强制类型转化到具体的目标类型，
        //且很容易出现“java.lang.ClassCastException”异常。
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
//            String value = (String) list.get(i);
//            System.out.println(value);
        }
    }

    /**
     * Int 类型的加法
     */
    private static int add(int a, int b) {
        System.out.println(a + "+" + b + "=" + (a + b));
        return a + b;
    }

    /**
     * 泛形方法实现不同类型的加法操作
     */
    private static <T extends Number> double add(T a, T b) {

        System.out.println(a + "+" + b + "=" + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }

    /**
     * Demo2:泛型类
     * 泛型类在初始化时限制了参数类型
     */
    public static void Demo2() {
        System.out.println("-----Demo2-----\n\n");
        System.out.println("----- 泛型类 -----");
        GenericClass<String> genericClass = new GenericMainTest.GenericClass<>();
        genericClass.setData("泛型类测试");
        System.out.println(genericClass.getData());

    }

    /**
     * 泛型类
     * public class GenericClass<T>{}
     */
    public static class GenericClass<T> {

        private T data;


        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public <T> void show(T t) {
            System.out.println(t.toString());
        }

        /**
         * 不能实例化泛型参数
         * Type parameter 'T' cannot be instantiated directly
         */
        public void setData() {
            System.out.println("不能实例化泛型参数");
//            this.data = new T();
        }

        /**
         * 静态变量或方法不能引用泛型类型变量
         * 'com.jay.java.泛型.restrict.GenericRestrict1.this' cannot be referenced from a static context
         */
        private static /*T*/ String result;

        private static /*T*/ String getResult() {
            System.out.println("静态变量或方法不能引用泛型类型变量");
            return result;
        }

        /**
         * 泛型类的继承关系在使用中同样会受到泛型类型的影响
         */
        void setDataGenericClassFather(GenericClass<Father> father) {

        }

        /**
         * 泛型类的继承关系在使用中同样会受到泛型类型的影响
         * 类型不匹配,可以使用<? extends Parent> 来解决
         */
        void setDataGenericClassExtendsFather(GenericClass<? extends Father> father) {

        }

        static class GenericClassSub<T> extends GenericClass<T> {

        }
    }

    /**
     * Demo3:泛型接口
     * public class ImplGenericInterface1<T> implements GenericInterface<T>{}
     * 1、未传入泛型实参时：在new出类的实例时，需要指定具体类型
     * <p>
     * public class ImplGenericInterface2 implements GenericInterface<String> {}
     * 2、传入泛型实参时： 在new出类的实例时，和普通的类没区别
     */
    public static void Demo3() {
        System.out.println("-----Demo3-----\n\n");
        System.out.println("----- 泛型接口 -----");
        ImplGenericInterface1<String> implGenericInterface1 = new ImplGenericInterface1<>();
        implGenericInterface1.setData("泛型接口测试1");
        System.out.println(implGenericInterface1.getData());

        ImplGenericInterface2 implGenericInterface2 = new ImplGenericInterface2();
        implGenericInterface2.setData("泛型接口测试2");
        System.out.println(implGenericInterface2.getData());
    }


    /**
     * 泛型接口
     */
    public interface GenericInterface<T> {
        T getData();
    }

    /**
     * 泛型接口实现类1
     * public class ImplGenericInterface1<T> implements GenericInterface<T>
     * 1、未传入泛型实参时：在new出类的实例时，需要指定具体类型
     */
    public static class ImplGenericInterface1<T> implements GenericInterface<T> {
        private T data;

        private void setData(T data) {
            this.data = data;
        }

        @Override
        public T getData() {
            return data;
        }
    }

    /**
     * 泛型接口实现类2
     * public class ImplGenericInterface2 implements GenericInterface<String> {}
     * 2、传入泛型实参时： 在new出类的实例时，和普通的类没区别
     */
    public static class ImplGenericInterface2 implements GenericInterface<String> {
        private String data;

        private void setData(String data) {
            this.data = data;
        }

        @Override
        public String getData() {
            return data;
        }
    }

    /**
     * Demo4:泛型方法
     * 定义一个泛型方法： private static <T> T genericAdd(T a, T b) {}
     * 泛型方法的参数类型在使用时指定
     */
    public static void Demo4() {
        System.out.println("-----Demo4-----\n\n");
        System.out.println("----- 泛型方法 -----");
        GenericMainTest.<String>genericAdd("a", "b");
        GenericMainTest.<Integer>genericAdd(1, 2);
        //泛型方法的参数类型在使用时指定,不受泛型类的限制
        GenericClass<Integer> genericClass = new GenericMainTest.GenericClass<>();
        genericClass.<String>show("show");

    }

    /**
     * 泛型方法
     */
    private static <T> T genericAdd(T a, T b) {
        System.out.println(a + "+" + b + "=" + a + b);
        return a;
    }

    /**
     * Demo5: 泛型中的约束和局限性
     * 1，不能实例化泛型变量
     * 2，静态变量或方法不能引用泛型类型变量，但是静态泛型方法是可以的
     * 3，基本类型无法作为泛型类型
     * 4，无法使用instanceof关键字或==判断泛型类的类型
     * 5，泛型类的原生类型与所传递的泛型无关，无论传递什么类型，原生类是一样的
     * 6，泛型数组可以声明但无法实例化
     * 7，泛型类不能继承Exception或者Throwable
     * 8，不能捕获泛型类型限定的异常但可以将泛型限定的异常抛出
     */
    private static void Demo5() {
        System.out.println("-----Demo5-----\n\n");
        GenericClass<Integer> genericClassInteger = new GenericClass<>();
        GenericClass<Integer> genericClassInteger2 = new GenericClass<>();
        GenericClass<String> genericClassString = new GenericClass<>();
        GenericClass genericClassNormal1 = new GenericClass();
        GenericClass genericClassNormal2 = new GenericClass();

        System.out.println("----- 泛型中的约束和局限性 -----");
        GenericClass<String> genericClass = new GenericMainTest.GenericClass<>();
        genericClass.setData();
        genericClass.getResult();

        System.out.println("----- 基本类型无法作为泛型类型 -----");
        //GenericClass<int> genericClassInt = new GenericClass<>();


        /**
         * 无法使用instanceof关键字判断泛型类的类型
         * Illegal generic type for instanceof
         */
        System.out.println("----- 无法使用instanceof关键字判断泛型类的类型 -----");
        //if (genericClassInteger instanceof GenericClass<Integer>) {
        //   return;
        //}

        /**
         * 无法使用“==”判断两个泛型类的实例
         * Operator '==' cannot be applied to this two instance
         */
        System.out.println("----- 无法使用“==”判断两个泛型类的实例 -----");
        //if (genericClassInteger == genericClassString) {
        //   return;
        //}

        /**
         * 泛型类的原生类型与所传递的泛型无关，无论传递什么类型，原生类是一样的
         */
        System.out.println("----- 泛型类的原生类型与所传递的泛型无关，无论传递什么类型，原生类是一样的 -----");
        System.out.println(genericClassNormal1.equals(genericClassNormal2));//false
        System.out.println(genericClassInteger == genericClassInteger2);//false
        System.out.println(genericClassInteger.getClass() == genericClassString.getClass()); //true
        System.out.println(genericClassInteger.getClass());//GenericMainTest$GenericClass
        System.out.println(genericClassString.getClass());//GenericMainTest$GenericClass

        /**
         * 泛型数组可以声明但无法实例化
         * Generic array creation
         */
        System.out.println("-----泛型数组可以声明但无法实例化 -----");
        GenericClass<String>[] genericRestrict1s;
//        genericRestrict1s = new GenericClass<String>[10];
        genericRestrict1s = new GenericClass[10];
        genericRestrict1s[0] = genericClassString;

        /**
         * 泛型类不能继承Exception或者Throwable
         * Generic class may not extend 'java.lang.Throwable'
         */
        System.out.println("-----泛型类不能继承Exception或者Throwable -----");
        //class MyGenericException<T> extends Exception {
        //}
        //class MyGenericThrowable<T> extends Throwable {
        //}

        /**
         * 不能捕获泛型类型限定的异常
         * Cannot catch type parameters
         */
        System.out.println("-----不能捕获泛型类型限定的异常 -----");
        //public <T extends Exception > void getException (T t){
        //    try {

        //   } catch (T e) {
        //  }
        //}

        /**
         *可以将泛型限定的异常抛出
         */
        System.out.println("-----可以将泛型限定的异常抛出 -----");
        //public <T extends Throwable > void getException (T t) throws T {
        //    try {

        //    } catch (Exception e) {
        //        throw t;
        //   }
        //}


    }

    /**
     * Demo6:泛型类型继承规则
     * 1，对于泛型参数是继承关系的泛型类之间是没有继承关系的 例如：GenericClass<Father> 与 GenericClass<Son>
     * 2，泛型类可以继承其它泛型类，例如: public class ArrayList<E> extends AbstractList<E>
     * 3，泛型类的继承关系在使用中同样会受到泛型类型的影响
     */
    private static void Demo6() {
        System.out.println("-----Demo6-----\n\n");
        System.out.println("-----泛型类型继承规则 -----");

        //Son 继承自 Father
        Father father = new Father();
        Son son = new Son();
        GenericClass genericClass = new GenericClass<>();
        GenericClass<Father> genericClassFather = new GenericClass<>();
        GenericClass<Son> GenericClassSon = new GenericClass<>();
        //GenericClassSub继承自GenericClass
        GenericClass.GenericClassSub<Father> GenericClassSubFather = new GenericClass.GenericClassSub<>();
        GenericClass.GenericClassSub<Son> GenericClassSubSon = new GenericClass.GenericClassSub<>();

        /**
         * 对于传递的泛型类型是继承关系的泛型类之间是没有继承关系的
         * GenericInherit<Father> 与GenericInherit<Son> 没有继承关系
         * Incompatible types.
         */
        System.out.println("-----对于传递的泛型类型是继承关系的泛型类之间是没有继承关系的 -----");
        father = new Son();
        //
        //genericClassFather = new GenericClass<Son>();

        /**
         * 泛型类可以继承其它泛型类，例如: public class ArrayList<E> extends AbstractList<E>
         */
        System.out.println("-----泛型类可以继承其它泛型类 -----");
        genericClassFather = new GenericClass.GenericClassSub<Father>();

        /**
         * 泛型类的继承关系在使用中同样会受到泛型类型的影响
         */
        genericClass.setDataGenericClassFather(genericClassFather);
        genericClass.setDataGenericClassFather(GenericClassSubFather);
        //普通类无影响
        genericClass.setDataGenericClassFather(GenericClassSon);
        genericClass.setDataGenericClassFather(GenericClassSubSon);

        System.out.println("-----泛型类的继承关系在使用中同样会受到泛型类型的影响 -----");
        //泛型类中类型不匹配
        //genericClassFather.setDataGenericClassFather(GenericClassSon);
        //genericClassFather.setDataGenericClassFather(GenericClassSubSon);
    }

    /**
     * 父类
     */
    static class Father {
    }

    /**
     * 子类
     */
    private static class Son extends Father {
    }

    /**
     * 通配符类型
     * 1，<? extends Parent> 指定了泛型类型的上届
     * 2，<? super Child> 指定了泛型类型的下届
     * 3，<?> 指定了没有限制的泛型类型
     */
    private static void Demo7() {
        System.out.println("-----Demo7-----\n\n");
        System.out.println("-----通配符类型 -----");
        //Son 继承自 Father
        Father father = new Father();
        Son son = new Son();
        GenericClass genericClass = new GenericClass<>();
        GenericClass<Father> genericClassFather = new GenericClass<>();
        GenericClass<Son> GenericClassSon = new GenericClass<>();
        //GenericClassSub继承自GenericClass
        GenericClass.GenericClassSub<Father> GenericClassSubFather = new GenericClass.GenericClassSub<>();
        GenericClass.GenericClassSub<Son> GenericClassSubSon = new GenericClass.GenericClassSub<>();

        /**
         * <? extends Parent> 指定了泛型类型的上届
         */
        System.out.println("-----<? extends Parent> 指定了泛型类型的上届 -----");
        //泛型类中类型不匹配，类型不匹配,可以使用<? extends Parent> 来解决
        genericClassFather.setDataGenericClassExtendsFather(GenericClassSon);
        genericClassFather.setDataGenericClassExtendsFather(GenericClassSubSon);


        System.out.println("-----<? extends Father> 表示GenericClass的类型参数的上届是Father -----");
        //<? extends Father> 表示GenericClass的类型参数的上届是Father
        GenericClass<? extends Father> extendFatherGenericClass = new GenericClass<>();
        /**
         * 道理很简单，<？extends X>  表示类型的上界，类型参数是X的子类，那么可以肯定的说，
         * get方法返回的一定是个X（不管是X或者X的子类）编译器是可以确定知道的。
         * 但是set方法只知道传入的是个X，至于具体是X的那个子类，不知道。
         * 总结：主要用于安全地访问数据，可以访问X及其子类型，并且不能写入非null的数据。
         */
        //<? extends Father> set方法不能被执行
        //extendFatherGenericClass.setData(father);
        //extendFatherGenericClass.setData(son);

        //<? extends Father>
        father = extendFatherGenericClass.getData();


        System.out.println("-----<? super Son>表示GenericClass的类型参数的下届是Son -----");
        //<? super Son> 表示GenericClass的类型参数的下界是Apple
        GenericClass<? super Son> supperSonGenericClass = new GenericClass<>();
        /**
         * <？ super  X> 表示类型的下界，类型参数是X的超类（包括X本身），
         * 那么可以肯定的说，get方法返回的一定是个X的超类，那么到底是哪个超类？不知道，
         * 但是可以肯定的说，Object一定是它的Son超类，所以get方法返回Object。
         * 编译器是可以确定知道的。对于set方法来说，编译器不知道它需要的确切类型，但是X和X的子类可以安全的转型为X。
         * 总结：主要用于安全地写入数据，可以写入X及其子类型。
         */
        //<? super Son> set方法只能写入Son本身和它的子类
        //supperSonGenericClass.setData(new Father());
        supperSonGenericClass.setData(new Son());

        //<? super Son> get方法只会返回一个Object类型的值。
        Object data = supperSonGenericClass.getData();


        /**
         * <?> 指定了没有限定的通配符
         */
        System.out.println("----- <?> 指定了没有限定的通配符 -----");
        GenericClass<?> genericClassCommon = new GenericClass<>();
        //setData 方法不能被调用， 甚至不能用 Object 调用；
        //genericClassCommon.setData(genericClass);
        //genericClassCommon.setData(new Object());
        //返回值只能赋给 Object
        Object object = genericClass.getData();

    }

    /**
     * Demo8:获取泛型的参数类型
     * 这里的Type指java.lang.reflect.Type, 是Java中所有类型的公共高级接口, 代表了Java中的所有类型.
     * Type体系中类型的包括：
     * 数组类型(GenericArrayType):数组类型,并不是我们工作中所使用的数组String[] 、byte[]，而是带有泛型的数组，即T[] ；
     * 参数化类型(ParameterizedType): 参数化类型,就是我们平常所用到的泛型List、Map；
     * 类型变量(TypeVariable)：是各种类型变量的公共高级接口
     * 通配符类型(WildcardType):通配符类型, 指的是<?>, <? extends T>等等
     * 原始类型(Class):原始类型, 不仅仅包含我们平常所指的类，还包括枚举、数组、注解等；
     * 基本类型(Class):基本类型, 也就是我们所说的java的基本类型，即int,float,double等
     * 以上这些类型都实现Type接口.
     * <p>
     * ParameterizedType:
     * <p>
     * public interface ParameterizedType extends Type {
     * // 返回确切的泛型参数, 如Map<String, Integer>返回[String, Integer]
     * Type[] getActualTypeArguments();
     * <p>
     * //返回当前class或interface声明的类型, 如List<?>返回List
     * Type getRawType();
     * <p>
     * //返回所属类型. 如,当前类型为O<T>.I<S>, 则返回O<T>. 顶级类型将返回null
     * Type getOwnerType();
     * }
     */
    private static void Demo8() {
        System.out.println("-----Demo8-----\n\n");
        System.out.println("-----获取泛型的参数类型 -----");
        GenericMainTest.GenericClass<String> genericType = new GenericMainTest.GenericClass<String>() {
        };
        Type superclass = genericType.getClass().getGenericSuperclass();
        //getActualTypeArguments 返回确切的泛型参数, 如Map<String, Integer>返回[String, Integer]
        Type type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
        System.out.println("-----GenericClass<String>的泛型类型 -----");
        System.out.println(type);//class java.lang.String
    }

    /**
     * Demo9:虚拟机是如何实现泛型的-类型擦除
     * Java泛型是Java1.5之后才引入的，为了向下兼容。Java采用了C++完全不同的实现思想。Java中的泛型更多的看起来像是编译期用的
     * Java中泛型在运行期是不可见的，会被擦除为它的上级类型。如果是没有限定的泛型参数类型，就会被替换为Object.
     * GenericClass<String> stringGenericClass=new GenericClass<>();
     * GenericClass<Integer> integerGenericClass=new GenericClass<>();
     * <p>
     * C++中GenericClass<String>和GenericClass<Integer>是两个不同的类型
     * Java进行了类型擦除之后统一改为GenericClass<Object>
     */
    private static void Demo9() {
        System.out.println("-----Demo8-----\n\n");
        System.out.println("-----获取泛型的参数类型 -----");

        Map<String, String> map = new HashMap<>();
        map.put("Key", "Value");
        System.out.println(map.get("Key"));
        /**
         * 执行后的.class字节码文件
         * Map<String, String> map = new HashMap();
         * map.put("Key", "Value");
         * System.out.println((String)map.get("Key"));
         */
    }


}
