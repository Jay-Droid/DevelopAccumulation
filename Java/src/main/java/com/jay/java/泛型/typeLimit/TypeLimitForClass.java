package com.jay.java.泛型.typeLimit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author：Jay On 2019/5/10 17:02
 * <p>
 * Description: 类型变量的限定-类
 */
public class TypeLimitForClass<T extends List & Serializable> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("A");
        stringArrayList.add("B");
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(1);
        integerArrayList.add(2);
        integerArrayList.add(3);
        TypeLimitForClass<ArrayList> typeLimitForClass01 = new TypeLimitForClass<>();
        typeLimitForClass01.setData(stringArrayList);
        TypeLimitForClass<ArrayList> typeLimitForClass02 = new TypeLimitForClass<>();
        typeLimitForClass02.setData(integerArrayList);

        System.out.println(getMinListSize(typeLimitForClass01.getData().size(), typeLimitForClass02.getData().size()));

    }

    public static <T extends Comparable<T>> T getMinListSize(T a, T b) {
        return (a.compareTo(b) < 0) ? a : b;
    }
}
