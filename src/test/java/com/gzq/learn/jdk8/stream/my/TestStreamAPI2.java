package com.gzq.learn.jdk8.stream.my;

import com.gzq.learn.jdk8.lambda.demo.Employee;
import com.gzq.learn.jdk8.lambda.demo.Employee.Status;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-15 16:19.
 */
public class TestStreamAPI2 {
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );
//3. 终止操作
    /*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */

    @Test
    public void test1() {
        boolean b = emps.stream().allMatch(employee -> employee.getStatus().equals(Status.BUSY));
        System.out.println("b = " + b);
        boolean b1 = emps.stream().anyMatch(employee -> employee.getStatus().equals(Status.BUSY));
        System.out.println("b1 = " + b1);
        boolean b2 = emps.stream().noneMatch(employee -> employee.getStatus().equals(Status.BUSY));
        System.out.println("b2 = " + b2);

    }

    @Test
    public void test2() {
        Optional<Employee> first = emps.stream().sorted((o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary())).findFirst();
        System.out.println(first.get());
        System.out.println("---------");
        //随机找一个处于忙碌状态的
        Optional<Employee> any = emps.parallelStream().filter(employee -> employee.getStatus().equals(Status.BUSY)).findAny();
        System.out.println(any.get());

    }

    @Test
    public void test3() {
        long count = emps.stream().filter(employee -> employee.getStatus().equals(Status.FREE)).count();
        System.out.println("count = " + count);

        Optional<Double> max = emps.stream().map(employee -> employee.getSalary()).max(Double::compare);
        Optional<Double> min = emps.stream().map(employee -> employee.getSalary()).min(Double::compare);
        System.out.println(min.get());
        System.out.println(max.get());
    }

    //注意：流进行了终止操作后，不能再次使用
    @Test
    public void test4() {
        Stream<Employee> stream = emps.stream().filter(employee -> employee.getStatus().equals(Status.BUSY));
        long count = stream.count();
        System.out.println("count = " + count);
        Optional<Double> max = stream.map(Employee::getSalary).max(Double::compare);
        System.out.println("max.get() = " + max.get());
    }

}
