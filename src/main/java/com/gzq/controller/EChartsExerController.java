package com.gzq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-11 15:45.
 */
@Controller
public class EChartsExerController {

    @RequestMapping("/exer1")
    public String exer1() {
        return "/echarts/exer1";
    }


    @RequestMapping("/rose")
    public String rose() {
        return "/echarts/rose";
    }


}
