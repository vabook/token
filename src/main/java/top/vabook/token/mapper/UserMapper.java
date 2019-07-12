package top.vabook.token.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import top.vabook.token.entity.User;

/**
 * @Author: vabook
 * @Date: 2019/7/11 15:59
 */

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results(@Result(column = "create_time",property = "createTime"))
    User findByName(String username);
}
