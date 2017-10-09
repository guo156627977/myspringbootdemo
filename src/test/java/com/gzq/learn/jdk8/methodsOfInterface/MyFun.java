package com.gzq.learn.jdk8.methodsOfInterface;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-26 14:39.
 */
public interface MyFun {
    default String getName() {
        return "MyFunc默认的方法";
    }
}
