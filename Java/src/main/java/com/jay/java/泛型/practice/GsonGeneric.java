package com.jay.java.泛型.practice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author：Jay On 2019/5/11 22:11
 * <p>
 * Description: Gson库中的泛型使用
 */
public class GsonGeneric {
    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            personList.add(new Person("name" + i, 18 + i));
        }
        // Serialization
        String json = gson.toJson(personList);
        System.out.println(json);
        // Deserialization
        Type personType = new TypeToken<List<Person>>() {}.getType();
        List<Person> personList2 = gson.fromJson(json, personType);
        System.out.println(personList2);
    }
}
