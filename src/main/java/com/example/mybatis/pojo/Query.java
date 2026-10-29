package com.example.mybatis.pojo;

import java.time.LocalDateTime;

public class Query {
    private Integer id;
    private String name;
    private String title;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
    private String status;

    public Query() {
    }

    public Query(Integer id, String name, String title, LocalDateTime borrowTime, LocalDateTime returnTime, String status) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "Query{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", borrowTime=" + borrowTime +
                ", returnTime=" + returnTime +
                ", status='" + status + '\'' +
                '}';
    }
}
