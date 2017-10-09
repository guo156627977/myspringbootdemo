package com.gzq.controller;

import com.gzq.pojo.User;
import com.gzq.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-10 16:10.
 */

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/getAllUser")
    public List<User> getAllUser() {
        List<User> allUser = userService.getAllUser();
        for (User user : allUser) {
            System.out.println("user.getUserName() = " + user.getUserName());
        }
        return allUser;
    }

    @RequestMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return user;
    }

    @RequestMapping("/luser")
    public User getUser() {
        User user = userService.getUserById("001");
        return user;
    }

    @RequestMapping("/user2")
    public User getUser1() {
        User user = userService.getUserById("001");
        return user;
    }

}
