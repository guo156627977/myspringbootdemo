package com.gzq.learn.juc.exer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-06 11:23.
 */
public class TestABCAlternatePrint {
    public static void main(String[] args) {

        AlternateDemo ad = new AlternateDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    ad.LoopA(i);
                }
            }
        }, "线程A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    ad.LoopB(i);
                }
            }
        }, "线程B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    ad.LoopC(i);
                }
            }
        }, "线程C").start();
    }
}

class AlternateDemo {
    private int number = 1;  //标识当前线程的标记

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void LoopA(int Loops) {
        lock.lock();
        try {
            while (number != 1) {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " :" + "第" + (Loops + 1) + "轮第" + (i+1) + "个A");

            }
            //设置标记
            number = 2;
            condition2.signal();

        } finally {
            lock.unlock();
        }
    }

    public void LoopB(int Loops) {
        lock.lock();
        try {
            while (number != 2) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName() + " :" + "第" + (Loops+1) + "轮第" + (i + 1) + "个B ");


            }
            //设置标记
            number = 3;
            condition3.signal();

        } finally {
            lock.unlock();
        }
    }

    public void LoopC(int Loops) {
        lock.lock();
        try {
            while (number != 3) {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i <15; i++) {
                System.out.println(Thread.currentThread().getName() + " :" +"第"+ (Loops + 1)+ "轮第"+ (i + 1) +"个C ");

            }
            //设置标记
            number = 1;
            condition1.signal();

        } finally {
            lock.unlock();
        }
    }

}
