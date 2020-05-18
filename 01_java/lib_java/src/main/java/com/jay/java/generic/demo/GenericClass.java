package com.jay.java.generic.demo;

/**
 * 泛型类
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2020/5/18
 */
public class GenericClass<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T setData) {
        this.data = setData;
    }

    public void show() {
        System.out.println(data.toString());
    }


}
