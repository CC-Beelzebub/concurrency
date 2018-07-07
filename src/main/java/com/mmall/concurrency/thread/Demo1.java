package com.mmall.concurrency.thread;

/**
 * @ClassName com.mmall.concurrency.thread
 * @Description: 题目一：子线程循环2次，主线程循环2次，然后子线程循环2次，主线程循环2次，这样循环10次；
 * @Author chenjie
 * @Date Created in 星期五 2018/6/29
 * @Version 1.0
 */
public class Demo1 {
    static class MyWork {
        private volatile boolean flag = true;

        private synchronized void subRun(int number) {
            while (!flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Sub 第" + number + "次循环 start.");
            for (int i = 0; i < 2; i++) {
                System.out.println("Sub run----------- " + i);
            }
            System.out.println("Sub 第" + number + "次循环 end.");
            this.flag = false;
            this.notify();

        }

        private synchronized void mainRun(int number) {
            while (flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Main 第" + number + "次循环 start.");
            for (int i = 0; i < 2; i++) {
                System.out.println("Main run----------- " + i);
            }
            System.out.println("Main 第" + number + "次循环 end.");
            this.flag = true;
            this.notify();
        }

    }

    public static void main(String[] args) {
        MyWork myWork = new MyWork();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    myWork.subRun(i);
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            myWork.mainRun(i);
        }
    }

}
