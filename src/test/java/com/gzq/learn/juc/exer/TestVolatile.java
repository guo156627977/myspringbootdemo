package com.gzq.learn.juc.exer;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-04 11:19.
 */
public class TestVolatile {
    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        new Thread(testDemo).start();
        while (true){
            if (testDemo.isFlag()) {
                System.out.println("------------------");
                break;
            }
        }
    }


}

class TestDemo implements Runnable {

    public TestDemo() {
    }

    private volatile boolean flag = false;
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
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag = " + flag);

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
