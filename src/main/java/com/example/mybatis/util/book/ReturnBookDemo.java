package com.example.mybatis.util.book;

import com.example.mybatis.mapper.BorrowMapper;
import com.example.mybatis.util.UserUtil;
import org.apache.ibatis.session.SqlSession;

public class ReturnBookDemo {
    public static int returnBookById(int userId,int bookId) {
        SqlSession session = UserUtil.openSession();

        try{
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            int returnrows1 = mapper.returnBook(userId,bookId);

            if(returnrows1 == 0){

                System.out.println("还书失败了！");
                return 0;
            }

            int returnrows= mapper.updateBookStockIncrease(bookId);
            if (returnrows == 0){
                System.out.println("库存增加失败：bookId=" + bookId);
            }
            session.commit();
            System.out.println("还书成功");
            return 1;

        } catch (Exception e) {
            session.rollback();
            System.out.println("发生错误了"+e.getMessage());
                e.printStackTrace();
                return -1;
        }finally {
            session.close();
        }
    }
}
