package com.jay.java.Generic.genericsdemo02.demo01;

public class XiaoMing extends Person {


    public RawPlate getPlate(){
        return new RawPlate();
    }

    public <T> AIPlate<T> getAIPlate(){
        return new AIPlate<T>();
    }

}
