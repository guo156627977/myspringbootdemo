package com.gzq.learn.juc.exer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-03 14:22.
 */
public class ThreeThread {

}

class TestCallable implements Callable<Integer> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100000; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        TestCallable testCallable = new TestCallable();
        FutureTask<Integer> futureTask = new FutureTask(testCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer sum = futureTask.get();
            System.out.println("sum = " + sum);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}


class TestRunable implements Runnable {
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
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        TestRunable testRunable = new TestRunable();
        for (int i = 0; i < 10; i++) {

            new Thread(testRunable).start();
        }
    }
}

class TestThread extends Thread {

    int i = 0;

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + "  " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  : " + i);
            if (i == 20) {
                new TestThread().run();
                new TestThread().run();
            }
        }
    }


}
