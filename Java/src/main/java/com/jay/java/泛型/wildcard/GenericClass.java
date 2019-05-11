package com.jay.java.泛型.wildcard;

/**
 * Author：Jay On 2019/5/10 20:01
 * <p>
 * Description:
 */
public class GenericClass<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
