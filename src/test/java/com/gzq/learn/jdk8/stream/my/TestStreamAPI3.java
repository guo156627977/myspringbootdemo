package com.gzq.learn.jdk8.stream.my;

import com.gzq.learn.jdk8.lambda.demo.Employee;
import com.gzq.learn.jdk8.lambda.demo.Employee.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-16 9:37.
 */
public class TestStreamAPI3 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );

    /*
            归约
            reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
         */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println("sum = " + sum);
        System.out.println("------------");
        Optional<Double> reduce = emps.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println("reduce.get() = " + reduce.get());
    }

    /*
    统计所有名字中出现六的次数
     */
    @Test
    public void test2() {
        Optional<Integer> sum = emps.stream().map(Employee::getName).flatMap(TestStreamAPI1::filterCharacter).map(character -> {
            if (character.equals('六')) {
                return 1;
            } else {
                return 0;
            }
        }).reduce(Integer::sum);
        System.out.println("sum = " + sum.get());
    }

    @Test
    public void test3() {
        //将所有名字放入list中
        List<String> list = emps.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println("list = " + list);
        list.forEach(System.out::println);
        System.out.println("-------------");
        Set<Integer> set = emps.stream().map(Employee::getId).collect(Collectors.toSet());
        System.out.println("set = " + set);
        System.out.println("-------------");
        HashSet<String> hashSet = emps.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    @Test
    public void test4() {
        Optional<Double> max = emps.stream().map(Employee::getSalary).collect(Collectors.maxBy(Double::compare));
        System.out.println("max = " + max.get());
        Optional<Double> min = emps.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
        System.out.println("min = " + min.get());
        //薪水总量
        Double sum = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println("sum = " + sum);
        //平均薪水
        Double avg = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("avg = " + avg);
        //统计数量
        Long count = emps.stream().collect(Collectors.counting());
        System.out.println("count = " + count);

        DoubleSummaryStatistics summaryStatistics = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("summaryStatistics = " + summaryStatistics);
    }


    //分组
    @Test
    public void test5() {
        Map<Status, List<Employee>> map = emps.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println("map = " + map);
    }

    //多级分组
    @Test
    public void test6() {
        Map<Status, Map<String, List<Employee>>> map = emps.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors
                .groupingBy((e) -> {
                    if (e.getAge() >= 60) {
                        return "老年";

                    } else if (e.getAge() >= 35) {
                        return "中年";
                    } else {
                        return "青年";
                    }
                })));
        System.out.println("map = " + map);
    }

    @Test
    public void test7() {
        Map<Boolean, List<Employee>> map = emps.stream().collect(Collectors.partitioningBy(o -> o.getSalary() >= 5000));
        System.out.println("map = " + map);
    }

    @Test
    public void test8() {
        String str = emps.stream().map(Employee::getName).collect(Collectors.joining(",", "----", "----"));
        System.out.println("str = " + str);
    }

    @Test
    public void test9() {
        Optional<Double> reduce = emps.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println("reduce.get() = " + reduce.get());
        Optional<Double> collect = emps.stream().map(Employee::getSalary).collect(Collectors.reducing(Double::sum));
        System.out.println("collect.get() = " + collect.get());
    }


}
