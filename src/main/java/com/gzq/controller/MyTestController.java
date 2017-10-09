package com.gzq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-10 15:12.
 */
@Controller
public class MyTestController {

    @RequestMapping("/mytest")
    public String myTest() {
        return "/mytest";
    }
}
