package com.pzg.netty.study.jmm;

/**
 * 暂时先不测
 * @author Administrator
 *
 */
public class TestStackDeep {
	private static int count = 0;
	
	public static void recursion(long a, long b, long c){
		long  e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
		count++;
		recursion(a, b ,c);
	}
	
	public static void recursion(){
		count++;
		recursion();
	}

	/**
	 * ctrl + alter + t 异常的快捷键
	 * ctrl + alter + l 进行格式化代码
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			recursion(0L, 0L ,0L);
		} catch (Throwable e) {
			System.out.println("deep of calling = " + count);
			e.printStackTrace();
		}
	}
}
