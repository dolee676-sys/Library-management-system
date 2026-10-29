package com.example.mybatis.pojo;

public class User {
    private Integer id;
    private String name;
    private String studentNum;

    public User() {}

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

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', studentNum='" + studentNum + "'}";
    }
}