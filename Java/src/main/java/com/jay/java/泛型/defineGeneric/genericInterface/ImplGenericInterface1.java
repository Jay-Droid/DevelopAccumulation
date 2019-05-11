package com.jay.java.泛型.defineGeneric.genericInterface;

/**
 * Author：Jay On 2019/5/9 16:59
 * <p>
 * Description: 泛型接口实现类-泛型类实现方式
 */
public class ImplGenericInterface1<T> implements GenericIntercace<T> {
    private T data;

    private void setData(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    public static void main(String[] args) {
        ImplGenericInterface1<String> implGenericInterface1 = new ImplGenericInterface1<>();
        implGenericInterface1.setData("Generic Interface1");
        System.out.println(implGenericInterface1.getData());
    }
}
