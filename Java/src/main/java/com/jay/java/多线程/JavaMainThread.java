package com.jay.java.多线程;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Author：Jay On 2019/5/19 10:50
 * <p>
 * Description: java天生就是多线程
 */
public class JavaMainThread {
    public static void main(String[] args) {
        //虚拟机线程管理的接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //取得线程信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + " " + threadInfo.getThreadName());
            //运行结果：
            //[6] Monitor Ctrl-Break
            //[5] Attach Listener
            //[4] Signal Dispatcher
            //[3] Finalizer
            //[2] Reference Handler
            //[1] main
        }
    }
}
