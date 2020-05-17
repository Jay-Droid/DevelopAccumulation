package com.jay.java.Android相关.myHandler;

public class Looper {
    public MessageQueue mQueue;
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();

    private Looper() {
        mQueue = new MessageQueue();
    }

    /**
     * 初始化Looper
     */
    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    /**
     * 获取Looper
     *
     * @return Looper
     */
    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        final Looper me = myLooper();//保证线程的唯
        final MessageQueue queue = me.mQueue;
        for(;;){
            Message msg=queue.next();
            msg.target.dispatchMessage(msg);
        }

    }

}
