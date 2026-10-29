package com.example.mybatis.pojo;

import java.time.LocalDateTime;

public class BorrowRecord {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
    private String status;

    public BorrowRecord() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(LocalDateTime borrowTime) {
        this.borrowTime = borrowTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowRecord{id=" + id + ", userId=" + userId + ", bookId=" + bookId +
                ", borrowTime=" + borrowTime + ", returnTime=" + returnTime + ", status='" + status + "'}";
    }
}