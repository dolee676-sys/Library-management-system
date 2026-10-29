package com.example.mybatis.util.book;

import com.example.mybatis.mapper.BookMapper;
import com.example.mybatis.mapper.BorrowMapper;
import com.example.mybatis.pojo.Book;
import com.example.mybatis.util.UserUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DeleteDemo {
    public static int deleteBook(int BookId) {
        SqlSession session = UserUtil.openSession();
        try {
            BookMapper Bookmapper = session.getMapper(BookMapper.class);
            BorrowMapper Borrowmapper = session.getMapper(BorrowMapper.class);
            int borrowing = Borrowmapper.countBorrowingByBookId(BookId);
            if(borrowing>0){
                System.out.println("不准删除这本书！还有人在借阅中");
                return 0;
            }
            int rows = Bookmapper.deleteBook(BookId);

            if (rows == 0) {
                System.out.println("书不存在，没删到");
                return 0;
            }

            session.commit();
            System.out.println("删除了 " + rows + " 行");
            return 1;
        } catch (Exception e) {
            session.rollback();
            System.out.println("删除发生错误"+e.getMessage());
            e.printStackTrace();
            return -1;
        }
        finally {
            session.close();
        }
    }
}
