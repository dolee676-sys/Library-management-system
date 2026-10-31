package com.example.mybatis.util.book;

import com.example.mybatis.mapper.BookMapper;
import com.example.mybatis.util.UserUtil;
import org.apache.ibatis.session.SqlSession;

public class UpdateDemo {
    public static int updateAuthor(int id, String author) {
        SqlSession session = UserUtil.openSession();
        try {
            BookMapper mapper = session.getMapper(BookMapper.class);
            int rows1 = mapper.updateAuthor(id, author);
            if (rows1 == 0) {
                System.out.println("未插入成功");
                return 0;
            }
            System.out.println("影响的行数是" + rows1);
            session.commit();
            return 1;

        } catch (Exception e) {

            session.rollback();
            System.out.println("修改错误" + e.getMessage());
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    public static int updateStock(Integer bookId, int stock) {
        SqlSession session = UserUtil.openSession();
        try {
            BookMapper mapper = session.getMapper(BookMapper.class);

            int rows = mapper.updateStock(bookId, stock);
            if (rows == 0) {
                System.out.println("修改失败");
                return 0;
            }

            session.commit();
            System.out.println("修改成功，影响行数 = " + rows);
            return 1;

        } catch (Exception e) {
            session.rollback();
            System.out.println("修改错误：" + e.getMessage());
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }
}
