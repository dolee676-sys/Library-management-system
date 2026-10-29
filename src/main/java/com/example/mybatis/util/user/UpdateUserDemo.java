package com.example.mybatis.util.user;

import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.util.UserUtil;
import org.apache.ibatis.session.SqlSession;

public class UpdateUserDemo {
    public static int updateUserName(int userId,String name) {
        SqlSession session = UserUtil.openSession();
        try{
            UserMapper mapper = session.getMapper(UserMapper.class);
            int rows = mapper.updateNameById(userId,name);
            if(rows == 0){
                System.out.println("改名失败");
                return 0;
            }

            session.commit();
            System.out.println("改名成功");
            return 1;


        } catch (Exception e) {
            session.rollback();
            System.out.println("程序发生错了！"+e.getMessage());
            e.printStackTrace();
            return -1;
        }finally {
            session.close();
        }

    }
}
