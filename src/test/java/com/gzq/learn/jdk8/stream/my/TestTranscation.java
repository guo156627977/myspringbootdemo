package com.gzq.learn.jdk8.stream.my;

import com.gzq.learn.jdk8.stream.demo.exer.Trader;
import com.gzq.learn.jdk8.stream.demo.exer.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-20 16:06.
 */
public class TestTranscation {
    List<Transaction> transactions = null;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    //1. 找出2012年发生的所有交易， 并按交易额排序（从低到高）
    @Test
    public void test1() {
        transactions.stream()
                .filter(transaction -> transaction.getYear()==2012)
                .sorted((o1, o2) -> Integer.compare(o1.getValue(),o2.getValue()))
                .forEach(System.out::println);
    }

    //2. 交易员都在哪些不同的城市工作过？
    @Test
    public void test2() {
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct().forEach(System.out::println);
    }

    //3. 查找所有来自剑桥的交易员，并按姓名排序
    @Test
    public void test3() {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .distinct()
                .sorted( (o1, o2) -> o1.getCity().compareTo(o2.getCity()))
                .forEach(System.out::println);
    }

    //4. 返回所有交易员的姓名字符串，按字母顺序排序
    @Test
    public void test4() {
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                //.sorted()
                .forEach(System.out::println);
        System.out.println("------------------------- ");
        String reduce = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", String::concat);
        System.out.println("reduce = " + reduce);
        System.out.println("-----------------------");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .flatMap(TestTranscation::filterCharacter)
                .sorted((o1, o2) -> o1.compareToIgnoreCase(o2))
                .forEach(System.out::print);

    }

    public static Stream<String> filterCharacter(String str) {
        List<String> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch.toString());
        }

        return list.stream();
    }

    //5. 有没有交易员是在米兰工作的？
    @Test
    public void test5() {
        boolean b = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("b = " + b);

    }


    //6. 打印生活在剑桥的交易员的所有交易额
    @Test
    public void test6() {
        Optional<Integer> sum = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println("sum = " + sum.get());
    }


    //7. 所有交易中，最高的交易额是多少
    @Test
    public void test7() {
        Optional<Integer> max = transactions.stream().map(Transaction::getValue).max(Integer::compare);
        Optional<Integer> min = transactions.stream().map(Transaction::getValue).min(Integer::compare);
        System.out.println("max = " + max.get());
        System.out.println("min = " + min.get());
    }

    //8. 找到交易额最小的交易
    @Test
    public void test8() {
        Optional<Transaction> min = transactions.stream().min((o1, o2) -> Integer.compare(o1.getValue(), o2.getValue()));
        System.out.println("min.get() = " + min.get());
    }
    
}
