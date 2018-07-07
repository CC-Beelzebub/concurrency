package com.mmall.concurrency.thread.procum;

/**
 * @ClassName com.mmall.concurrency.thread.procum
 * @Description:
 * @Author chenjie
 * @Date Created in 星期六 2018/6/30
 * @Version 1.0
 */
public class Producer implements Runnable {
    private PublicBox publicBox;

    public Producer(PublicBox publicBox) {
        this.publicBox = publicBox;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                publicBox.increace();
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
