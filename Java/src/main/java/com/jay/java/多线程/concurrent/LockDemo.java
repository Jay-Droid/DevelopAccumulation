package com.jay.java.多线程.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 *
 *使用显示锁的范式
 */
public class LockDemo {

	private int count = 0;
	private Lock lock = new ReentrantLock(true);
	
	public void incr(){
		lock.lock();
		try{
			count++;
		}finally {
			lock.unlock();
		}
	}

	public synchronized void incr2(){
		count++;
		incr2();
	}

	public static void main(String[] args) {
		LockDemo lockDemo = new LockDemo();
	}

}
