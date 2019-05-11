package com.jay.java.泛型.needGeneric;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Jay On 2019/5/9 16:23
 * <p>
 * Description: 为什么要使用泛型
 */
public class NeedGeneric2 {
    static class C{

    }
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add("A");
        list.add("B");
        list.add(new C());
        list.add(100);
        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
            String value= (String) list.get(i);
            System.out.println(value);
        }
    }
}
