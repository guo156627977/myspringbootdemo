package com.gzq.learn.jdk8.stream.my;

import com.gzq.learn.jdk8.lambda.demo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-14 11:08.
 */
public class TestStreamAPI1 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    @Test
    public void test1() {
        emps.stream().map(employee -> employee.getName()).forEach(System.out::println);
        System.out.println("---------------");
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        strList.stream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println("---------------------");
        strList.stream().map(TestStreamAPI1::filterCharacter).forEach(characterStream -> characterStream.forEach(System.out::println));
        System.out.println("---------------------");
        strList.stream().flatMap(TestStreamAPI1::filterCharacter).forEach(System.out::println);

    }


    public static Stream<Character> filterCharacter(String string) {
        ArrayList<Character> list = new ArrayList<>();
        for (char c : string.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    //排序
    @Test
    public void test2() {
        //自然排序
     emps.stream().map(Employee::getName).sorted().forEach(System.out::println);
     //定制排序
        emps.stream().sorted((o1, o2) -> {
            if (o1.getAge()==o2.getAge()){
                return o1.getName().compareTo(o2.getName());
            }else {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        }).forEach(System.out::println);
    }
}
