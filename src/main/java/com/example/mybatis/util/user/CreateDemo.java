package com.example.mybatis.util.user;

import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.pojo.User;
import com.example.mybatis.util.UserUtil;
import org.apache.ibatis.session.SqlSession;

public class CreateDemo {
    public static int addUser(String name, String student_num) {

        //增加用户

        SqlSession session = UserUtil.openSession();

        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = new User();
            user.setName(name);
            user.setStudentNum(student_num);
            mapper.insertUser(user);
            session.commit();
            System.out.println("新增用户成功，id = " + user.getId());

        return user.getId();
        } catch (Exception e) {
            session.rollback();
            System.out.println("新增用户失败，发生什么事了？" + e.getMessage());
            return -1;
        } finally {
            session.close();

        }
    }

}
