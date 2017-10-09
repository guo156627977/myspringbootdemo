package com.gzq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-10 14:56.
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String hello() {
        return"Hello World";
    }


}
