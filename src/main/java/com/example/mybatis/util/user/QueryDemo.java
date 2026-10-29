package com.example.mybatis.util.user;

import com.example.mybatis.mapper.QueryMapper;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.pojo.Query;
import com.example.mybatis.pojo.User;
import com.example.mybatis.util.UserUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class QueryDemo {

    public static int findUser(String studentnum) {
        SqlSession session = UserUtil.openSession();

        try {
            QueryMapper mapper = session.getMapper(QueryMapper.class);
            List<Query> list = mapper.selectBookRecordByStudentNum(studentnum);
            if (list.isEmpty()) {
                System.out.println("此人没有借书记录");
                return 0;
            } else {


                for (Query user : list) {
                    System.out.println(user);

                }
                return 1;
            }

        } catch (Exception e) {
            System.out.println("发生不明错误" + e.getMessage());
            return -1;
        } finally {
            session.close();
        }
    }

  public static int findBook(String title) {
        SqlSession session = UserUtil.openSession();
        try {
            QueryMapper mapper = session.getMapper(QueryMapper.class);
            List<Query> rows = mapper.selectBookBorrowByWho(title);
            if (rows.isEmpty()) {
                System.out.println("没有人借了这本书");
                return 0;
            }

                System.out.println("有" + rows.size() + "个人借了这本书");
                for (Query user : rows) {
                    System.out.println(user);
                }

            return 1;
        } catch (Exception e) {
            System.out.println("发生错误" + e.getMessage());
            return -1;
        } finally {
            session.close();
        }
    }

    public static int listUser(){
        SqlSession session = UserUtil.openSession();
        try {


            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> users = mapper.selectAll();
            for(User user:users){
                System.out.println(user);
            }
            System.out.println("成功查询所有用户");
            return 1;
        } catch (Exception e) {
            System.out.println("发生不明错误"+e.getMessage());
            return -1;
        }
        finally {
            session.close();
        }
    }
}

