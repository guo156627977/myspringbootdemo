package com.gzq.service;

import com.gzq.pojo.User;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-07-10 15:17.
 */

public interface UserService {
    List<User> getAllUser();

    User getUserById(String userId);

}
