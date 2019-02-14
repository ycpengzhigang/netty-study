package com.pzg.netty.study.agent;

/**
 * @author PENGZHIGANG
 * @date
 */
public class Account {
    public void operation(){
        System.out.println("operaion...");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
