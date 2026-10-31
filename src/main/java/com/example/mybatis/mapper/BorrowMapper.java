package com.example.mybatis.mapper;

import com.example.mybatis.pojo.BorrowRecord;
import com.example.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface BorrowMapper {
    int borrowBook(@Param("userId")Integer userId,
                   @Param("bookId")Integer bookId);
    //选择用户和图书
    int returnBook(@Param("userId")Integer userId,
                   @Param("recordId")Integer recordId
    );
    int updateBookStockIncrease(Integer bookId);

    int updateBookStockDecrease(Integer bookId);

    BorrowRecord selectBookByRecordId(Integer id);
    int countBorrowingByBookId(@Param("bookId") Integer bookId);
}
