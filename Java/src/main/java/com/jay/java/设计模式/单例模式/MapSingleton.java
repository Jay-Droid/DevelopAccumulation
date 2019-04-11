package com.jay.java.设计模式.单例模式;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用容器实现单例模式
 */
public class MapSingleton {

    private static Map<String, Object> objectMap = new HashMap<>();

    private MapSingleton() {
    }

    public static void registerInstance(String key, Object instance) {
            objectMap.put(key, instance);
    }

    public static Object getInstance(String key) {
        return objectMap.get(key);
    }
}
