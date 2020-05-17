package com.jay.java.设计模式.适配器模式;

/**
 * 定义具体使用目标类，并通过Adapter类调用所需要的方法从而实现目标
 */
public class AdapterPattern {

    public static void main(String[] args) {
        Target mAdapter = new Adapter();
        mAdapter.request();
    }
}
