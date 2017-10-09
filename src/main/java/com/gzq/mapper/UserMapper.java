package com.gzq.mapper;

import com.gzq.pojo.User;

import java.util.List;

public interface UserMapper {
    int insert(User record);

    List<User> selectAll();

    User getUserById(String userId);
}