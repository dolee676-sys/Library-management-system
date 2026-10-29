package com.example.mybatis.util.user;

import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.pojo.User;
import com.example.mybatis.util.UserUtil;
import org.apache.ibatis.session.SqlSession;

public class DeleteDemo {
    public static int deleteUser(int userId) {
        SqlSession session = UserUtil.openSession();
        try{
            UserMapper mapper = session.getMapper(UserMapper.class);

            int i = mapper.deleteById(userId);
            if(i == 0){
                System.out.println("删除无效,用户不存在");

                return 0;
            }

            session.commit();
            System.out.println("删除成功");
            System.out.println("ID为"+userId+"的同学已经被删除");



            return i;
        } catch (Exception e) {
            System.out.println("删除错误"+e.getMessage());
            session.rollback();
            return -1;
        }finally {
            session.close();
        }
    }
}
