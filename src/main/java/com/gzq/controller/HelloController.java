package com.gzq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-11 11:20.
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String helloWorld() {
        return "/hello";
    }
}
