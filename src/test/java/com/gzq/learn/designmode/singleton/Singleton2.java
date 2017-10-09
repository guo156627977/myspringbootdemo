package com.gzq.learn.designmode.singleton;

/**
 * ${DESCRIPTION}
 * 饿汉式：在类加载时就创建实例
 * @author think
 * @created 2017-03-31 16:46.
 */
public class Singleton2 {
    // 在静态初始化器中创建单件。这段代码保证了线程安全
    private static Singleton2 uniqueInstance = new Singleton2();

    private Singleton2() {
    }

    public static Singleton2 getUniqueInstance() {
        return uniqueInstance;
    }
}
