package com.jay.java.设计模式.策略模式;

public class StrategyPattern {
    public static void main(String[] args) {

        Context_SalesMan mSalesMan = new Context_SalesMan();

        //春节来了，使用春节促销活动
        System.out.println("对于春节：");
        mSalesMan.salesMan("A");
        mSalesMan.salesManShow();


        //中秋节来了，使用中秋节促销活动
        System.out.println("对于中秋节：");
        mSalesMan.salesMan("B");
        mSalesMan.salesManShow();

        //圣诞节来了，使用圣诞节促销活动
        System.out.println("对于圣诞节：");
        mSalesMan.salesMan("C");
        mSalesMan.salesManShow();
    }
}
