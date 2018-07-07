package com.mmall.concurrency.thread.procum;

/**
 * @ClassName com.mmall.concurrency.thread.procum
 * @Description:
 * @Author chenjie
 * @Date Created in 星期六 2018/6/30
 * @Version 1.0
 */
public class Consumer implements Runnable {
    private PublicBox publicBox;

    public Consumer(PublicBox publicBox) {
        this.publicBox = publicBox;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                publicBox.decreace();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
