package com.gzq.learn.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-09-07 11:24.
 */
public class EmpAndDeptDataCreateUtil {

    private String directoryPath = "C:\\Users\\think\\Desktop";
    private String deptFileName = "dept.txt";
    private String empFileName = "emp.txt";

    private static List<String> deptAddress = Arrays.asList("NEWTORK", "DALLAS", "CHICAGO", "BOSTON");
    private static List<String> deptName = Arrays.asList("ACCOUNTING", "RESEARCH", "SALES", "OPERATIONS");
    private static List<String> job = Arrays.asList("CLERK", "SALESMAN", "MANAGER", "ANALYST");


    @Test
    public void createDeptData() {
        Instant start = Instant.now();
        int count = 10;
        FileOutputStream fos = null;
        FileChannel outChannel = null;
        String deptString = "";

        for (int i = 1; i < count; i++) {
            Dept dept = new Dept(i * 10, deptName.get((int) (Math.random() * 4)), deptAddress.get((int) (Math.random() * 4)));
            deptString += dept.getDeptNo() + "\t" + dept.getDeptName() + "\t" + dept.getDeptLoc() + "\n";
        }
        try {
            fos = new FileOutputStream(new File(directoryPath + File.separator + deptFileName));
            outChannel = fos.getChannel();
            ByteBuffer src = Charset.forName("utf8").encode(deptString);
            int length = 0;
            while ((length = outChannel.write(src)) != 0) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        Instant end = Instant.now();
        System.out.println("所花时间" + Duration.between(start, end).toMillis() + "ms");

    }

    @Test
    public void test() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter mmmMyydd = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = now.format(mmmMyydd);
        System.out.println("format = " + format);
        System.out.println("now = " + now);
        LocalDateTime localDateTime = now.plusDays(-1);
        System.out.println("localDateTime = " + localDateTime);
    }


    @Test
    public void createEmpData() {
        Instant start = Instant.now();
        int count = 10;
        //int count = 10000000;
        FileOutputStream fos = null;
        FileChannel outChannel = null;
        String empString = "";
        //这个用来调整id位数
        int idprefix = 100000000;
        LocalDate now = LocalDate.now();
        DateTimeFormatter mmmMyydd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {

            fos = new FileOutputStream(new File(directoryPath + File.separator + empFileName));
            outChannel = fos.getChannel();

            for (int i = 1; i < count; i++) {
                Emp emp = new Emp(idprefix + i, RandomValue.getChineseName(), job.get((int) (Math.random() * 4)), idprefix + 1 + (int) (Math
                        .random() * 100), now.plusDays(-(int) (Math.random() * 1000)).format(mmmMyydd), new BigDecimal((int) (Math.random
                        () * 10000)+3000), new
                        BigDecimal((int) (Math.random() * 10)*100), ((int) (Math.random() * 9 + 1) * 10));

                empString = emp.getEmpNo() + "\t" + emp.getEmpName() + "\t" + emp.getJob() + "\t" + emp.getManagerNo() + "\t" + emp
                        .getHireTime() + "\t" + emp.getSalay() + "\t" + emp.getBonus() + "\t" + emp.getDeptNo() + "\n";
                ByteBuffer src = Charset.forName("utf8").encode(empString);
                int length = 0;
                while ((length = outChannel.write(src)) != 0) {

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        Instant end = Instant.now();
        System.out.println("所花时间" + Duration.between(start, end).toMillis() + "ms");
    }
}
