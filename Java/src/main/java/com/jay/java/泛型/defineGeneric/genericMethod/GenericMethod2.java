package com.jay.java.泛型.defineGeneric.genericMethod;


/**
 * Author：Jay On 2019/5/10 16:10
 * <p>
 * Description: 泛型方法
 */
public class GenericMethod2 {

    /**
     * 泛型类
     *
     * @param <T> 泛型参数
     */
    public class GenericClass<T> {
        private T data;

        //这个是普通成员方法，不是泛型方法
        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

    }

    /**
     *这才是一个泛型方法
     */
    public <T, K> T showKeyName(GenericClass<T> data) {

        return data.getData();
    }

}
