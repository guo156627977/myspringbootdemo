package com.gzq.learn.nio;

import java.math.BigDecimal;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-09-07 15:41.
 */
public class Emp {
    private int empNo;
    private String empName;
    private String job;
    private int managerNo;
    private String hireTime;
    private BigDecimal salay;
    private BigDecimal bonus;
    private int deptNo;

    public Emp() {
    }

    public Emp(int empNo, String empName, String job, int managerNo, String hireTime, BigDecimal salay, BigDecimal bonus, int
            deptNo) {
        this.empNo = empNo;
        this.empName = empName;
        this.job = job;
        this.managerNo = managerNo;
        this.hireTime = hireTime;
        this.salay = salay;
        this.bonus = bonus;
        this.deptNo = deptNo;
    }

    public int getManagerNo() {
        return managerNo;
    }

    public void setManagerNo(int managerNo) {
        this.managerNo = managerNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }



    public String getHireTime() {
        return hireTime;
    }

    public void setHireTime(String hireTime) {
        this.hireTime = hireTime;
    }

    public BigDecimal getSalay() {
        return salay;
    }

    public void setSalay(BigDecimal salay) {
        this.salay = salay;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }
}
