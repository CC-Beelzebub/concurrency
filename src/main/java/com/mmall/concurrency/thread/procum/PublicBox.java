package com.mmall.concurrency.thread.procum;

/**
 * @ClassName com.mmall.concurrency.thread.procum
 * @Description:
 * @Author chenjie
 * @Date Created in 星期六 2018/6/30
 * @Version 1.0
 */
public class PublicBox {
    private int apple = 0;
    private int max_size = 5;

    public synchronized void increace() {
        while (apple > max_size) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        apple++;
        System.out.println("生产者生成苹果成功，当前苹果数:" + apple);
        this.notify();
    }

    public synchronized void decreace() {
        while (apple == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        apple--;
        System.out.println("消费者消费苹果成功，当前苹果数:" + apple);
        this.notify();
    }

    public  static void main(String[] args){
        PublicBox publicBox = new PublicBox();
        new Thread(new Producer(publicBox)).start();
        new Thread(new Consumer(publicBox)).start();

    }
}
