package com.example.mybatis.util.book;

import com.example.mybatis.mapper.BookMapper;
import com.example.mybatis.mapper.BorrowMapper;
import com.example.mybatis.mapper.QueryMapper;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.pojo.Book;
import com.example.mybatis.pojo.User;
import com.example.mybatis.util.UserUtil;
import com.example.mybatis.util.user.QueryDemo;
import org.apache.ibatis.session.SqlSession;

public class BorrowBookDemo {
    public static int borrowBookById(int userId,int bookId) {
        SqlSession session = UserUtil.openSession();

        try{
            //先查库存，再借书
            //查人和书是否存在
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            QueryMapper mapper1 = session.getMapper(QueryMapper.class);
            User user = mapper1.selectById(userId);
            Book book = mapper1.findBookById(bookId);


            if(user == null){
                System.out.println("用户不存在");
                return 0;
            }

            if(book == null){
                System.out.println("书不存在");
                return 0;
            }
            int borrowrows1 = mapper.updateBookStockDecrease(bookId);
            if(borrowrows1 == 0){
                System.out.println("库存不足！看看别的书吧。");
                return  0 ;
            }
                int borrowrows = mapper.borrowBook(userId, bookId);
            if(borrowrows == 0){
                session.rollback();
                return 0;

            }
                session.commit();
                System.out.println("借书成功了！恭喜恭喜！增加了"+borrowrows+"条借书记录");

return 1;


        } catch (Exception e) {
            session.rollback();
            System.out.println("发生错误了！咋了？"+e.getMessage());
                e.printStackTrace();
                return -1;
        }finally {
            session.close();
        }
    }
}
