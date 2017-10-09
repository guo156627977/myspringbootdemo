package com.gzq.service.impl;

import com.gzq.mapper.UserMapper;
import com.gzq.pojo.User;
import com.gzq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-10 15:17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public User getUserById(String userId) {
        User user = userMapper.getUserById(userId);
        return user;
    }
}
