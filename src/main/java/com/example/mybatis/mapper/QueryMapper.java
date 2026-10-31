package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Book;
import com.example.mybatis.pojo.Query;
import com.example.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueryMapper {
    List<Query> selectBookRecordByStudentNum(String studentNum);


    List<Query> selectBookBorrowByWho(@Param("title") String title);

    User selectById(Integer id);
    Book findBookById(Integer id);

}
