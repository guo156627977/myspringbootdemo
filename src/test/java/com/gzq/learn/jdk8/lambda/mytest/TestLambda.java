package com.gzq.learn.jdk8.lambda.mytest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-05-11 15:25.
 */
public class TestLambda {

    //Predicate<T> 断言型接口：
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "atguigu", "Lambda", "www", "ok");
        List<String> strList = filterStr(list, (s) -> s.length() > 3);
        for (String str : strList) {
            System.out.println("str = " + str);
        }
    }

    //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        ArrayList<String> newList = new ArrayList<>();
        for (String str : list) {
            if (pre.test(str)){
                newList.add(str);
            }
        }
        return newList;
    }

    //Function<T, R> 函数型接口：
    @Test
    public void test3() {
        String newStr1 = strHandler("\t\t\t  测试函数性接口  ", (str) -> str.trim());
        System.out.println("newStr1 = " + newStr1);

        String newStr2 = strHandler("0123456", (str) -> str.substring(2, 5));
        System.out.println("newStr2 = " + newStr2);
    }

    //需求：用于处理字符串
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    //Supplier<T> 供给型接口 :
    @Test
    public void test() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer integer : numList) {
            System.out.println(integer);
        }

    }

    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        return list;
    }

    //Consumer<T> 消费型接口 :
    @Test
    public void test2() {
        happy(2000, (x) -> System.out.println("今天花了" + x + "元"));
    }

    public void happy(double money, Consumer<Double> com) {
        com.accept(money);
    }

    @Test
    public void test1() {
        int num = 0;
        Runnable r1 = new Runnable() {
            public void run() {
                System.out.println("Hello Lambda!");
            }
        };
        r1.run();
        System.out.println("-----------------------------");
        Runnable r2 = () -> System.out.println("Hello Lambda!");
        r2.run();
    }
}
