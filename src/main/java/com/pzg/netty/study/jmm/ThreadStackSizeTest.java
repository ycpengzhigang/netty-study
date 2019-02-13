package com.pzg.netty.study.jmm;

import java.util.concurrent.CountDownLatch;
/**
 *	测试线程栈的大小
 * @author PENGZHHIGANG
 *
 */
public class ThreadStackSizeTest extends Thread {
	CountDownLatch cd1 = new CountDownLatch(1);
	
	public void run() {
		try {
			cd1.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		for (int i=0;;i ++) {
			new Thread(new ThreadStackSizeTest()).start();
			System.out.println("count = " + i);
		}
	}
}
