package com.mmall.concurrency.thread;

/**
 * @ClassName com.mmall.concurrency.thread
 * @Description: 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5,
 * 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15.
 * 接着再由线程1打印16,17,18,19,20....以此类推, 直到打印到75. 程序的输出结果应该为:
 * @Author chenjie
 * @Date Created in 星期五 2018/6/29
 * @Version 1.0
 */
public class Demo4 {
    static class MyWork implements Runnable {

        private static int count = 0;

        private int id;

        public MyWork(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (count < 75) {
                synchronized (Demo4.class) {
                    if (count / 5 % 3 + 1 == id) {//关键点，确定count变化值时，所需要执行的线程 即某一值该为哪个线程执行
                        System.out.println("线程 " + Thread.currentThread().getName() + " start");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Thread.currentThread().getName() + "=" + ++count);
                        }
                        Demo4.class.notifyAll();
                        System.out.println("线程 " + Thread.currentThread().getName() + " end");
                    } else {
                        try {
                            Demo4.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyWork(1), "线程1");
        Thread t2 = new Thread(new MyWork(2), "线程2");
        Thread t3 = new Thread(new MyWork(3), "线程3");
        t1.start();
        t2.start();
        t3.start();

    }
}
