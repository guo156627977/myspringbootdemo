package com.gzq.learn.jdk8.methodsOfInterface;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-26 14:48.
 */
public class SubClass2 implements MyFun,MyInterface {
    @Override
    public String getName() {
        //return MyFun.super.getName();
        return MyInterface.super.getName();
    }
}
