package top.vabook.token.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.vabook.token.entity.User;
import top.vabook.token.mapper.UserMapper;

/**
 * @Author: vabook
 * @Date: 2019/7/11 16:07
 */

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping(value = "/user/{username}")
    public Object getUserByName(@PathVariable  String username){

        System.out.println("========================进入get=============================");
        User user = userMapper.findByName(username);
        return user;
    }

}
