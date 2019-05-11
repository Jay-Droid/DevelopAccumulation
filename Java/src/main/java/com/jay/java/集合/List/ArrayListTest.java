package com.jay.java.集合.List;

import java.util.*;

public class ArrayListTest {
    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        arrayList.add("a3");

        arrayList.add("a2");

        arrayList.add("b1");

        for (String name : arrayList) {
            System.out.println(name);
        }

    }
}
