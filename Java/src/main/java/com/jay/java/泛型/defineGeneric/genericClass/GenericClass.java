package com.jay.java.泛型.defineGeneric.genericClass;

/**
 * Author：Jay On 2019/5/9 16:49
 * <p>
 * Description: 泛型类
 */
public class GenericClass<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        GenericClass<String> genericClass=new GenericClass<>();
        genericClass.setData("Generic Class");
        System.out.println(genericClass.getData());
    }
}
