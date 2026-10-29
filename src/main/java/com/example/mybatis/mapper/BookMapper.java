package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    List<Book> selectByBookname(String title);

    List<Book> selectByAuthor(String author);

    List<Book> selectAll();

    int insertbook (Book book);   //新增图书

    int updateAuthor(@Param("id") Integer id,
            @Param("author") String author
    );

    int updateStock(
                    @Param("title") String title,
    @Param("stock") int stock);

    int deleteBook (Integer id);

}
