package com.jay.java.myHandler;

import java.util.UUID;

public class HandlerMain {
    public static void main(String[] args) {
        Looper.prepare();
        final Handler handler = new Handler() {
            @Override
            public void handlerMessage(Message msg) {
                super.handlerMessage(msg);
                System.out.println("myselfHandler-handleMessage: thread id=" + Thread.currentThread().getId());
                System.out.println("myselfHandler-handleMessage: msg= " + msg.toString());
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始");
                Message message=new Message(UUID.randomUUID().toString());
                System.out.println("myselfHandler-handleMessage: thread id=" + Thread.currentThread().getId());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("结束");
                handler.sendMessage(message);
            }
        }).start();
        Looper.loop();
    }
}
