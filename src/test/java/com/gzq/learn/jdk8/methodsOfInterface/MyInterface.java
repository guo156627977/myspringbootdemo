package com.gzq.learn.jdk8.methodsOfInterface;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-26 14:49.
 */
public interface MyInterface {
    default String getName() {
        return "MyInterface中的方法";
    }
}
