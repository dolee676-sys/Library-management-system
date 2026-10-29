package com.example.mybatis.mapper;

import com.example.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {

    int insertUser(User user);   //新增用户

    User selectByNum(String studentNum);

    List<User> selectAll();

    int deleteById(Integer id);

    int updateNameById(@Param("id") Integer id,
                       @Param("name") String name);

}
