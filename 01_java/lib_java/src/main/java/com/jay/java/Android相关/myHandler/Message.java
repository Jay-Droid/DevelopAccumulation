package com.jay.java.Android相关.myHandler;

public class Message {
    public String obj;
    public Handler target;

    public Message(String object) {
        obj = object;
    }

    @Override
    public String toString() {
        return "Message{" +
                "obj=" + obj +
                '}';
    }
}
