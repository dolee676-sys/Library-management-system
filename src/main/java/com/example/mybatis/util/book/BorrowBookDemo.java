package com.example.mybatis.util.book;

import com.example.mybatis.mapper.BorrowMapper;
import com.example.mybatis.util.UserUtil;
import org.apache.ibatis.session.SqlSession;

public class BorrowBookDemo {
    public static int borrowBookById(int userId,int bookId) {
        SqlSession session = UserUtil.openSession();

        try{
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            //先查库存，再借书
            int borrowrows1 = mapper.updateBookStockDecrease(bookId);
            if(borrowrows1 == 0){
                System.out.println("库存不足！看看别的书吧。");
                return  0 ;
            }
                int borrowrows = mapper.borrowBook(userId, bookId);
                System.out.println("借书成功了！恭喜恭喜！增加了"+borrowrows+"条借书记录");
            session.commit();
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
