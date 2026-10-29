package com.example.mybatis.util.book;

import com.example.mybatis.mapper.BookMapper;
import com.example.mybatis.mapper.QueryMapper;
import com.example.mybatis.pojo.Book;
import com.example.mybatis.util.UserUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class QueryDemo {
    public static int findBookByAuthor(String author) {
        SqlSession session = UserUtil.openSession();
        try {
            BookMapper mapper = session.getMapper(BookMapper.class);
            List<Book> authorList = mapper.selectByAuthor(author);
            if (authorList.isEmpty()) {
                System.out.println("此作者不存在");
                return 0;
            }

            //查询不用提交事务，可以不用写session.commit();

            for(Book book : authorList) {
                System.out.println(book);
            }
            System.out.println("查询到" + authorList.size() + "条记录");

            return authorList.size();

        } catch (Exception ex) {
            System.out.println("查询错误" + ex.getMessage());
            return -1;

        } finally {
            session.close();
        }

    }
    public static int findBookByTitle(String title) {
        SqlSession session = UserUtil.openSession();
        try {
            BookMapper mapper = session.getMapper(BookMapper.class);
            List<Book> titleList = mapper.selectByBookname(title);
            //此处不应该写==null,因为List是返回空List的
            if (titleList.isEmpty()) {
                System.out.println("此书不存在");
                return 0;
            }

                for (Book book : titleList) {
                    System.out.println(book);
                }

            System.out.println("按书名查到 " + titleList.size() + " 条");
            return 1;

        } catch (Exception e) {
            System.out.println("查询失败"+e.getMessage());
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }

    }
    public static int findAllBook(){
        SqlSession session = UserUtil.openSession();
        try{
            BookMapper mapper = session.getMapper(BookMapper.class);
            List<Book> books = mapper.selectAll();
            for(Book book: books){
                System.out.println(book);
            }

            return 1;

        } catch (Exception e) {
            System.out.println("查询失败"+e.getMessage());
            e.printStackTrace();
            return -1;

        }finally {
            session.close();
        }

    }



    }

