package com.jay.java.集合.Map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();

        map.put("a3", "aa");

        map.put("a2", "bb");

        map.put("b1", "cc");

        for (String name : map.values()) {
            System.out.println(name);
        }

    }
}
