package com.gzq.learn.designmode.singleton;

/**
 * ${DESCRIPTION}
 * 懒汉式：只有用到时才会实例化
 * @author think
 * @created 2017-03-31 16:29.
 */
public class Singleton1 {
    // 利用一个静态变量来记录Singleton的唯一实例。
    public static Singleton1 uniqueInstance;

    // 把构造器声明为私有的，只有Singleton类内才可以调用构造器。
    public Singleton1() {
    }

    // 用getInstance()方法实例化对象，并返回这个实例。
    // 这种方法在多线程中就是灾难，可能同一时间取到多份
    public static Singleton1 getInstance1() {
        // 如果uniqueInstance是空的，表示还没有创建实例。
        if (uniqueInstance == null) {
            // 如果uniqueInstance是空的，我们就利用私有的构造器产生一个Singleton实例并
            // 把它赋值给uniqueInstance静态变量中。请注意，如果我们不需要这个实例，它就
            // 永远不会产生。这就是“延迟实例化”
            uniqueInstance = new Singleton1();
        }
        return uniqueInstance;
    }

    // 通过增加synchronized关键字到getInstance()方法中，我们
    // 迫使每个线程在进入这个方法之前，要先等候别的线程离开该方法。
    // 也就是说，不会有两个线程可以同时进入这个方法。
    // 但是同步会降低性能
    public static synchronized Singleton1 getInstance2() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton1();
        }
        return uniqueInstance;
    }
}
