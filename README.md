# 图书管理系统

可供图书馆使用的图书管理系统，支持图书管理，用户管理，借书和还书功能

## 一,环境

| 项       | 版本                          |
|---------|-----------------------------|
| JDK     | 25                          |
| Maven   | 3.10.0                      |
| MySQL   | 8.0.45                      |
| MyBatis | 3.5.19                      |
| MySQL驱动 | mysql-connector-java 8.0.33 |

## 二，建库建表

在MySQL客户端中依次执行

```sql
CREATE DATABASE IF NOT EXISTS book_demo
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;
USE book_demo;

CREATE TABLE user (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(20) DEFAULT NULL,
    student_num varchar(50) DEFAULT NULL
) DEFAULT CHARSET = utf8mb4;

CREATE TABLE book (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title varchar(50) DEFAULT NULL,
    author varchar(50) DEFAULT NULL,
    stock int NOT NULL DEFAULT 0
) DEFAULT CHARSET = utf8mb4;

CREATE TABLE borrow_record (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id int NOT NULL,
    book_id int NOT NULL,
    borrow_time datetime DEFAULT CURRENT_TIMESTAMP,
    return_time datetime DEFAULT NULL,
    status varchar(20) NOT NULL DEFAULT '借出'
) DEFAULT CHARSET = utf8mb4;

```

执行完用`SHOW TABLES;`查看三张表.

## 三，修改连接配置

打开`src/main/resources/jdbc.properties`,改成自己机器的账号和密码：

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver

jdbc.url=jdbc:mysql://localhost:3306/book_demo?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8&allowPublicKeyRetrieval=true

jdbc.username=root

jdbc.password=你的密码
```

| 键              | 作用            | 什么时候需要改             |
|----------------|---------------|---------------------|
| jdbc.driver    | MySQL 8 的驱动类名 | 一般不动                |
| jdbc.url       | 连哪个库，时区，编码    | 库名不是`book_demo`的时候改 |
| jdbc.username  | 数据库账号         | 改成自己的               |
| jdbc.password  | 数据库密码         |改成自己的|

> 该文件已被 `.gitignore` 排除，不会提交到仓库。克隆本项目后需自行创建 `src/main/resources/jdbc.properties`，填入上面四项。

## 四，运行

1. 用 IDEA 打开项目根目录，等 Maven 依赖下载完成

2. 打开 `src/main/java/com/example/mybatis/Menu.java`

3. 右键 `main` 方法 → Run

出现下面的界面说明连接成功：

```text
===== 图书管理系统 =====
1. 用户管理
2. 图书管理
3. 借书
4. 还书
0. 退出
请选择：
```

## 五，功能


| 菜单      | 功能                            |
|---------|-------------------------------|
| 1. 用户管理 | 新增用户、按学号查借阅记录、用户列表、修改姓名       |
| 2. 图书管理 | 图书入库、按书名/作者/全部查询、删除图书、修改作者与库存 |
| 3. 借书   | 输入用户 id 与图书 id；库存不足时失败，不产生记录  |
| 4. 还书   | 输入用户 id 与图书 id；成功后库存加回，记录改为已还 |
| 2->2->4 |查书被谁借走了|

## 六，项目结构


```text
src/main/java/com/example/mybatis/
├── Menu.java                控制台菜单：读输入、分发到各功能
├── mapper/                  Mapper 接口：只声明方法，不写实现
├── pojo/                    实体类：装数据库一行数据
└── util/                    业务类：开 session、调 Mapper、控制事务
    ├── UserUtil.java        工具类：生成 SqlSessionFactory
    ├── book/                图书相关业务
    └── user/                用户相关业务

src/main/resources/
├── mybatis-config.xml       全局配置：数据源、驼峰映射、注册 Mapper
├── jdbc.properties          连接信息（不提交）
├── jdbc.properties.example  连接信息模板（提交）
└── com/example/mybatis/     四个 Mapper XML，写实际 SQL
```

| 层 | 职责 |
| --- | --- |
| 配置 | 决定连哪个库、开启驼峰映射、注册哪些 XML；账号密码独立存放 |
| Mapper 接口 | 只写方法签名，MyBatis 运行时用动态代理生成实现 |
| Mapper XML | 写 SQL；`namespace` = 接口全限定名，`id` = 方法名，二者必须一致 |
| 业务类 | 一个方法一个事务：开 session → 调 Mapper → commit / rollback → close |


## 七，设计
        
        并发借最后一本，由于借书会判断库存是否大于0，并且是先扣库存，再成功借书
        而还书是先执行记录，再对库存进行更改，所以借书的时候不会出现多人借最后一本的情况

















