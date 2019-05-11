package com.jay.java.泛型.wildcard;

/**
 * Author：Jay On 2019/5/10 19:51
 * <p>
 * Description: 泛型通配符测试类
 */
public class GenericByWildcard {
    private static void print(GenericClass<Fruit> fruitGenericClass) {
        System.out.println(fruitGenericClass.getData().getColor());
    }

    private static void use() {
        GenericClass<Fruit> fruitGenericClass = new GenericClass<>();
        print(fruitGenericClass);
        GenericClass<Orange> orangeGenericClass = new GenericClass<>();
        //类型不匹配,可以使用<? extends Parent> 来解决
//        print(orangeGenericClass);
    }

    /**
     * <? extends Parent> 指定了泛型类型的上届
     */
    private static void printExtends(GenericClass<? extends Fruit> genericClass) {
        System.out.println(genericClass.getData().getColor());
    }

    public static void useExtend() {
        GenericClass<Fruit> fruitGenericClass = new GenericClass<>();
        printExtends(fruitGenericClass);
        GenericClass<Orange> orangeGenericClass = new GenericClass<>();
        printExtends(orangeGenericClass);

        GenericClass<Food> foodGenericClass = new GenericClass<>();
        //Food是Fruit的父类，超过了泛型上届范围，类型不匹配
//        printExtends(foodGenericClass);

        //表示GenericClass的类型参数的上届是Fruit
        GenericClass<? extends Fruit> extendFruitGenericClass = new GenericClass<>();
        Apple apple = new Apple();
        Fruit fruit = new Fruit();
        /*
         * 道理很简单，？ extends X  表示类型的上界，类型参数是X的子类，那么可以肯定的说，
         * get方法返回的一定是个X（不管是X或者X的子类）编译器是可以确定知道的。
         * 但是set方法只知道传入的是个X，至于具体是X的那个子类，不知道。
         * 总结：主要用于安全地访问数据，可以访问X及其子类型，并且不能写入非null的数据。
         */
//        extendFruitGenericClass.setData(apple);
//        extendFruitGenericClass.setData(fruit);

        fruit = extendFruitGenericClass.getData();

    }

    /**
     * <? super Child> 指定了泛型类型的下届
     */
    public static void printSuper(GenericClass<? super Apple> genericClass) {
        System.out.println(genericClass.getData());
    }

    public static void useSuper() {
        GenericClass<Food> foodGenericClass = new GenericClass<>();
        printSuper(foodGenericClass);

        GenericClass<Fruit> fruitGenericClass = new GenericClass<>();
        printSuper(fruitGenericClass);

        GenericClass<Apple> appleGenericClass = new GenericClass<>();
        printSuper(appleGenericClass);

        GenericClass<HongFuShiApple> hongFuShiAppleGenericClass = new GenericClass<>();
        // HongFuShiApple 是Apple的子类，达不到泛型下届，类型不匹配
//        printSuper(hongFuShiAppleGenericClass);

        GenericClass<Orange> orangeGenericClass = new GenericClass<>();
        // Orange和Apple是兄弟关系，没有继承关系，类型不匹配
//        printSuper(orangeGenericClass);

        //表示GenericClass的类型参数的下界是Apple
        GenericClass<? super Apple> supperAppleGenericClass = new GenericClass<>();
        supperAppleGenericClass.setData(new Apple());
        supperAppleGenericClass.setData(new HongFuShiApple());
        /*
         * ？ super  X  表示类型的下界，类型参数是X的超类（包括X本身），
         * 那么可以肯定的说，get方法返回的一定是个X的超类，那么到底是哪个超类？不知道，
         * 但是可以肯定的说，Object一定是它的超类，所以get方法返回Object。
         * 编译器是可以确定知道的。对于set方法来说，编译器不知道它需要的确切类型，但是X和X的子类可以安全的转型为X。
         * 总结：主要用于安全地写入数据，可以写入X及其子类型。
         */
//        supperAppleGenericClass.setData(new Fruit());

        //get方法只会返回一个Object类型的值。
        Object data = supperAppleGenericClass.getData();
    }

    /**
     * <?> 指定了没有限定的通配符
     */
    public static void printNonLimit(GenericClass<?> genericClass) {
        System.out.println(genericClass.getData());
    }

    public static void useNonLimit() {
        GenericClass<Food> foodGenericClass = new GenericClass<>();
        printNonLimit(foodGenericClass);
        GenericClass<Fruit> fruitGenericClass = new GenericClass<>();
        printNonLimit(fruitGenericClass);
        GenericClass<Apple> appleGenericClass = new GenericClass<>();
        printNonLimit(appleGenericClass);

        GenericClass<?> genericClass = new GenericClass<>();
        //setData 方法不能被调用， 甚至不能用 Object 调用；
//        genericClass.setData(foodGenericClass);
//        genericClass.setData(new Object());
        //返回值只能赋给 Object
        Object object = genericClass.getData();

    }

}
