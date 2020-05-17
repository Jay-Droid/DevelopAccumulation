package com.jay.java.基础相关;

/**
 * Author：Jay On 2019/5/12 11:30
 * <p>
 * Description: String类相关的测试类
 */
public class StringTest {
    public static void main(String[] args) {
        /**
         * String能被继承吗？为什么？
         * public final class String implements Serializable, Comparable<java.lang.String>, CharSequence {}
         * String类是用final修饰的，无法被继承
         */
        String str = "str";

        /**
         String str="a"和String str=new String("a")的区别
         如String num=1,调用的是
         1public static String valueOf(int i) {
         return Integer.toString(i);}
         后者则是调用如下部分：
         public String(String original) {
         this.value = original.value;
         this.hash = original.hash;}
         */
        String str1 = "a";
        String str2 = new String("b");
        String str3 = String.valueOf("c");

        /**
         * String， Stringbuffer， StringBuilder 的区别。
         * String 字符串常量(final修饰，不可被继承)，String是常量，当创建之后即不能更改
         *
         * StringBuffer 字符串变量（线程安全）,其也是final类别的，不允许被继承，
         * 其中的绝大多数方法都进行了同步处理，包括常用的Append方法也做了同步处理(synchronized修饰)。
         * 其自jdk1.0起就已经出现。其toString方法会进行对象缓存，以减少元素复制开销。
         *
         * StringBuilder 字符串变量（非线程安全）其自jdk1.5起开始出现。
         * 与StringBuffer一样都继承和实现了同样的接口和类，方法除了没使用synch修饰以外基本一致，
         * 不同之处在于最后toString的时候，会直接返回一个新对象。
         */
        String str4 = new String("b");
        System.out.println( str4.toString());
        StringBuffer stringBuffer = new StringBuffer("b");
        System.out.println(stringBuffer.toString());
        StringBuilder stringBuilder = new StringBuilder("b");
        System.out.println(stringBuilder.toString());


    }
}
