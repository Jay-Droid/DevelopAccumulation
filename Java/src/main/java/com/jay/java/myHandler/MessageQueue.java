package com.jay.java.myHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {
    //阻塞队列，模拟MessageQueue阻塞机制
    BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(10);

    /**
     * 向队列中添加消息
     * 根据时间排序，当队列满的时候，阻塞，直到用户调用next取出消息
     * 当next被调用，通知MessageQueue可以进行消息入队
     *
     * @param msg Message
     */
    public void enqueueMessage(Message msg) {
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从消息队列中取出消息
     * 当消息达到执行时间就取出来
     * 当MessageQueue为空的时候就阻塞队列
     * 等MessageQueue调用enqueueMessage的时候通知队列可以取消息，停止阻塞
     *
     * @return Message
     */
    public Message next() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
