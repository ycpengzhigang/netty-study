package com.pzg.netty.study.jmm;

/**
 * @author PENGZHIGANG
 * @date
 */
public class HeapAlloc {
    public static void main(String[] args) {
        System.out.println("maxMemory =");
        System.out.println(Runtime.getRuntime().maxMemory() + "byte");
        System.out.println("free mem = ");
        System.out.println(Runtime.getRuntime().freeMemory() + "byte");
        System.out.println("total mem = ");
        System.out.println(Runtime.getRuntime().totalMemory() + "byte");

        byte[] b = new byte[1024 * 1024];
        System.out.println("分配了1M空间给数组");

        System.out.println("maxMemory =");
        System.out.println(Runtime.getRuntime().maxMemory() + "byte");
        System.out.println("free mem = ");
        System.out.println(Runtime.getRuntime().freeMemory() + "byte");
        System.out.println("total mem = ");
        System.out.println(Runtime.getRuntime().totalMemory() + "byte");

        b = new byte[4 *1024 * 1024];
        System.out.println("分配了4M空间给数组");

        System.out.println("maxMemory =");
        System.out.println(Runtime.getRuntime().maxMemory() + "byte");
        System.out.println("free mem = ");
        System.out.println(Runtime.getRuntime().freeMemory() + "byte");
        System.out.println("total mem = ");
        System.out.println(Runtime.getRuntime().totalMemory() + "byte");
    }



}
