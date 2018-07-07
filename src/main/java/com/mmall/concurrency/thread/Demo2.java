package com.mmall.concurrency.thread;

/**
 * @ClassName com.mmall.concurrency.thread
 * @Description: 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行
 * @Author chenjie
 * @Date Created in 星期五 2018/6/29
 * @Version 1.0
 */
public class Demo2 {
    static class MyWork implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyWork(), "线程T1");
        Thread t2 = new Thread(new MyWork(), "线程T2");
        Thread t3 = new Thread(new MyWork(), "线程T3");

        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
