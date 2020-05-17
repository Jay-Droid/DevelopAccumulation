package com.jay.java.多线程.concurrent.vola;

import com.jay.java.多线程.concurrent.tools.SleepTools;
/**
 * @author Mark老师 享学课堂 https://enjoy.ke.qq.com 往期课程咨询芊芊老师 QQ：2130753077 VIP课程咨询 依娜老师 QQ：2133576719
 *     类说明：演示Volatile的提供的可见性
 */
public class VolatileCase {
  // 适用于多读一写的情况
  private static volatile boolean ready; // 可见性，未保证原子性不安全
  //  private static boolean ready;
  private static int number;

  private static class PrintThread extends Thread {
    @Override
    public void run() {
      System.out.println("PrintThread is running.......");
      while (!ready) ;
      System.out.println("number = " + number);
    }
  }

  public static void main(String[] args) {
    new PrintThread().start();
    SleepTools.second(1);
    number = 51;
    ready = true;
    SleepTools.second(5);
    System.out.println("main is ended!");
  }
}
