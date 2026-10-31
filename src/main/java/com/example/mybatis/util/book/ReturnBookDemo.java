package com.example.mybatis.util.book;

import com.example.mybatis.mapper.BorrowMapper;
import com.example.mybatis.mapper.QueryMapper;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.pojo.BorrowRecord;
import com.example.mybatis.pojo.User;
import com.example.mybatis.util.UserUtil;
import org.apache.ibatis.session.SqlSession;

public class ReturnBookDemo {
    public static int returnBookById(int userId,int recordId) {
        SqlSession session = UserUtil.openSession();

        try{

            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            QueryMapper mapper1 = session.getMapper(QueryMapper.class);

            User user = mapper1.selectById(userId);
            if (user ==  null) {
                System.out.println("此人不存在");
                return 0;
            }
            int returnrows1 = mapper.returnBook(userId,recordId);

            if(returnrows1 != 1){

                System.out.println("还书失败了！");
                return 0;
            }

            BorrowRecord borrowRecord = mapper.selectBookByRecordId(recordId);
            Integer bookId = borrowRecord.getBookId();

            int returnrows= mapper.updateBookStockIncrease(bookId);
            if (returnrows != 1){
                session.rollback();
                System.out.println("库存减少失败，你搞错了!");
                return 0;
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
