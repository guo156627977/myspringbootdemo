package com.gzq.learn.jdk8.lambda.mytest;

import com.gzq.learn.jdk8.lambda.demo.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-05-18 10:32.
 */
public class TestMethodRef {

    //1.对象的引用 ::实例方法名
    //
    //     2.类名 ::静态方法名
    //
    //     3.类名 ::实例方法名

    @Test
    public void test7() {

    }

    @Test
    public void test6() {
        Supplier<Employee> sup = () -> new Employee();
        System.out.println(sup.get());
        System.out.println("------------");
        Supplier<Employee> sup2= Employee::new;
        System.out.println(sup2.get());
    }

    @Test
    public void test5() {
        BiPredicate bp1 = (x, y) -> x.equals(y);
        System.out.println(bp1.test("abcde", "abcde"));


        BiPredicate<String, String> bp2 = String::equals;
        System.out.println(bp2.test("abc","abc"));
        System.out.println(bp2.test("abc","abcd"));
    }


    @Test
    public void test4() {
        Comparator<Integer> com = Integer::compare;
        System.out.println(com.compare(3,2));
        Integer.compare(3,2);
        System.out.println(Integer.compare(3, 2));
    }


    @Test
    public void test3() {
        BiFunction<Integer, Integer, Integer> fun = Math::max;
        System.out.println(fun.apply(2,3));
    }



    @Test
    public void test2() {
        Employee emp = new Employee(1, "张三", 24, 9999.99);
        Supplier<Double> sup = emp::getSalary;
        System.out.println(sup.get());
    }
    
    @Test
    public void test1() {
        PrintStream ps = System.out;
        Consumer<String> con = (str) -> ps.println(str);
        con.accept("Hello World!");


        Consumer con2 = System.out::println;
        con2.accept("hello java8");
    }

}
