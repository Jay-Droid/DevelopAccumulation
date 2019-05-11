package com.jay.java;

import com.jay.java.Android相关.view.MeasureSpec;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyClass {
    public static void main(String[] args) {
//        testCallable();
        testMeasureSpec();
    }

    private static void testMeasureSpec() {
        MeasureSpec measureSpec = new MeasureSpec();

        System.out.println("MODE_SHIFT=" + MeasureSpec.MODE_SHIFT);
        System.out.println("MODE_MASK=" + Integer.toBinaryString(MeasureSpec.MODE_MASK));
        System.out.println("AT_MOST=" + Integer.toBinaryString(MeasureSpec.AT_MOST));
        System.out.println("EXACTLY=" + Integer.toBinaryString(MeasureSpec.EXACTLY));
        System.out.println("UNSPECIFIED=" + Integer.toBinaryString(MeasureSpec.UNSPECIFIED));
    }

    private static void testCallable() {
        try {
            MyCallable myCallable = new MyCallable();
            FutureTask<String> futureTask = new FutureTask<>(myCallable);
            new Thread(futureTask).start();
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyCallable implements Callable<String> {
        @Override
        public String call() {
            return "Callable String";
        }
    }


}
