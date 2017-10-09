package com.gzq.learn.juc.exer;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-05 11:25.
 */
public class TestProductorAndConsumer {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(consumer, "消费者B").start();
        new Thread(productor, "生产者A").start();
        new Thread(productor, "生产者C").start();
        new Thread(consumer, "消费者D").start();
        //new Thread(productor, "生产者E").start();
        //new Thread(consumer, "消费者F").start();

    }
}

//class Clerk {
//    //private volatile int product =0;
//    private int product = 0;
//
//    public synchronized void get() {
//        //if (product >= 1) {
//        while (product >= 1) {//这里要用while循环避免虚假唤醒
//            System.out.println("产品已满");
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//            }
//        }
//        System.out.println(Thread.currentThread().getName() + ":" + ++product);
//        this.notifyAll();
//    }
//
//    public synchronized void sale() {
//        //if (product <= 0) {
//        while (product <= 0) {//这里要用while循环避免虚假唤醒
//            System.out.println("产品缺货");
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//            }
//        }
//        System.out.println(Thread.currentThread().getName() + ":" + --product);
//        this.notifyAll();
//    }
//}
//
//class Productor implements Runnable {
//    private Clerk clerk;
//
//    public Productor(Clerk clerk) {
//        this.clerk = clerk;
//    }
//
//    /**
//     * When an object implementing interface <code>Runnable</code> is used
//     * to create a thread, starting the thread causes the object's
//     * <code>run</code> method to be called in that separately executing
//     * thread.
//     * <p>
//     * The general contract of the method <code>run</code> is that it may
//     * take any action whatsoever.
//     *
//     * @see Thread#run()
//     */
//    @Override
//    public void run() {
//        for (int i = 0; i < 20; i++) {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//            }
//            clerk.get();
//        }
//    }
//}
//
//
//class Consumer implements Runnable {
//
//    private Clerk clerk;
//
//    public Consumer(Clerk clerk) {
//        this.clerk = clerk;
//    }
//
//    /**
//     * When an object implementing interface <code>Runnable</code> is used
//     * to create a thread, starting the thread causes the object's
//     * <code>run</code> method to be called in that separately executing
//     * thread.
//     * <p>
//     * The general contract of the method <code>run</code> is that it may
//     * take any action whatsoever.
//     *
//     * @see Thread#run()
//     */
//    @Override
//    public void run() {
//        for (int i = 0; i < 20; i++) {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//            }
//            clerk.sale();
//        }
//    }
//}
