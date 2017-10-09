package com.gzq.learn.jdk8.methodsOfInterface;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-26 14:46.
 */
public class TestInterface {
    public static void main(String[] args) {
        SubClass1 subClass1 = new SubClass1();
        //类优先
        System.out.println("subClass1.getName() = " + subClass1.getName());

        System.out.println("---------------");
        SubClass2 subClass2 = new SubClass2();
        System.out.println("subClass2.getName() = " + subClass2.getName());

    }
}
