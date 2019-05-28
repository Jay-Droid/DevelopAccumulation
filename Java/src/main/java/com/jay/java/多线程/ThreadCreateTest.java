package com.jay.java.多线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Author：Jay On 2019/5/19 13:55
 * <p>
 * Description: 线程的创建测试类
 */
public class ThreadCreateTest {
    /*扩展自Thread类*/
    private static class UseThread extends Thread{
        @Override
        public void run() {
            super.run();
            //do my work
            System.out.println("I am extends Thread");
        }
    }

    /*实现Runnable接口*/
    private static class UseRun implements Runnable{

        @Override
        public void run() {
            System.out.println("I am implements Runnable");
        }
    }

    /*实现Callable接口，允许有返回值*/
    private static class UseCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("I am implements Callable");
            return "CallResult";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for (int i = 0; i < 10000000; i++) {
            UseThread useThread = new UseThread();
            useThread.start();
        }

//        UseRun useRun = new UseRun();
//        new Thread(useRun).start();
//
//
//        UseCall useCall = new UseCall();
//        FutureTask<String> futureTask = new FutureTask<>(useCall);
//        new Thread(futureTask).start();
//        //do my work
//        //.....
//        System.out.println(futureTask.get());
    }
}
