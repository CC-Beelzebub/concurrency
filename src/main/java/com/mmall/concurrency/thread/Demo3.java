package com.mmall.concurrency.thread;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

/**
 * @ClassName com.mmall.concurrency.thread
 * @Description: 设计4个线程、实现2个线程对i加一，2个线程对i进行减一
 * 效果：
 * Thread-0加1
 * Thread-1减0
 * Thread-2加1
 * Thread-3减0
 *
 * 分析：即加执行两次，减执行两次
 * @Author chenjie
 * @Date Created in 星期五 2018/6/29
 * @Version 1.0
 */
public class Demo3 {
    private int i = 0;

    private synchronized void add() {
        i++;
        System.out.println(Thread.currentThread().getName() + "对 i" + "做加法i，当前值为:" + i);
    }

    private synchronized void sub() {
        i--;
        System.out.println(Thread.currentThread().getName() + "对 i" + "做减法，但前值为:" + i);

    }

     class AddWork implements Runnable {
        @Override
        public void run() {
            add();
        }
    }

    class SubWork implements Runnable {
        @Override
        public void run() {
            sub();
        }
    }

    public static void main(String[] args) {
        Demo3 demo3 = new Demo3();
        AddWork addWork =  demo3.new AddWork();
        SubWork subWork = demo3.new SubWork();

        for(int i=0 ;i<2 ;i++){
            Thread t1 = new Thread(addWork);
            t1.start();
            Thread t2 = new Thread(subWork);
            t2.start();
        }
    }

}
