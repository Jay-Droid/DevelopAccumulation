package com.jay.java.Android相关.myHandler;

public class Handler {
    final MessageQueue mQueue;
    final Looper mLooper;

    public Handler() {
        mLooper = Looper.myLooper();
        mQueue = mLooper.mQueue;
    }

    public void sendMessage(Message msg) {
        enqueueMessage(msg);
    }

    /**
     * 向消息队列添加消息
     *
     * @param msg 消息
     */
    private void enqueueMessage(Message msg) {
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    public void dispatchMessage(Message msg) {
        handlerMessage(msg);
    }

    /**
     * 最小知识原则
     *
     * @param msg
     */
    public void handlerMessage(Message msg) {

    }
}
