package com.pzg.netty.study.agent;

/**
 * java agent 探针技术
 *
 * @author PENGZHIGANG
 * @date
 */
public class RunLoopAccountMain {
    public static void main(String[] args) {
           Account account = new Account();
           while (true) {
               account.operation();
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
    }
}
