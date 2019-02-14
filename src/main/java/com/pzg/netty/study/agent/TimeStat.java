package com.pzg.netty.study.agent;

import java.sql.SQLOutput;

/**
 * @author PENGZHIGANG
 * @date
 */
public class TimeStat {
    static  ThreadLocal<Long> t = new ThreadLocal<>();

    public static  void start(){
        t.set(System.currentTimeMillis());
    }

    public  static void end(){
        long time  = System.currentTimeMillis() - t.get();
        System.out.println(Thread.currentThread().getStackTrace()[2] + "spend");
        System.out.println(time);
    }

}
