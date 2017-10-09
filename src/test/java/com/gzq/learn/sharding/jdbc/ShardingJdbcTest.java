package com.gzq.learn.sharding.jdbc;

import com.gzq.MyspringbootdemoApplication;
import com.gzq.mapper.UserMapper;
import com.gzq.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-10-09 9:48.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,classes = MyspringbootdemoApplication.class)// 指定spring-boot的启动类
public class ShardingJdbcTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User zhangsan = new User("100", "zhangsan", "123456");
        //userMapper.insert(zhangsan);
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println("user = " + user.toString());
        }

        User user = userMapper.getUserById("001");
        System.out.println("user = " + user.toString());
    }
}
