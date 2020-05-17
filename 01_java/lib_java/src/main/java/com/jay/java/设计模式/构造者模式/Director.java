package com.jay.java.设计模式.构造者模式;

/**
 * 电脑城老板委派任务给装机人员（Director）
 */
public class Director {
    //指挥装机人员组装电脑
    public void Construct(Builder builder) {
        builder.BuildCPU();
        builder.BuildMainboard();
        builder.BuildHD();
    }
}
