package com.pzg.netty.study.jmm;

public class OnStackTest {
	public static class User {
		public int id = 0;
		public String name ="";
	}
	
	public static void alloc() {
		User u = new User();
		u.id = 5;
		u.name = "geym";
	}
	
	/**
	 * -server的模式下才可以启用逃逸分析
	 * -Xmx10m -Xms10m 
	 * -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB -XX:+EliminateAllocations
	 * 在jdk 1.8的情況下是触发了GC 这里并没有试验成功 使用标量替换
	 * @param args
	 */
	public static void main(String[] args) {
		long b = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			alloc();
		}
		long e = System.currentTimeMillis();
		System.out.println(e - b);
	}
	
}
