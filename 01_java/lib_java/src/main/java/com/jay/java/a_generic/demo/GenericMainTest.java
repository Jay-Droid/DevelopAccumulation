package com.jay.java.a_generic.demo;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 演示Java范型机制的测试类
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-26 17:51
 */
public class GenericMainTest {

    public static void main(String[] args) throws NoSuchFieldException {
        int demoIndex = 1;
        switch (demoIndex) {
            case 1: {
                //Demo1: 为什么使用泛型，使用泛型的好处？
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
     * Demo1: 为什么使用泛型，使用泛型的好处？
     * - 代码更健壮(类型安全，编译器会检查类型，只要编译期没有警告，那么运行期就不会出现 ClassCastException)
     * - 代码更简洁(不需要强制类型转换)
     * - 代码更灵活，代码复用（适用于多种数据类型执行相同的代码）
     */
    public static void Demo1() {
        System.out.println("-----Demo1-----\n\n");
        System.out.println("----- 测试泛型优势一：类型安全，检查类型提前到编译器阶段，避免了运行时报错 -----");
        System.out.println("嗨，哥们儿，来个B: " + ((B) getListB().get(0)).name);
        // 我要一个B,你却不懂我给我一个C,后果很严重  //ClassCastException
        //System.out.println("嗨，哥们儿，这个B来错了: " + ((B) getListB().get(1)).name);
        //看我怎么治治你，上泛型,不用强转
        System.out.println("嗨，哥们儿，再来个B: " + (getGenericListB().get(0)).name);
        //这回不能捣乱了吧，我都看到了，错误: 不兼容的类型: B无法转换为C
        //System.out.println("嗨，哥们儿，再来个B吧: " + ((C) getGenericListB().get(0)).name);

        System.out.println("----- 测试泛型优势二：代码复用，适用于多种数据类型执行相同的代码-----");
        System.out.println("嗨，哥们儿，数数这里有几个硬币: ");
        addTwoNum(3, 11);
        System.out.println("嗨，哥们儿，那这些硬币总共多少元钱呢: ");
        addTwoFloat(1.4f, 2.7f);
        //每换一种类型都写个方法，太臃肿了，能不能用同一个方法实现多类型都加法运算呢，上泛型
        System.out.println("嗨，哥们儿，你看波操作如何: ");
        GenericMainTest.<Float>addTwoNum(1.4f, 2.7f);
        GenericMainTest.addTwoNum(1d, 2);
        GenericMainTest.<Integer>addTwoNum(1, 2);

    }


    /**
     * 返回一个装有B的容器
     */
    private static List getListB() {
        //当我们将一个对象放入集合中，集合不会记住此对象的类型，
        //当再次从集合中取出此对象时，该对象的编译类型变成了Object类型，
        //因此，取出集合元素时需要人为的强制类型转化到具体的目标类型，
        //且很容易出现“java.lang.ClassCastException”异常。
        List items = new ArrayList(2);
        items.add(new B());
        items.add(new C());
        return items;
    }

    /**
     * 返回一个装有B的容器，已经经过泛型限制
     */
    private static List<B> getGenericListB() {
        List<B> items = new ArrayList<>(1);
        items.add(new B());
        //items.add(new C());//编译错误
        return items;
    }

    /**
     * Int 类型的加法
     */
    private static int addTwoNum(int a, int b) {
        System.out.println(a + "+" + b + "=" + (a + b));
        return a + b;
    }

    /**
     * Float 类型的加法
     */
    private static float addTwoFloat(float a, float b) {
        System.out.println(a + "+" + b + "=" + (a + b));
        return a + b;
    }

    /**
     * 泛形方法实现不同类型的加法操作
     */
    private static <T extends Number> double addTwoNum(T a, T b) {
        System.out.println(a + "+" + b + "=" + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }

    /**
     * Demo2:泛型类
     * 泛型类在初始化时限制了参数类型
     */
    public static void Demo2() {
        System.out.println("-----Demo2-----\n\n");
        System.out.println("----- 测试泛型类 -----");
        GenericClass<String> genericClass = new GenericMainTest.GenericClass<String>();
        genericClass.setData("泛型类测试数据");
        System.out.println(genericClass.getData());

    }

    /**
     * 通配符类型
     * 1，<? extends Parent> 指定了泛型类型的上届
     * 2，<? super Child> 指定了泛型类型的下届
     * 3，<?> 指定了没有限制的泛型类型
     */
    /*
     * 参数化类型： 把类型当参数一样传递 <数据类型>只能是引用类型(泛型的副作用)
     * 举个例子：
     * Plate<T>中的”T”称为类型参数
     * “Plate<T>” 整个成为泛型类型
     * Plate<Banana>中的”Banana”称为实际类型参数
     * “Plate<Banana>”整个称为参数化的类型ParameterizedType
     */

    /*
     * 说一下Java泛型中的PECS原则？
     * PECS即 Producer extends Consumer super
     * 如果你只需要从集合中获得类型T , 使用<? extends T>通配符
     * 如果你只需要将类型T放到集合中, 使用<? super T>通配符
     * 如果既不能存也不能取使用<?>  通配符
     * 如果你既要获取又要放置元素，则不使用任何通配符。例如List<Person>
     */
    private static void Demo7() {
        System.out.println("-----Demo7-----\n\n");
        System.out.println("-----通配符类型 -----");
        //Son 继承自 Father
        Father father = new Father();
        Son son = new Son();
        Grandson grandson = new Grandson();

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
        GenericClass<? extends Father> extendFatherGenericClass = new GenericClass<Father>();
        /**
         * <？extends X>  表示类型的上界，类型参数 T 是 X 的子类，那么可以肯定的说，
         * get方法返回的一定是个 X（不管是X或者X的子类）编译器是可以确定知道的。
         * 但是set方法只知道传入的是个 X，至于具体是 X 的那个子类，不知道。
         * 总结：<？extends X> 限定符主要用于安全地读数据，可以访问 X 及其子类型，并且不能写入非null的数据。
         */

        //<? extends Father> set方法不能写入人类类型

        // setData(T setData) 方法执行 this.data = setData;
        // 该语句是将 setData 的值赋值给 data (setData 和 data 泛型类型为 <? extends Father>) 表示限定 T 为 Father 的子类（包括 Father ）
        // 又因为子类可以安全的转型为父类，如果能找到一个Father类的最下届的子类就可以赋值，
        // 但是在Java中只有最上届的类型Object 不存在最下届的类型，所以没有这样的类型可以赋值给 data
        //extendFatherGenericClass.setData(father); //无法执行
        //extendFatherGenericClass.setData(son); //无法执行


        //<? extends Father> get方法只能读取出 Father 类型或 Father 的父类型

        // T getData() 方法执行 return data; 该语句是返回 data(data 泛型类型为 <? extends Father>) 表示限定 T 为 Father 的子类（包括 Father ）
        // 那什么样的类型可以存放取出来的这个 data 呢？只知道是 Father 的子类就行，但是不能确定是哪个子类，
        // 又因为子类可以安全的转型为父类, 所以作为 Father 所有子类中的父类 Father 以及 Father 的父类型肯定可以接收这个 data
        Father fatherData = extendFatherGenericClass.getData();
        Object fatherDat = extendFatherGenericClass.getData();


        System.out.println("-----<? super Son>表示GenericClass的类型参数的下届是Son -----");
        //<? super Son> 表示GenericClass的类型参数的下界是 Son
        GenericClass<? super Son> supperSonGenericClass = new GenericClass<Son>();
        GenericClass<? super Son> supperSonGenericClass2 = new GenericClass<Father>();
        //GenericClass<? super Son> supperSonGenericClass3 = new GenericClass<Grandson>(); //无法执行
        /**
         * <？super X> 表示类型的下界，类型参数是 X 的父类（包括 X 本身），
         * public T getData() {return data;}
         * get() 方法返回的一定是个 X 的超类，那么到底是哪个超类？不知道，但是可以肯定的说，Object一定是它的Son超类，所以get方法返回Object。编译器是可以确定知道的。
         * public void setData(T data) { this.data = data; }
         * 对于set()方法来说，编译器不知道它需要的确切类型，但是 X 和 X 的子类可以安全的转型为 X。
         * 总结：<？super X> 限定符主要用于安全地写入数据，可以写入 X 及其子类型。
         */

        // <? super Son> set方法只能写入Son本身和它的子类

        // setData(T setData) 方法是执行 this.data = setData;
        // 该语句是将 setData 的值赋值给 data (setData 和 data 泛型类型为 <? super Son>) 表示限定 T 为Son的父类（包括Son）
        // 又因为子类可以安全的转型为父类，所以作为最下届的子类型 Son(Son的子类也可以安全的转为Son类型)的数据类型肯定可以赋值给 data
        // supperSonGenericClass.setData(new Object()); //无法执行
        // supperSonGenericClass.setData(new Father()); //无法执行
        supperSonGenericClass.setData(new Son());
        supperSonGenericClass.setData(new Grandson());


        // <? super Son> get方法只能读取出 Object 类型

        // T getData() 方法执行 return data; 该语句是返回 data(data 泛型类型为 <? super Son>) 表示限定 T 为Son的父类（包括Son）
        // 那什么样的类型可以存放取出来的这个 data 呢？只知道是 Son 的父类就行，但是不能确定是哪个父类，
        // 又因为子类可以安全的转型为父类,所以作为最上届的父类型 Object 肯定可以接收这个data
        Object data = supperSonGenericClass.getData();
        //Son sonData = supperSonGenericClass.getData(); //无法执行

        /**
         * <?> 指定了没有限定的通配符
         */
        System.out.println("----- <?> 指定了没有限定的通配符 -----");
        GenericClass<?> genericClassCommon = new GenericClass<>();

        //<?> set方法不能写入人类类型

        // 因为子类可以安全的转型为父类，如果能找到一个任意一个的最下届的子类就可以赋值，
        // 但是在Java中只有最上届的类型 Object 不存在最下届的类型，所以没有这样的类型可以赋值给 data
        //genericClassCommon.setData(genericClass);
        //genericClassCommon.setData(new Object());

        // <?> get方法只能读取出 Object 类型

        //因为子类可以安全的转型为父类,所以不管什么类型，作为最上届的父类型 Object 肯定可以接收这个data
        Object object = genericClass.getData();

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
     * 2，静态变量或方法不能引用泛型类型变量，但是静态泛型方法是可以的声明的
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
     * Demo9:虚拟机是如何实现泛型的-类型擦除 Java泛型是Java1.5之后才引入的，为了向下兼容。Java采用了C++完全不同的实现思想。Java中的泛型更多的看起来像是编译期用的
     * Java中泛型在运行期是不可见的，会被擦除为它的上级类型。如果是没有限定的泛型参数类型，就会被替换为Object. GenericClass<String>
     * stringGenericClass=new GenericClass<>(); GenericClass<Integer> integerGenericClass=new
     * GenericClass<>();
     *
     * <p>C++中GenericClass<String>和GenericClass<Integer>是两个不同的类型 Java进行了类型擦除之后统一改为GenericClass<Object>
     */
    private static void Demo9() {
        System.out.println("-----Demo8-----\n\n");
        System.out.println("-----获取泛型的参数类型 -----");

        Map<String, String> map = new HashMap<>();
        map.put("Key", "Value");
        System.out.println(map.get("Key"));
        /**
         * 执行后的.class字节码文件 Map<String, String> map = new HashMap(); map.put("Key", "Value");
         * System.out.println((String)map.get("Key"));
         */
        GenericClass<String> stringGenericClass = new GenericClass<>();
        GenericClass<Integer> integerGenericClass = new GenericClass<>();
        System.out.println(stringGenericClass.getClass().toString());
        System.out.println(integerGenericClass.getClass().toString());
        //com.jay.java.Generic.test.GenericMainTest$GenericClass,运行时对Class类相同
        System.out.println(stringGenericClass.getClass() == integerGenericClass.getClass());//true

        // 查看字节码信息
        GenericByteCodeTest2<String> genericByteCodeTest2 = new GenericByteCodeTest2<>();
        genericByteCodeTest2.set("Bytecode");
        System.out.println(genericByteCodeTest2.get());


    }

    /**
     * 孙子类
     */
    private static class Grandson extends Son {
    }


    /**
     * Demo8:获取泛型的参数类型
     * 这里的Type指java.lang.reflect.Type, 是Java中所有类型的公共高级接口, 代表了Java中的所有类型.
     * Type体系中类型的包括：
     * 数组类型(GenericArrayType):数组类型,并不是我们工作中所使用的数组String[] 、byte[]，而是带有泛型的数组，即T[] ；
     * 参数化类型(ParameterizedType): 参数化类型,就是我们平常所用到的泛型List、Map；
     * 类型变量(TypeVariable)：是各种类型变量的公共高级接口
     * 通配符类型(WildcardType):通配符类型, 指的是<?>, <? extends T>等等
     * 原始类型(Class):原始类型, 不仅仅包含我们平常所指的类，还包括枚举、数组、注解等,还有基本类型即int,float,double等；
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

    private static void Demo8() throws NoSuchFieldException {
        System.out.println("-----Demo8-----\n\n");
        System.out.println("-----获取泛型的参数类型 -----");
        GenericMainTest.GenericClass<String> genericType = new GenericMainTest.GenericClass<String>() {
        };
        Type superclass = genericType.getClass().getGenericSuperclass();
        //getActualTypeArguments 返回确切的泛型参数, 如Map<String, Integer>返回[String, Integer]
        Type type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
        System.out.println("-----GenericClass<String>的泛型类型 -----");
        System.out.println(type);//class java.lang.String

        Map<String, String> map;
        //擦除 其实在类常量池里面保留了泛型信息
        Field f = GenericMainTest.class.getDeclaredField("map");
        System.out.println(f.getGenericType());                               // java.util.Map<java.lang.String, java.lang.String>
        System.out.println(f.getGenericType() instanceof ParameterizedType);  // true
        ParameterizedType pType = (ParameterizedType) f.getGenericType();
        System.out.println(pType.getRawType());                               // interface java.util.Map
        for (Type t : pType.getActualTypeArguments()) {
            System.out.println(t);                                         // 打印两遍: class java.lang.String
        }
        System.out.println(pType.getOwnerType());                             // null

    }

    /**
     * 泛型类
     * public class GenericClass<T>{}
     */
    public static class GenericClass<T> {


        //静态变量不能引用泛型类型变量
//        private static T String result;
        //静态方法不能引用泛型类型变量
//        private static T String  getResult() {
//            System.out.println("静态变量或方法不能引用泛型类型变量");
//            return result;
//        }

        private T data;

        public T getData() {
            return data;
        }

        public void setData(T setData) {
            this.data = setData;
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
     * 泛型测试类
     * A是超类
     * BC继承A
     * DE继承B,FG继承C
     * HI继承D
     */
    static class A {

        protected String name;

        A() {
            this.name = this.getClass().getSimpleName();
        }

        protected void printName() {
            System.out.println("name:" + name);
        }

    }

    static class B extends A {

    }

    static class C extends A {

    }


    static class D extends B {

    }

    static class E extends B {

    }

    static class F extends C {

    }

    static class G extends C {

    }


    static class H extends F {

    }

    static class I extends F {

    }

}


