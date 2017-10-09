package com.gzq.learn.jdk8.newDateAPI.exer;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-27 15:59.
 */
public class TestNewDate {


    @Test
    public void test6() {
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    //5. DateTimeFormatter : 解析和格式化日期或时间
    @Test
    public void test5() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");
        LocalDateTime now = LocalDateTime.now();
        String strDate = now.format(dateTimeFormatter);
        System.out.println("strDate = " + strDate);
        LocalDateTime parse = now.parse(strDate, dateTimeFormatter);
        System.out.println("parse = " + parse);

    }

    //4. TemporalAdjuster : 时间校正器
    @Test
    public void test4() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        LocalDateTime ldt = now.withDayOfMonth(10);
        System.out.println("ldt = " + ldt);
        //下一个周日
        LocalDateTime sunDay = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println("sunDay = " + sunDay);

        LocalDateTime nextDay = now.plusDays(1);
        //下一个工作日
        LocalDateTime nextWorkDay = nextDay.with((l) -> {
            LocalDateTime dateTime = (LocalDateTime) l;
            DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)){
                return dateTime.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return dateTime.plusDays(2);
            } else {
                return dateTime.plusDays(1);
            }
        });
        System.out.println("nextWorkDay = " + nextWorkDay);

    }

    //3.
    //Duration : 用于计算两个“时间”间隔
    //Period : 用于计算两个“日期”间隔
    @Test
    public void test3() {
        //Instant now1 = Instant.now();
        //try {
        //    Thread.sleep(1000);
        //} catch (InterruptedException e) {
        //}
        //Instant now2 = Instant.now();
        //Duration between = Duration.between(now1, now2);
        //System.out.println("between.toMillis() = " + between.toMillis());

        LocalDateTime lt1 = LocalDateTime.now();
        LocalDateTime lt2 = LocalDateTime.of(2017,1,1,0,0,0);
        //Period pe = Period.between(lt2, lt1);
        //int days = pe.getDays();
        //System.out.println("days = " + days);
        //int months = pe.getMonths();
        //System.out.println("months = " + months);
        //int years = pe.getYears();
        //System.out.println("years = " + years);
        Duration between = Duration.between(lt2, lt1);
        long l = between.toDays();
        System.out.println("l = " + l);

    }


    //2. Instant : 时间戳。 （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
    @Test
    public void test2() {
        //获取标准时间
        Instant UTTime = Instant.now();
        System.out.println("UTTime = " + UTTime);
        //时区矫正
        OffsetDateTime ChinaTime = UTTime.atOffset(ZoneOffset.ofHours(8));
        System.out.println("ChinaTime = " + ChinaTime);

        int nano = UTTime.getNano();
        System.out.println("nano = " + nano);
        //相对于Unix元年 偏移的秒数
        Instant instant = Instant.ofEpochSecond(30);
        System.out.println("instant = " + instant);
        //获取当前时间距离时间戳的秒数
        long epochSecond = UTTime.getEpochSecond();
        System.out.println("epochSecond = " + epochSecond);
        //获取当前时间距离Unix元年的毫米数
        long l = UTTime.toEpochMilli();
        System.out.println("l = " + l);

    }

    //1. LocalDate、LocalTime、LocalDateTime
    @Test
    public void test1() {
        //获取当前的系统时间
        LocalDateTime start = LocalDateTime.now();
        System.out.println("start = " + start);
        int sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        System.out.println("sum = " + sum);
        LocalDateTime end = LocalDateTime.now();

        System.out.println("所花时间" + Duration.between(start, end).toMillis() + "ms");

        int hour = start.getHour();
        System.out.println("hour = " + hour);
        int dayOfMonth = start.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth);
        int year = start.getYear();
        System.out.println("year = " + year);
        int dayOfYear = start.getDayOfYear();
        System.out.println("dayOfYear = " + dayOfYear);
        Month month = start.getMonth();
        System.out.println("month = " + month);
        System.out.println("month.getValue() = " + month.getValue());
        int minute = start.getMinute();
        System.out.println("minute = " + minute);
        int second = start.getSecond();
        System.out.println("second = " + second);
        int nano = start.getNano();
        System.out.println("nano = " + nano);
        //设置某一个日期
        LocalDateTime ldt = LocalDateTime.of(2015, 12, 12, 11, 11, 11);
        System.out.println("ldf = " + ldt);
        //设置转换格式
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //格式转换
        String format = ldt.format(dtf);
        System.out.println("format = " + format);

    }
}
