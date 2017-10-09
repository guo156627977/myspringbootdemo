package com.gzq.learn.juc.exer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-05 10:29.
 */
public class TestLock {

    public static void main(String[] args) {
        SellTicket sellTicket = new SellTicket();
        new Thread(sellTicket, "1号窗口").start();
        new Thread(sellTicket, "2号窗口").start();
        new Thread(sellTicket, "3号窗口").start();
    }
}


class SellTicket implements Runnable {

    private int ticket = 100;

    private Lock lock = new ReentrantLock();

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

            while (true) {
                lock.lock();//上锁
                try {
                    if (ticket > 0) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " 完成售票，余票为：" + --ticket);
                    }else {
                        break;
                    }
                } finally {
                    lock.unlock();//释放锁
                }
            }

    }
}