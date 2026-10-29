package com.example.mybatis.util.book;

import com.example.mybatis.mapper.BookMapper;
import com.example.mybatis.pojo.Book;
import com.example.mybatis.util.UserUtil;
import org.apache.ibatis.session.SqlSession;

public class CreateDemo {
    public static int addBook(String title,String author,int stock) {
        //增加图书
        SqlSession session = UserUtil.openSession();
        try {
            BookMapper mapper = session.getMapper(BookMapper.class);
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setStock(stock);

            int rows = mapper.insertbook(book);

            System.out.println("新插入的图书id为"+book.getId());

            session.commit();
            return rows;

        } catch (Exception e) {
            //回滚为什么要在sout前面,rollback() 是“补救动作”，println() 是“通知动作”。
            // 先补救，再通知，逻辑上更顺：先把数据恢复到安全状态，再告诉用户出事了。

            session.rollback();
            System.out.println("发生错误了"+e.getMessage());
            return -1;

        }finally {
            session.close();
        }
    }
}
