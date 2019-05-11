package com.jay.java.泛型.defineGeneric.genericInterface;

/**
 * Author：Jay On 2019/5/9 17:01
 * <p>
 * Description: 泛型接口实现类-指定具体类型实现方式
 */
public class ImplGenericInterface2 implements GenericIntercace<String> {
    @Override
    public String getData() {
        return "Generic Interface2";
    }

    public static void main(String[] args) {
        ImplGenericInterface2 implGenericInterface2 = new ImplGenericInterface2();
        System.out.println(implGenericInterface2.getData());
    }
}
