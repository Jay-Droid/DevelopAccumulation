package com.jay.java.多线程;

import com.jay.java.多线程.concurrent.tools.SleepTools;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示Java线程的测试类
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-26 17:51
 */
public class ThreadMainTest {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    int demoIndex = 7;

    switch (demoIndex) {
      case 1:
        {
          // Demo1:多线程的一些概念
          Demo1();
          break;
        }
      case 2:
        {
          // Demo2:线程的启动
          Demo2();
          break;
        }
      case 3:
        {
          // Demo3:线程的终止
          Demo3();
          break;
        }
      case 4:
        {
          // Demo4:其他的线程方法，run,start,yield,join
          Demo4();
          break;
        }
      case 5:
        {
          // Demo5:线程间的共享
          Demo5();
          break;
        }
      case 6:
        {
          // Demo6：线程间的协作
          Demo6();
          break;
        }
      case 7:
        {
          // Demo7：ThreadLocal的使用
          Demo7();
          break;
        }
      case 8:
        {
          // Demo8：显式锁 Lock
          Demo8();
          break;
        }
      case 9:
        {
          // Demo9：读写锁 ReentrantReadWriteLock 或者 synchronized
          Demo9();
          break;
        }
      case 10:
        {
          // Demo10：线程池
          Demo10();
          break;
        }
    }
  }

  /**
   * Demo1:多线程的一些概念
   *
   * <p>1，多核心：多个处理器集成到一个芯片上并行执行不同的进程
   *
   * <p>2，多线程：通过复制处理器上的结构状态,让同一个处理器上的多个线程同步执行并共享处理器的执行资源
   *
   * <p>3，关系：1比1 四核四线程，1比2 四核八线程（利用超线程技术实现）
   *
   * <p>4，CPU时间片轮转机制：时间片轮转调度是一种最古老、最简单、最公平且使用最广的CPU调度算法,又称RR调度。每个进程被分配一个时间段,称作它的时间片,即该进程允许运行的时间。
   * 调度程序所要做的就是维护一张就绪进程列表,当进程用完它的时间片后,它被移到队列的末尾。
   * 时间片设得太短会导致过多的进程切换,降低了CPU效率:而设得太长又可能引起对短的交互请求的响应变差。将时间片设为100ms通常是一个比较合理的折衷。
   *
   * <p>5，进程：进程是程序运行资源分配的最小单位，其中资源包括:CPU、内存空间、磁盘IO等，进程可以分为系统进程和用户进程,同一进程中的多条线程共享该进程中的全部系统资源
   *
   * <p>6，线程：线程是CPU调度的最小单位,必须依赖于进程而存在，线程自己基本上不拥有系统资源,只拥有一点在运行中必不可少的资源(如程序计数器,一组寄存器和栈)
   *
   * <p>7，并行：指应用能够同时执行不同的任务，如：八条车道最多同时并排行驶8辆车
   *
   * <p>8，并发：指应用能够交替执行不同的任务，如：一小时内经过这八条车道的车辆有两千
   *
   * <p>9，多线程可以给程序带来如下好处： (1)充分利用CPU的资源 (2)加快响应用户的时间 (3)可以使你的代码模块化,异步化,简单化
   *
   * <p>10，多线程程序需要注意事项 (1)线程之间的安全性：有多个线程同时执行写操作,一般都需要考虑线程同步,否则就可能影响线程安全。
   * (2)线程之间的死循环过程：Java的锁机制用不好可能会不小心就会产生Java线程死锁的多线程问题 (3)线程太多了会将服务器资源耗尽形成死机当机：资源池
   *
   * <p>11，线程的生命周期 https://www.cnblogs.com/marsitman/p/11228684.html
   *
   * <p>(1)新建（new）new Thread() 这里的创建，仅仅是在JAVA的这种编程语言层面被创建，而在操作系统层面，真正的线程还没有被创建。只有当我们调用了 start()
   * 方法之后，该线程才会被创建出来，进入Runnable状态。
   *
   * <p>(2)就绪（runnable）调用start()方法后，JVM 进程会去创建一个新的线程，而此线程不会马上被 CPU
   * 调度运行，进入Running状态，这里会有一个中间状态，就是Runnable状态，你可以理解为等待被 CPU
   * 调度的状态,Runnable状态的线程要么能被转换成Running状态，要么被意外终止。
   *
   * <p>(3)运行（running）当CPU调度发生，并从任务队列中选中了某个Runnable线程时，该线程会进入Running执行状态，并且开始调用run()方法中逻辑代码。
   * 那么处于Running状态的线程能发生哪些状态转变？ 被转换成Terminated状态，比如调用 stop() 方法; 被转换成Blocked状态，比如调用了sleep, wait
   * 方法被加入 waitSet 中； 被转换成Blocked状态，如进行 IO 阻塞操作，如查询数据库进入阻塞状态； 被转换成Blocked状态，比如获取某个锁的释放，而被加入该锁的阻塞队列中；
   * 该线程的时间片用完，CPU 再次调度，进入Runnable状态； 线程主动调用 yield 方法，让出 CPU 资源，进入Runnable状态
   *
   * <p>(4)阻塞（blocked） Blocked状态的线程能够发生哪些状态改变？ 被转换成Terminated状态，比如调用 stop() 方法，或者是 JVM 意外 Crash;
   * 被转换成Runnable状态，阻塞时间结束，比如读取到了数据库的数据后； 完成了指定时间的休眠，进入到Runnable状态；
   * 正在wait中的线程，被其他线程调用notify/notifyAll方法唤醒，进入到Runnable状态； 线程获取到了想要的锁资源，进入Runnable状态；
   * 线程在阻塞状态下被打断，如其他线程调用了interrupt方法，进入到Runnable状态
   *
   * <p>(5)销毁（terminated） 一旦线程进入了Terminated状态，就意味着这个线程生命的终结，哪些情况下，线程会进入到Terminated状态呢？
   * 线程正常运行结束，生命周期结束； 线程运行过程中出现意外错误； JVM 异常结束，所有的线程生命周期均被结束。
   */
  private static void Demo1() {
    System.out.println("-----Demo1-----\n\n");
    // Java虚拟机可用的处理器数量
    int count = Runtime.getRuntime().availableProcessors();
    System.out.println("Java虚拟机可用的处理器数量:" + count);

    System.out.println("Java里的程序天生就是多线程的:");
    // 一个Java程序从main()方法开始执行，然后按照既定的代码逻辑执行，看似没有其他线程参与，
    // 但实际上Java程序天生就是多线程程序，因为执行main()方法的是一个名称为main的线程。

    // 获取虚拟机线程管理的接口
    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    // 返回具有堆栈跟踪和同步信息的所有活线程的线程信息。
    // dumpAllThreads(boolean lockedMonitors, boolean lockedSynchronizers)
    ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
    // 打印所有线程的id和name
    for (ThreadInfo threadInfo : threadInfos) {
      System.out.println("[" + threadInfo.getThreadId() + "]" + " " + threadInfo.getThreadName());
    }

    /**
     * 运行结果： [6] Monitor Ctrl-Break //监控Ctrl-Break中断信号的 [5] Attach
     * Listener//内存dump，线程dump，类信息统计，获取系统属性等 [4] Signal Dispatcher // 分发处理发送给JVM信号的线程 [3] Finalizer
     * //调用对象finalize方法的线程 [2] Reference Handler//清除Reference的线程 [1] main //main线程，用户程序入口
     */
    System.out.println("Android里的程序天生就是多线程的:");
    // 将下面的代码在Activity中执行
//    Map<Thread, StackTraceElement[]> threadMap = Thread.getAllStackTraces();
//    for (Thread thread : threadMap.keySet()) {
//      System.out.println("[" + thread.getId() + "]" + " " + thread.getName());
//    }

    /**
     * D: jay_thread running
     D: [1335] Thread-2
     D: [1354] OkHttp ConnectionPool
     D: [1389] ACCS0
     D: [1373] pool-1-thread-1
     D: [1394] Thread-30
     D: [1413] ACCS-SEND1
     D: [1418] AsyncTask #1
     D: [1370] NetWorkSender
     D: [1330] HeapTaskDaemon
     D: [1391] Binder:interceptor
     D: [1396] Thread-31
     D: [1327] ReferenceQueueDaemon
     D: [1377] queued-work-looper
     D: [1333] Binder:8785_3
     D: [1341] Thread-8
     D: [1427] jay_thread
     D: [1382] LMREQUEST
     D: [1337] Thread-4
     D: [1417] Binder:8785_5
     D: [1395] AWCN Scheduler1
     D: [1329] FinalizerWatchdogDaemon
     D: [1423] UMThreadPoolExecutor6
     D: [1416] SL-NetWorkSender
     D: [1336] T-log
     D: [1324] Jit thread pool worker thread 0
     D: [1332] Binder:8785_2
     D: [1381] Okio Watchdog
     D: [2] T-connStch
     D: [1331] Binder:8785_1
     D: [1411] pool-4-thread-1
     D: [1419] UMThreadPoolExecutor3
     D: [1359] Thread-23
     D: [1378] UMThreadPoolExecutor2
     D: [1355] Thread-19
     D: [1367] SpStatus daemon
     D: [1349] Thread-15
     D: [1420] UMThreadPoolExecutor4
     D: [1409] spdy-0
     D: [1421] Thread-41
     D: [1414] Binder:8785_4
     D: [1356] UMThreadPoolExecutor1
     D: [1403] RenderThread
     D: [1325] Signal Catcher
     D: [1366] Thread-26
     D: [1371] FileObserver
     D: [1334] Profile Saver
     D: [1398] AMDC1
     D: [1384] sensor_thread
     D: [1422] UMThreadPoolExecutor5
     D: [1407] process reaper
     D: [1369] ConnectivityThread
     D: [1328] FinalizerDaemon
     D: [1358] Thread-22
     D: [1397] Thread-32
     D: [1402] AMDC2
     D: [1364] DBUpdater
     D: [1343] Thread-10
     D: [1346] Thread-12
     D: [1365] work_thread
     D: processors=8
     D: jay_thread running
     D: [1335] Thread-2
     D: [1354] OkHttp ConnectionPool
     D: [1389] ACCS0
     D: [1373] pool-1-thread-1
     D: [1394] Thread-30
     D: [1413] ACCS-SEND1
     D: [1418] AsyncTask #1
     D: [1370] NetWorkSender
     D: [1330] HeapTaskDaemon
     D: [1391] Binder:interceptor
     D: [1396] Thread-31
     D: [1327] ReferenceQueueDaemon
     D: [1377] queued-work-looper
     D: [1333] Binder:8785_3
     D: [1341] Thread-8
     D: [1382] LMREQUEST
     D: [1337] Thread-4
     D: [1417] Binder:8785_5
     D: [1395] AWCN Scheduler1
     D: [1329] FinalizerWatchdogDaemon
     D: [1423] UMThreadPoolExecutor6
     D: [1416] SL-NetWorkSender
     D: [1336] T-log
     D: [1324] Jit thread pool worker thread 0
     D: [1332] Binder:8785_2
     D: [1381] Okio Watchdog
     D: [2] T-connStch
     D: [1331] Binder:8785_1
     D: [1411] pool-4-thread-1
     D: [1419] UMThreadPoolExecutor3
     D: [1359] Thread-23
     D: [1378] UMThreadPoolExecutor2
     D: [1355] Thread-19
     D: [1367] SpStatus daemon
     D: [1349] Thread-15
     D: [1420] UMThreadPoolExecutor4
     D: [1409] spdy-0
     D: [1421] Thread-41
     D: [1414] Binder:8785_4
     D: [1356] UMThreadPoolExecutor1
     D: [1403] RenderThread
     D: [1325] Signal Catcher
     D: [1366] Thread-26
     D: [1371] FileObserver
     D: [1334] Profile Saver
     D: [1429] jay_thread
     D: [1398] AMDC1
     D: [1384] sensor_thread
     D: [1422] UMThreadPoolExecutor5
     D: [1407] process reaper
     D: [1369] ConnectivityThread
     D: [1328] FinalizerDaemon
     D: [1358] Thread-22
     D: [1397] Thread-32
     D: [1402] AMDC2
     D: [1364] DBUpdater
     D: [1343] Thread-10
     D: [1346] Thread-12
     D: [1365] work_thread
     D: processors=8
     */
  }

  /**
   * Demo2:线程的启动 启动线程的方式有： 1、X extends Thread;，然后X.run 2、X implements Runnable；然后交给Thread运行 3、X
   * implements Callable；然后交给Thread运行
   * 第1、2方式都有一个缺陷就是：在执行完任务之后无法获取执行结果。从Java1.5开始，就提供了Callable和Future，通过它们可以在任务执行完毕之后得到任务执行结果。
   */
  private static void Demo2() throws ExecutionException, InterruptedException {
    System.out.println("-----Demo2-----\n\n");
    // 创建方式一：X extends Thread
    System.out.println("创建方式一：X extends Thread");
    UseThread t1 = new UseThread();
    t1.start();
    // 创建方式二：X implements Runnable
    System.out.println("创建方式二：X implements Runnable");
    UseRunnable r = new UseRunnable();
    Thread t2 = new Thread(r);
    t2.start();
    // 创建方式三：X implements Callable
    System.out.println("创建方式三：X implements Callable");
    UseCallable useCall = new UseCallable();
    // Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果。必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
    // 因为Future只是一个接口，所以是无法直接用来创建对象使用的，因此就有了下面的FutureTask。
    FutureTask<String> futureTask = new FutureTask<>(useCall);
    Thread t3 = new Thread(futureTask);
    t3.start();
    // 通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
    System.out.println("UseCallable返回信息:" + futureTask.get());
  }

  /**
   * Demo4:其他的线程方法（start/run/yield/join） Thread类是Java里对线程概念的抽象，可以这样理解：我们通过new
   * Thread()其实只是new出一个Thread的实例，还没有操作系统中真正的线程挂起钩来。 只有执行了start()方法后，才实现了真正意义上的启动线程。
   * 1，start()方法：使一个线程进入就绪队列等待分配cpu，分到cpu后才调用实现的run()方法，start()方法不能重复调用。
   * 2，run()方法：是线程业务逻辑实现的地方，本质上和任意一个类的任意一个成员方法并没有任何区别，可以重复执行，可以被单独调用。
   * 2，yield()方法：使当前线程让出CPU占有权，但让出的时间是不可设定的。也不会释放锁资源，所有执行yield()的线程有可能在进入到可执行状态后马上又被执行。
   * 3，join()方法：把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
   */
  private static void Demo4() {
    System.out.println("-----Demo4-----\n\n");
    System.out.println("深入理解run()和start(),(开启一个线程分别调用线程实例的start和run方法)");
    // start和run方法的区别
    ThreadRunAndStart threadRunAndStart = new ThreadRunAndStart();
//    threadRunAndStart.run(); // 当前是main线程
//    threadRunAndStart.start(); // 当前是thread-0 线程
    //threadRunAndStart.start(); //不能调用多次：Exception in thread "main" java.lang.IllegalThreadStateException

    System.out.println("\n\n深入理解 yield()方法(开启两个线程进入同一个代码块，调用yield方法后看当前正在执行的线程是否会让出cpu执行权)");
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        //当有synchronized时yield()方法会失效?
        //当yield()方法执行后线程确实变成了可运行状态,但是有一点需要注意.调用yield()方法的时候是在同步代码内,所以该线程还没释放锁对象,
        //那么真实的情况就是 线程A调用了yield()方法后状态变为了可运行,然后和A和B再次重新竞争cpu的执行权.
        //但是B没有Demo的锁对象,所以必定失败.就会给我们造成同步代码内调用yield()方法无效.
//                synchronized (this) {
        for (int i = 0; i < 5; i++) {
          System.out.println("当前线程为: " + Thread.currentThread().getName() + "index: " + i);
          if (i == 3) {
            System.out.println("调用了Thread.yield()");
            //yield()方法的作用是将当前的线程的状态变为可运行状态,其意义就是让一个正在运行的线程主动放弃cpu资源.但是主动放弃并不意味着就完全放弃了运行的权限,就相当于重新和其他线程竞争cpu资源.例如 线程A正在运行然后调用了yield()不意味着能成功将cpu下个时间片运行的线程变成线程B, 有可能还是线程A.
            Thread.yield();
          }
        }
//                }
      }
    };
    Thread threadA = new Thread(runnable, "Thread-A---");
    Thread threadB = new Thread(runnable, "Thread-B---");
//    threadA.start();
//    threadB.start();

    System.out.println("\n\n深入理解 join()方法(顺序执行线程A,B,C)");
    ThreadA tA = new ThreadA();
    ThreadB tB = new ThreadB(tA);
    ThreadC tc = new ThreadC(tB);
    tA.start();
    tB.start();
    tc.start();

  }

  /**
   * 扩展自Thread类
   */
  private static class UseThread extends Thread {
    @Override
    public void run() {
      super.run();
      // do my work
      System.out.println("我是通过继承Thread来实现的线程");
      Thread t = Thread.currentThread();
      System.out.println("[" + t.getId() + "]" + " " + t.getName());
    }
  }

  /** 实现Runnable接口 */
  private static class UseRunnable implements Runnable {

    @Override
    public void run() {
      // do my work
      System.out.println("我是通过实现Runnable接口实现的线程");
      Thread t = Thread.currentThread();
      System.out.println("[" + t.getId() + "]" + " " + t.getName());
    }
  }

  /**
   * 实现Callable接口，允许有返回值
   * Callable位于java.util.concurrent包下，它也是一个接口，在它里面也只声明了一个方法，只不过这个方法叫做call()，这是一个泛型接口，call()函数返回的类型就是传递进来的V类型。
   */
  private static class UseCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
      // do my work
      System.out.println("我是通过实现Runnable接口实现的线程");
      Thread t = Thread.currentThread();
      System.out.println("[" + t.getId() + "]" + " " + t.getName());
      TimeUnit.SECONDS.sleep(2);

      return "CallResult";
    }
  }

  /**
   * Demo3:线程的终止
   * 自然终止：要么是run执行完成了，要么是抛出了一个未处理的异常导致线程提前结束。
   * 手动中止：暂停、恢复和停止操作对应在线程Thread的API就是 suspend()、resume()和stop()。但是这些API是过期的， 因为这些API终结一个线程时不会保证线程的资源正常释放，这样容易引发死锁问题或导致程序可能工作在不确定状态下
   * 如何安全的终止一个线程？
   * 安全的中止则是其他线程通过调用某个线程A的interrupt()方法对其进行中断操作，不代表线程A会立即停止自己的工作，同样的A线程完全可以不理会这种中断请求。因为java里的线程是协作式的，不是抢占式的
   * 线程通过检查自身的中断标志位是否被置为true来进行响应，线程通过方法isInterrupted()来进行判断是否被中断，
   * 也可以调用静态方法Thread.interrupted()来进行判断当前线程是否被中断，不过Thread.interrupted()会同时将中断标识位改写为false。
   * 如果一个线程处于了阻塞状态（如线程调用了thread.sleep、thread.join、thread.wait、），则线程在检查中断标示时如果发现中断标示为true，则会在这些阻塞方法调用处抛出InterruptedException异常
   * 处于死锁状态的线程无法被中断
   */
  private static void Demo3() throws InterruptedException {
    System.out.println("-----Demo3-----\n\n");
    Thread endThread = new EndThread("EndThread");
    endThread.start();
    Thread.sleep(1000);
//    System.out.println("stop()方法终止线程");
//     endThread.stop();
    System.out.println("interrupt()方法终止线程");
    endThread.interrupt();
  }

  /** 用于演示线程的终止 */
  static class EndThread extends Thread {

    public EndThread(String endThread) {
      super(endThread);
    }

    @Override
    public void run() {
      String threadName = Thread.currentThread().getName();
      System.out.println(threadName + "01_run中断标志位：isInterrupted=" + isInterrupted());
      // 将听到中断标志位发生改变就停止循环
      while (!isInterrupted()) {
        // 静态方法Thread.interrupted()会将isInterrupted修改为false
//        while (!Thread.interrupted()) {
        // 开启一个线程，执行一个死循环,测试stop
//      while (true) {
//        try {
        //如果一个线程处于了阻塞状态（如线程调用了thread.sleep、thread.join、thread.wait、），则线程在检查中断标示时如果发现中断标示为true，则会在这些阻塞方法调用处抛出InterruptedException异常
        //Thread.sleep(1000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
        System.out.println(threadName + "------线程正在运行中。。。");
        System.out.println(threadName + "02_循环体中断标志位：isInterrupted=" + isInterrupted());
      }
      System.out.println(threadName + "03_循环体外断标志位：isInterrupted=" + isInterrupted());
    }
  }

  /**
   * 测试start和run方法的区别
   */
  public static class ThreadRunAndStart extends Thread {

    @Override
    public void run() {
      int i = 10;
      while (i > 0) {
        second(1);
        System.out.println("I am " + Thread.currentThread().getName() + " and now the i=" + i--);
      }
    }
  }

  /**
   * 测试join()-ThreadA
   */
  static class ThreadA extends Thread {
    @Override
    public void run() {
      System.out.println("A");
    }
  }

  /**
   * 测试join()-ThreadB
   */
  static class ThreadB extends Thread {
    private ThreadA threadA;

    public ThreadB(ThreadA threadA) {
      this.threadA =threadA;
    }

    @Override
    public void run() {
      try {
        threadA.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("B");
    }

  }

  /**
   * 测试join()-ThreadC
   */
  static class ThreadC extends Thread {
    private ThreadB threadB;

    public ThreadC(ThreadB threadB) {
      this.threadB = threadB;
    }

    @Override
    public void run() {
      try {
        threadB.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("C");
    }
  }


  /**
   * Demo5:线程间的共享 内置锁synchronized： Java支持多个线程同时访问一个对象或者对象的成员变量，关键字synchronized可以修饰方法或者以同步块的形式来进行使用，
   * 它主要确保多个线程在同一个时刻，只能有一个线程处于方法或者同步块中， 它保证了线程对变量访问的可见性和排他性，又称为内置锁机制
   *
   * <p>对象锁和类锁：
   * 一，对象锁是针对实例对象的，类声明后，我们可以 new 出来很多的实例对象。这时候，每个实例在 JVM
   * 中都有自己的引用地址和堆内存空间，在实例上加的锁和其他的实例就没有关系，互不影响了 使用对象锁的方式有下面三种：
   * 1、锁住实体里的非静态变量
   * 2、直接在非静态方法上加synchronized
   * 3、锁住 this 对象 使用对象锁的情况，只有使用同一实例的线程才会受锁的影响，多个实例调用同一方法不会受影响。
   *
   * <p>二，类锁是加在类上的，而类信息是存在 JVM 方法区的，并且整个 JVM 只有一份，方法区又是所有线程共享的，所以类锁是所有线程共享的。 使用类锁的方式有如下方式：
   * 1、锁住类中的静态变量 2、直接在静态方法上加 synchronized 3、锁住 Class(className.class)
   * 类锁是所有线程共享的锁，所以同一时刻，只能有一个线程使用加了锁的方法或方法体，不管是不是同一个实例。
   */
  private static void Demo5() throws InterruptedException {
    System.out.println("-----Demo5-----\n\n");
    System.out.println("测试线程间的共享的安全性（两个线程对同一个变量同时累加100次)");
    final SyncCountTest syncCountTest = new SyncCountTest();
    Runnable runnable =
        new Runnable() {
          @Override
          public void run() {
            for (int i = 0; i < 1000; i++) {
//               syncCountTest.addCount1();//没加锁
              syncCountTest.addCount2(); // 方发锁（对象锁）
            }
          }
        };
    Thread threadA = new Thread(runnable, "Thread-A---");
    Thread threadB = new Thread(runnable, "Thread-B---");
    threadA.start();
    threadB.start();
    Thread.sleep(50);
    System.out.println("多线程操作后的数量：count=" + syncCountTest.count); // 2000

//    System.out.println("\n\n测试对象锁（多个线程同时执行同一个实例的同一个方法）");
    // 使用相同的实例对象
    final InstanceLock instanceLock = new InstanceLock();
    for (int i = 0; i < 5; i++) {
      Thread instanceThread = new Thread(new Runnable() {
                @Override
                public void run() {
                  // 每次都创建新的实例对象，影响对象锁
                  // InstanceLock instanceLock = new InstanceLock();
                  int instanceIndex = 0;
                  switch (instanceIndex) {
                    case 1:
                      {
                        // 未加锁
                        instanceLock.unLock(); // 同时执行完
                        break;
                      }
                    case 2:
                      {
                        // 1、锁住实体里的非静态变量
                        instanceLock.lockField(); // 各个线程依次执行
                        break;
                      }
                    case 3:
                      {
                        // 2、直接在非静态方法上加 synchronized
                        instanceLock.lockMethod(); // 各个线程依次执行
                        break;
                      }
                    case 4:
                      {
                        // 3、锁住 this 对象
                        instanceLock.lockThis(); // 各个线程依次执行
                        break;
                      }
                  }
                }
              });
      instanceThread.setName("instanceThread-" + i);
      instanceThread.start();
    }

    System.out.println("\n\n测试类锁（多个线程同时执行同一个类的同一个方法）");
    // 使用相同的实例对象
    final ClassLock classLock = new ClassLock();
    for (int i = 0; i < 5; i++) {
      Thread classThread = new Thread(new Runnable() {
                @Override
                public void run() {
                  // 每次都创建新的实例对象,不影响类锁
                  // ClassLock classLock = new ClassLock();
                  int instanceIndex = 1;
                  switch (instanceIndex) {
                    case 1:
                      {
                        // 未加锁
                        ClassLock.unLock(); // 同时执行完
                        break;
                      }
                    case 2:
                      {
                        // 1、锁住静态变量
                        ClassLock.lockStaticField(); // 各个线程依次执行
                        break;
                      }
                    case 3:
                      {
                        // 2、锁住静态方法
                        ClassLock.lockStaticMethod(); // 各个线程依次执行
                        break;
                      }
                    case 4: {
                      // 3、锁住 Class(className.class)
                        classLock.lockClass(); // 各个线程依次执行
                        break;
                      }
                  }
                }
              });
      classThread.setName("classThread-" + i);
      classThread.start();
    }
  }

  /** 数量累加测试类 */
  public static class SyncCountTest {

    private long count = 0;

    /** 未加锁 */
    void addCount1() {
      count++;
    }

    /** 对象锁 */
    synchronized void addCount2() {
      count++;
    }
  }

  /** 对象锁测试类 */
  public static class InstanceLock {
    /** 实例成员作为锁 */
    private final Object lockField = new Object();

    /** 未加锁 */
    public void unLock() {
      second(1);
      System.out.println(new Date().toString() + "--" + Thread.currentThread().getName());
    }

    /** 锁住非静态变量 */
    public void lockField() {
      synchronized (lockField) {
        second(1);
        System.out.println(new Date().toString() + "--" + Thread.currentThread().getName());
      }
    }

    /** 锁住 this 对象 this 就是当前对象实例 */
    public void lockThis() {
      synchronized (this) {
        second(1);
        System.out.println(new Date().toString() + "--" + Thread.currentThread().getName());
      }
    }

    /** 直接锁住非静态方法 */
    public synchronized void lockMethod() {
      second(1);
      System.out.println(new Date().toString() + "--" + Thread.currentThread().getName());
    }
  }

  /** 类锁测试类 */
  public static class ClassLock {
    /** 静态变量作为锁 */
    private static Object lockField = new Object();

    /** 未加锁 */
    public static void unLock() {
      second(1);
      System.out.println(new Date().toString() + "--" + Thread.currentThread().getName());
    }

    /** 锁住静态变量 */
    public static void lockStaticField() {
      synchronized (lockField) {
        second(1);
        System.out.println(new Date().toString() + "--" + Thread.currentThread().getName());
      }
    }

    /** 锁住 xxx.class */
    public void lockClass() {
      synchronized (ClassLock.class) {
        second(1);
        System.out.println(new Date().toString() + "--" + Thread.currentThread().getName());
      }
    }

    /** 锁住静态方法 */
    public static synchronized void lockStaticMethod() {
      second(1);
      System.out.println(new Date().toString() + "--" + Thread.currentThread().getName());
    }
  }

  /**
   * 按秒休眠
   *
   * @param seconds 秒数
   */
  private static void second(int seconds) {
    try {
//      System.out.println("等待中...");
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * 按秒休眠
   *
   * @param milliSeconds 秒数
   */
  private static void milliSeconds(int milliSeconds) {
    try {
      System.out.println("等待中...");
      TimeUnit.MILLISECONDS.sleep(milliSeconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Demo6：线程间的协作,等待/通知机制,
   *
   * <p>等待和通知的标准范式：
   *
   * <p>等待方遵循如下原则。 1）获取对象的锁。 2）如果条件不满足，那么调用对象的wait()方法，被通知后仍要检查条件。 3）条件满足则执行对应的逻辑。 synchronized(对象){
   * while(条件不满足){ 对象.wait() } 处理对应的逻辑 }
   *
   * <p>通知方遵循如下原则。 1）获得对象的锁。 2）改变条件。 3）通知所有等待在对象上的线程。 synchronized(对象){ 改变条件 对象.notifyAll() }
   *
   * <p>一，使用内置锁协作（wait()/notify()/notifyAll()）
   *
   * <p>wait()方法：调用该方法的线程进入WAITING状态,只有等待另外线程的通知或被中断才会返回.需要注意,调用wait()方法后,会释放对象的锁
   *
   * <p>notify()方法：知一个在对象上等待的线程,使其从wait方法返回,而返回的前提是该线程获取到了对象的锁，没有获得锁的线程重新进入WAITING状态。
   *
   * <p>notifyAll()方法：唤醒所有等待着的线程尝试获取锁，这些线程排队等待锁。
   *
   * <p>二，使用显示锁协作（await()/signal()/signalAll()）
   *
   * <p>await()方法：
   *
   * <p>signal()方法：
   *
   * <p>signalAll()方法：
   */
  private static void Demo6() throws InterruptedException {
    System.out.println("-----Demo6-----\n\n");
    System.out.println("测试线程间的协作-内置锁实现（快递业务：公里数或城市发生了变化通知用户）");
    final Expressage express = new Expressage(0, Expressage.CITY_HAI);
    for (int i = 0; i < 3; i++) {
      // 三个线程
      new Thread(
              new Runnable() {
                @Override
                public void run() {
                  // 检查里程数变化的线程,不满足条件，线程一直等待
                  express.waitKm();

                  // 检查地点变化的线程,不满足条件，线程一直等待
                  express.waitSite();
                }
              })
          .start();
    }

    // 5秒钟之后修改条件
    Thread.sleep(5000);
    // 快递里程数变化
    express.changeKm();
    // 快递城市变化
    express.changeSite();

    System.out.println("测试线程间的协作-显示锁实现（快递业务：公里数或城市发生了变化通知用户）");
    final ExpressageByLock expressByLock = new ExpressageByLock(0, ExpressageByLock.CITY_HAI);
    for (int i = 0; i < 3; i++) {
      // 三个线程
      new Thread(
              new Runnable() {
                @Override
                public void run() {
                  // 检查里程数变化的线程,不满足条件，线程一直等待
                  expressByLock.waitKm();

                  // 检查地点变化的线程,不满足条件，线程一直等待
                  expressByLock.waitSite();
                }
              })
          .start();
    }

    // 5秒钟之后修改条件
    Thread.sleep(5000);
    // 快递里程数变化
    expressByLock.changeKm();
    // 快递城市变化
    //      expressByLock.changeSite();
  }

  /** 快递业务测试类（内置锁synchronized实现） */
  public static class Expressage {

    public static String CITY_HAI = "ShangHai";
    public static String CITY_JING = "BeiJing";
    // 快递运输里程数
    private int km;
    // 快递到达地点
    private String site;

    public Expressage(int km, String site) {
      this.km = km;
      this.site = site;
    }

    /** 检查公里数变化的等待方 */
    public void waitKm() {
      synchronized (this) {
        while (this.km < 100) {
          try {
            System.out.println("waitKm");
            wait();
            System.out.println("检查公里数 thread[" + Thread.currentThread().getName() + "] 被通知了");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("公里数变化了： " + this.km);
      }
    }

    /** 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理 */
    public void changeKm() {
      synchronized (this) {
        this.km = 101;
        // notify();
        notifyAll();
      }
    }

    /** 检查城市变化的等待方 */
    public void waitSite() {
      synchronized (this) {
        while (CITY_JING.equals(this.site)) {
          try {
            System.out.println("waitSite");
            wait();
            System.out.println("检查地点 thread[" + Thread.currentThread().getName() + "] 被通知了");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("地点改变了： " + this.site);
      }
    }

    /** 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理 */
    public void changeSite() {
      synchronized (this) {
        this.site = CITY_JING;
        // notify();
        notifyAll();
      }
    }
  }

  /**
   * Demo7：ThreadLocal 的使用 https://www.zhihu.com/question/23089780 ThreadLocal
   * 的作用是提供线程内的局部变量，这种变量在线程的生命周期内起作用，减少同一个线程内多个函数或者组件之间一些公共变量的传递的复杂度。
   * ThreadLocal常用方法：
   * 1，void set(Object value) 设置当前线程的线程局部变量的值。
   * 2，Object get() 该方法返回当前线程所对应的线程局部变量。
   * 3，void remove() 将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK 5.0新增的方法。需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。
   * 4，protected Object initialValue() 返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的。这个方法是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。
   *
   * <p>ThreadLocal与Synchronized：
   * 1，相同：ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题。
   * 2，不同：Synchronized同步机制采用了“以时间换空间”的方式，仅提供一份变量，让不同的线程排队访问；而ThreadLocal采用了“以空间换时间”的方式，每一个线程都提供了一份变量，因此可以同时访问而互不影响。
   * 3，以时间换空间->即枷锁方式，某个区域代码或变量只有一份节省了内存，但是会形成很多线程等待现象，因此浪费了时间而节省了空间。
   * 4，以空间换时间->为每一个线程提供一份变量，多开销一些内存，但是呢线程不用等待，可以一起执行而相互之间没有影响。 *
   * ThreadLocal是解决线程安全问题一个很好的思路，它通过为每个线程提供一个独立的变量副本解决了变量并发访问的冲突问题。在很多情况下，ThreadLocal比直接使用synchronized同步机制解决线程安全问题更简单，更方便，且结果程序拥有更高的并发性。
   */
  private static void Demo7() {
    System.out.println("-----Demo7-----\n\n");
    System.out.println("演示ThreadLocal的使用）");
    UseThreadLocal test = new UseThreadLocal();
    test.StartThreadArray();
  }

  /** 类说明：演示ThreadLocal的使用 */
  public static class UseThreadLocal {

    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
          @Override
          protected Integer initialValue() {
            // 初始化
            return 1;
          }
        };

    /** 运行3个线程，对同一个threadLocal修改值 */
    void StartThreadArray() {
      Thread[] runs = new Thread[3];
      for (int i = 0; i < runs.length; i++) {
        runs[i] = new Thread(new TestThread(i));
        runs[i].start();
      }
    }

    /** 测试线程，线程的工作是将ThreadLocal变量的值变化，并写回， 看看线程之间是否会互相影响 */
    public static class TestThread implements Runnable {
      int id;
      public TestThread(int id) {
        this.id = id;
      }
      public void run() {
        System.out.println(Thread.currentThread().getName() + ":start");
        // Thread 类的成员变量：ThreadLocal.ThreadLocalMap threadLocals
        Integer s = threadLocal.get();
        s = s + id;
        threadLocal.set(s);
        System.out.println(Thread.currentThread().getName() + " :" + threadLocal.get());
        threadLocal.remove();
      }
    }
  }

  /**
   * Demo8： 显式锁 Lock
   *
   * <p>Lock接口和synchronized的比较
   * 我们一般的Java程序是靠synchronized关键字实现锁功能的，使用synchronized关键字将会隐式地获取锁，但是它将锁的获取和释放固化了，也就是先获取再释放。synchronized属于Java语言层面的锁，也被称之为内置锁。
   * synchronized这种机制，一旦开始获取锁，是不能中断的，也不提供尝试获取锁的机制。
   * 而Lock是由Java在语法层面提供的，锁的获取和释放需要我们明显的去获取，因此被称为显式锁。并且提供了synchronized不提供的机制。
   *
   * <p>1，尝试非阳塞地获取锁 当前线程尝试获取锁，如果这一时刻锁没有被其他线程获取到，则成功获取并持有锁
   *
   * <p>2，能被中断地获取顿 与syachronized不同，获取到锁的线程能够响应中断，当优取到镜的线程被中断时，中断异常将会被抛出，同时锁会被释放
   *
   * <p>3，超时获取锁 在指定的截止时间之前获取锁，如果截止时间到了仍旧无法庆取读。则返回
   *
   * <p>
   *
   * <p>Lock接口和核心方法
   *
   * <p>1，void lock() 获取锁，调用该方法当前线程将会获取锁，当锁获得后，从该方法返回
   *
   * <p>2，void lockInterruptiblyO InterruptedException
   * 可中断地获取锁，和lock()方法的不同之处在于该方法会响应中断，即在锁的获取中可以中断当前线程
   *
   * <p>3，boolean tryLock() 尝试非阻塞的获取锁，调用该方法后立刻返回，如果能够获取则返回true,否则返回false
   *
   * <p>4，boolean tryLock(long time, TimeUnit unit) throws InterruptedException
   * 超时的获取锁，当前线程在以下3种情况下会返回: ①当前线程在超时时间内获得了锁 ②当前线程在超时时间内被中断 ③超时时间结束，返回false
   *
   * <p>5，void unlock() 释放锁
   *
   * <p>
   *
   * <p>可重入锁：synchronized关键字隐式的支持重进入，比如一个synchronized修饰的递归方法，在方法执行时，执行线程在获取了锁之后仍能连续多次地获得该锁。ReentrantLock在调用lock()方法时，已经获取到锁的线程，能够再次调用lock()方法获取锁而不被阻塞。
   *
   * <p>排他锁:（synchronized和ReentrantLock）基本都是排他锁，这些锁在同一时刻只允许一个线程进行访问
   *
   * <p>公平和非公平锁
   *
   * <p>如果在时间上，先对锁进行获取的请求一定先被满足，那么这个锁是公平的，反之，是不公平的
   *
   * <p>公平的获取锁，也就是等待时间最长的线程最优先获取锁，也可以说锁获取是顺序的。
   *
   * <p>ReentrantLock提供了一个构造函数，能够控制锁是否是公平的。
   *
   * <p>事实上，公平的锁机制往往没有非公平的效率高。原因是，在恢复一个被挂起的线程与该线程真正开始运行之间存在着严重的延迟。假设线程A持有一个锁,并且线程B请求这个锁。由于这个锁已被线程A持有,因此B将被挂起。当A释放锁时,B将被唤醒,因此会再次尝试获取锁。与此同时,如果C也请求这个锁,那么C很可能会在B被完全唤醒之前获得、使用以及释放这个锁。这样的情况是一种“双赢”的局面:B获得锁的时刻并没有推迟,C更早地获得了锁,并且吞吐量也获得了提高。
   */
  private static void Demo8() throws InterruptedException {
    System.out.println("-----Demo8-----\n\n");
    System.out.println("测试线程间的共享的安全性（两个线程对同一个变量同时累加100次)");

    final LockTestClass lockTestClass = new LockTestClass();
    Runnable runnable =
        new Runnable() {
          @Override
          public void run() {
            for (int i = 0; i < 10000; i++) {
              //                    lockTestClass.addCount1();//没加锁
              lockTestClass.addCount2(); // 显式锁
            }
          }
        };
    Thread threadA = new Thread(runnable);
    Thread threadB = new Thread(runnable);
    threadA.start();
    threadB.start();
    Thread.sleep(50);
    System.out.println("多线程操作后的数量：count=" + lockTestClass.count); // 20000

    System.out.println("测试线程间的协作-显示锁实现（快递业务：公里数或城市发生了变化通知用户）");
    final ExpressageByLock express = new ExpressageByLock(0, ExpressageByLock.CITY_HAI);
    for (int i = 0; i < 3; i++) {
      // 三个线程
      new Thread(
              new Runnable() {
                @Override
                public void run() {
                  // 检查里程数变化的线程,不满足条件，线程一直等待
                  express.waitKm();

                  // 检查地点变化的线程,不满足条件，线程一直等待
                  express.waitSite();
                }
              })
          .start();
    }

    // 5秒钟之后修改条件
    Thread.sleep(5000);
    // 快递里程数变化
    express.changeKm();
    // 快递城市变化
    //      express.changeSite();

  }

  /** 使用显示锁的范式 */
  public static class LockTestClass {

    private int count = 0;

    private Lock lock = new ReentrantLock(true);

    /** 未加锁 */
    void addCount1() {
      count++;
    }

    /** 显示锁 在finally块中释放锁，目的是保证在获取到锁之后，最终能够被释放 */
    void addCount2() {
      lock.lock();
      try {
        count++;
      } finally {
        lock.unlock();
      }
    }
  }

  /** 快递业务测试类（显示锁 ReentrantLock 实现） */
  public static class ExpressageByLock {

    public static String CITY_HAI = "ShangHai";
    public static String CITY_JING = "BeiJing";
    // 可重入锁
    private Lock lock = new ReentrantLock();
    //
    private Condition kmCond = lock.newCondition();
    //
    private Condition siteCond = lock.newCondition();
    // 快递运输里程数
    private int km;
    // 快递到达地点
    private String site;

    public ExpressageByLock(int km, String site) {
      this.km = km;
      this.site = site;
    }

    /** 检查公里数变化的等待方 */
    public void waitKm() {
      lock.lock();
      try {
        while (this.km < 100) {
          try {
            System.out.println("awaitKm");
            kmCond.await();
            System.out.println("检查公里数 thread[" + Thread.currentThread().getName() + "] 被通知了");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      } finally {
        lock.unlock();
      }
      System.out.println("公里数变化了： " + this.km);
    }

    /** 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理 */
    public void changeKm() {
      lock.lock();
      try {
        this.km = 101;
        //        kmCond.signal();
        kmCond.signalAll();
      } finally {
        lock.unlock();
      }
    }

    /** 检查城市变化的等待方 */
    public void waitSite() {
      lock.lock();
      try {
        while (CITY_JING.equals(this.site)) {
          try {
            System.out.println("awaitSite");
            siteCond.await();
            System.out.println("检查地点 thread[" + Thread.currentThread().getName() + "] 被通知了");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      } finally {
        lock.unlock();
      }

      System.out.println("地点改变了： " + this.site);
    }

    /** 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理 */
    public void changeSite() {
      lock.lock();
      try {
        this.site = CITY_JING;
        //      siteCond.signal();
        siteCond.signalAll();
      } finally {
        lock.unlock();
      }
    }
  }

  /**
   * Demo9：读写锁 ReentrantReadWriteLock 或者 synchronized
   *
   * <p>之前提到锁（synchronized和ReentrantLock）基本都是排他锁，这些锁在同一时刻只允许一个线程进行访问，而读写锁在同一时刻可以允许多个读线程访问，但是在写线程访问时，所有的读线程和其他写线程均被阻塞。读写锁维护了一对锁，一个读锁和一个写锁，通过分离读锁和写锁，使得并发性相比一般的排他锁有了很大提升。
   *
   * <p>一般情况下，读写锁的性能都会比排它锁好，因为大多数场景读是多于写的。在读多于写的情况下，读写锁能够提供比排它锁更好的并发性和吞吐量
   */
  private static void Demo9() {
    System.out.println("-----Demo9-----\n\n");
    System.out.println("测试读写锁（模拟商品销售和入库）");
    final int readWriteRatio = 10; // 读写线程的比例
    final int minReadCount = 3; // 最少线程数
    GoodsInfo goodsInfo = new GoodsInfo(100000, 10000);
    //    final GoodsService goodsService = new UseRwLock(goodsInfo);
    final GoodsService goodsService = new UseSyn(goodsInfo);

    for (int i = 0; i < minReadCount; i++) {
      Thread setT =
          new Thread(
              new Runnable() {
                @Override
                public void run() {
                  long start = System.currentTimeMillis();
                  for (int i = 0; i < 100; i++) { // 操作100次
                    goodsService.getNum();
                  }
                  System.out.println(
                      Thread.currentThread().getName()
                          + "读取商品数据耗时："
                          + (System.currentTimeMillis() - start)
                          + "ms");
                }
              });
      for (int j = 0; j < readWriteRatio; j++) {
        Thread getT =
            new Thread(
                new Runnable() {
                  @Override
                  public void run() {
                    long start = System.currentTimeMillis();
                    Random r = new Random();
                    for (int i = 0; i < 10; i++) { // 操作10次
                      second(1);
                      goodsService.setNum(r.nextInt(10));
                    }
                    System.out.println(
                        Thread.currentThread().getName()
                            + "写商品数据耗时："
                            + (System.currentTimeMillis() - start)
                            + "ms---------");
                  }
                });
        getT.start();
      }
      milliSeconds(2);
      setT.start();
    }
  }

  /** 商品的实体类 */
  public static class GoodsInfo {
    private double totalMoney; // 总销售额
    private int storeNumber; // 库存数
    private int price = 25; // 单价

    public GoodsInfo(int totalMoney, int storeNumber) {
      this.totalMoney = totalMoney;
      this.storeNumber = storeNumber;
    }

    public void changeNumber(int sellNumber) {
      this.totalMoney += sellNumber * price;
      this.storeNumber -= sellNumber;
    }
  }

  /** 商品的服务的接口 */
  public interface GoodsService {

    public GoodsInfo getNum(); // 获得商品的信息

    public void setNum(int number); // 设置商品的数量
  }

  /** 读写锁实现 */
  public static class UseRwLock implements GoodsService {

    private GoodsInfo goodsInfo;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock getLock = lock.readLock(); // 读锁
    private final Lock setLock = lock.writeLock(); // 写锁

    public UseRwLock(GoodsInfo goodsInfo) {
      this.goodsInfo = goodsInfo;
    }

    @Override
    public GoodsInfo getNum() {
      getLock.lock();
      try {
        milliSeconds(2);
        return this.goodsInfo;
      } finally {
        getLock.unlock();
      }
    }

    @Override
    public void setNum(int number) {
      setLock.lock();
      try {
        milliSeconds(2);
        goodsInfo.changeNumber(number);
      } finally {
        setLock.unlock();
      }
    }
  }

  /** 同步锁实现 */
  public static class UseSyn implements GoodsService {

    private GoodsInfo goodsInfo;

    public UseSyn(GoodsInfo goodsInfo) {
      this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfo getNum() {
      milliSeconds(2);
      return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) {
      milliSeconds(2);
      goodsInfo.changeNumber(number);
    }
  }

  /**
   * Demo10：线程池
   *
   * <p>合理地使用线程池能够带来3个好处。
   *
   * <p>1,降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
   *
   * <p>2,提高响应速度,当任务到达时，任务可以不需要等到线程创建就能立即执行。
   *
   * <p>3,提高线程的可管理性,线程是稀缺资源，如果无限制地创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一分配、调优和监控。
   *
   * <p>
   *
   * <p>JDK中的线程池和工作机制 线程池的创建各个参数含义
   *
   * <p>public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit
   * unit,BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler
   * handler)
   *
   * <p>corePoolSize 线程池中的核心线程数，当提交一个任务时，线程池创建一个新线程执行任务，直到当前线程数等于corePoolSize；
   * 如果当前线程数为corePoolSize，继续提交的任务被保存到阻塞队列中，等待被执行；
   * 如果执行了线程池的prestartAllCoreThreads()方法，线程池会提前创建并启动所有核心线程。
   *
   * <p>maximumPoolSize 线程池中允许的最大线程数。如果当前阻塞队列满了，且继续提交任务，则创建新的线程执行任务，前提是当前线程数小于maximumPoolSize
   *
   * <p>keepAliveTime 线程空闲时的存活时间，即当线程没有任务执行时，继续存活的时间。默认情况下，该参数只在线程数大于corePoolSize时才有用
   *
   * <p>TimeUnit keepAliveTime的时间单位
   *
   * <p>workQueue
   * workQueue必须是BlockingQueue阻塞队列。当线程池中的线程数超过它的corePoolSize的时候，线程会进入阻塞队列进行阻塞等待。通过workQueue，线程池实现了阻塞功能
   *
   * <p>RejectedExecutionHandler（饱和策略）
   * 线程池的饱和策略，当阻塞队列满了，且没有空闲的工作线程，如果继续提交任务，必须采取一种策略处理该任务，线程池提供了4种策略： （1）AbortPolicy：直接抛出异常，默认策略；
   * （2）CallerRunsPolicy：用调用者所在的线程来执行任务； （3）DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
   * （4）DiscardPolicy：直接丢弃任务； 当然也可以根据应用场景实现RejectedExecutionHandler接口，自定义饱和策略，如记录日志或持久化存储不能处理的任务。
   *
   * <p>
   *
   * <p>线程池的工作机制 1）如果当前运行的线程少于corePoolSize，则创建新线程来执行任务（注意，执行这一步骤需要获取全局锁）。
   * 2）如果运行的线程等于或多于corePoolSize，则将任务加入BlockingQueue。
   * 3）如果无法将任务加入BlockingQueue（队列已满），则创建新的线程来处理任务（注意，执行这一步骤需要获取全局锁）。
   * 4）如果创建新线程将使当前运行的线程超出maximumPoolSize，任务将被拒绝，并调用RejectedExecutionHandler.rejectedExecution()方法。
   *
   * <p>
   *
   * <p>合理配置线程池 要想合理地配置线程池，就必须首先分析任务特性，可以从以下几个角度来分析。 •任务的性质：CPU密集型任务、IO密集型任务和混合型任务。 •任务的优先级：高、中和低。
   * •任务的执行时间：长、中和短。 •任务的依赖性：是否依赖其他系统资源，如数据库连接。 性质不同的任务可以用不同规模的线程池分开处理。
   * CPU密集型任务应配置尽可能小的线程，如配置Ncpu(当前设备的CPU个数)+1个线程的线程池。
   * IO密集型任务线程并不是一直在执行任务，则应配置尽可能多的线程，如2*Ncpu(当前设备的CPU个数)。
   * 混合型的任务，如果可以拆分，将其拆分成一个CPU密集型任务和一个IO密集型任务，只要这两个任务执行的时间相差不是太大，那么分解后执行的吞吐量将高于串行执行的吞吐量。如果这两个任务执行时间相差太大，则没必要进行分解。
   * 可以通过Runtime.getRuntime().availableProcessors()方法获得当前设备的CPU个数。
   */
  private static void Demo10() throws ExecutionException, InterruptedException {
    System.out.println("-----Demo10-----\n\n");
    System.out.println("测试线程池的使用");
    ThreadPoolExecutor pool =
        new ThreadPoolExecutor(
            2,
            4,
            3,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10),
            new ThreadFactory() {
              @Override
              public Thread newThread(Runnable runnable) {
                return newThread(runnable);
              }
            },
            new ThreadPoolExecutor.DiscardOldestPolicy());
    pool.prestartAllCoreThreads(); // 线程池会提前创建并启动所有核心线程。
    for (int i = 0; i < 6; i++) {
      //      RunnableWorker worker = new RunnableWorker("RunnableWorker_" + i);
      //      pool.execute(worker);
    }
    for (int i = 0; i < 6; i++) {
      //      CallWorker callWorker = new CallWorker("CallWorker_" + i);
      //      Future<String> result = pool.submit(callWorker);
      //      System.out.println(result.get());
    }
    pool.shutdown();

    System.out.println("测试自己实现的线程池的使用");
    // 创建3个线程的线程池
    MyThreadPool t = new MyThreadPool(3, 0);
    t.execute(new MyTask("testA"));
    t.execute(new MyTask("testB"));
    t.execute(new MyTask("testC"));
    t.execute(new MyTask("testD"));
    t.execute(new MyTask("testE"));
    System.out.println(t);
    Thread.sleep(10000);
    t.destroy(); // 所有线程都执行完成才destory
    System.out.println(t);
  }

  // 自己的线程池任务类
  static class MyTask implements Runnable {

    private String name;
    private Random r = new Random();

    public MyTask(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public void run() { // 执行任务
      try {
        Thread.sleep(r.nextInt(1000) + 2000);
      } catch (InterruptedException e) {
        System.out.println(
            Thread.currentThread().getId()
                + " sleep InterruptedException:"
                + Thread.currentThread().isInterrupted());
      }
      System.out.println("任务 " + name + " 完成");
    }
  }

  static class RunnableWorker implements Runnable {
    private String taskName;
    private Random r = new Random();

    public RunnableWorker(String taskName) {
      this.taskName = taskName;
    }

    public String getName() {
      return taskName;
    }

    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + " process the task : " + taskName);
      SleepTools.ms(r.nextInt(100) * 5);
    }
  }

  static class CallWorker implements Callable<String> {

    private String taskName;
    private Random r = new Random();

    public CallWorker(String taskName) {
      this.taskName = taskName;
    }

    public String getName() {
      return taskName;
    }

    @Override
    public String call() throws Exception {
      System.out.println(Thread.currentThread().getName() + " process the task : " + taskName);
      return Thread.currentThread().getName() + ":" + r.nextInt(100) * 5;
    }
  }

  /** 类说明：自己线程池的实现 */
  public static class MyThreadPool {
    // 线程池中默认线程的个数为5
    private static int WORK_NUM = 5;
    // 队列默认任务个数为100
    private static int TASK_COUNT = 100;

    // 工作线程组
    private WorkThread[] workThreads;

    // 任务队列，作为一个缓冲
    private final BlockingQueue<Runnable> taskQueue;
    private final int worker_num; // 用户在构造这个池，希望的启动的线程数

    // 创建具有默认线程个数的线程池
    public MyThreadPool() {
      this(WORK_NUM, TASK_COUNT);
    }

    // 创建线程池,worker_num为线程池中工作线程的个数
    public MyThreadPool(int worker_num, int taskCount) {
      if (worker_num <= 0) worker_num = WORK_NUM;
      if (taskCount <= 0) taskCount = TASK_COUNT;
      this.worker_num = worker_num;
      taskQueue = new ArrayBlockingQueue<>(taskCount);
      workThreads = new WorkThread[worker_num];
      for (int i = 0; i < worker_num; i++) {
        workThreads[i] = new WorkThread();
        workThreads[i].start();
      }
      // 获得当前设备的CPU个数。
      // int count = Runtime.getRuntime().availableProcessors();
      // Runtime.getRuntime().availableProcessors()*2
    }

    // 执行任务,其实只是把任务加入任务队列，什么时候执行有线程池管理器决定
    public void execute(Runnable task) {
      try {
        taskQueue.put(task);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // 销毁线程池,该方法保证在所有任务都完成的情况下才销毁所有线程，否则等待任务完成才销毁
    public void destroy() {
      // 工作线程停止工作，且置为null
      System.out.println("ready close pool.....");
      for (int i = 0; i < worker_num; i++) {
        workThreads[i].stopWorker();
        workThreads[i] = null; // help gc
      }
      taskQueue.clear(); // 清空任务队列
    }

    // 覆盖toString方法，返回线程池信息：工作线程个数和已完成任务个数
    @Override
    public String toString() {
      return "WorkThread number:" + worker_num + "  wait task number:" + taskQueue.size();
    }

    /** 内部类，工作线程 */
    private class WorkThread extends Thread {

      @Override
      public void run() {
        Runnable r = null;
        try {
          while (!isInterrupted()) {
            r = taskQueue.take();
            if (r != null) {
              System.out.println(getId() + " ready exec :" + r);
              r.run();
            }
            r = null; // help gc;
          }
        } catch (Exception e) {
          // TODO: handle exception
        }
      }

      public void stopWorker() {
        interrupt();
      }
    }
  }
}
