package com.pzg.netty.study.jmm;

/**
 * 测试栈的溢出
 *
 * @author PENGZHIGANG
 * @date
 */
public class TestStackDeep {
    private static int count = 0;

    /**
     * 使用的是递归
     */
    private static void recursion() {
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
            recursion();
        } catch (Throwable e) {// 注意这里应该使用的是Throwable
            System.out.println("deep of calling = " + count);
            e.printStackTrace();
        }
    }

}
