package com.example.mybatis;

import com.example.mybatis.util.book.BorrowBookDemo;

import java.util.Scanner;

import static com.example.mybatis.util.book.BorrowBookDemo.borrowBookById;
import static com.example.mybatis.util.book.CreateDemo.addBook;
import static com.example.mybatis.util.book.DeleteDemo.deleteBook;
import static com.example.mybatis.util.book.QueryDemo.*;
import static com.example.mybatis.util.book.ReturnBookDemo.returnBookById;
import static com.example.mybatis.util.book.UpdateDemo.updateAuthor;
import static com.example.mybatis.util.book.UpdateDemo.updateStock;
import static com.example.mybatis.util.user.CreateDemo.addUser;
import static com.example.mybatis.util.user.QueryDemo.*;
import static com.example.mybatis.util.user.UpdateUserDemo.updateUserName;

public class Menu {

    private static final Scanner SC = new Scanner(System.in);
   public static void main(String[] args) {
        while(true){
            System.out.println("===== 图书管理系统 =====");
            System.out.println("1. 用户管理");
            System.out.println("2. 图书管理");
            System.out.println("3. 借书");
            System.out.println("4. 还书");
            System.out.println("0. 退出");
            System.out.print("请选择：");

            switch (SC.nextLine().trim()){
                case "1": userMenu();break;
                case "2": bookMenu(); break;
                case "3": borrowBookMenu();  break;
                case "4": returnBookMenu(); break;
                case "0":
                    System.out.println("退出程序");
                    return;
                default:
                    System.out.println("无效选项");

            }
        }
    }
    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(SC.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("请输入数字");
            }
        }
    }

    private static void userMenu(){
       while(true){
           System.out.println("\n-----用户管理-----");
           System.out.println("1. 新增用户  2. 按学号查  3. 用户列表  4.修改用户姓名  0. 返回");
           System.out.println("请选择");

           switch (SC.nextLine().trim()){
               case "1":
                   System.out.print("姓名：");
                   String name = SC.nextLine().trim();
                   System.out.print("学号：");
                   String studentNum = SC.nextLine().trim();

                   int id = addUser(name, studentNum);
                   if (id > 0) {
                       System.out.println("保存成功，id = " + id);
                   } else {
                       System.out.println(" 保存失败");
                   }
                   break;

               case "2":

                   System.out.print("学号：");
                   studentNum = SC.nextLine().trim();
                   int user = findUser(studentNum);
                   if(user>0) {
                       System.out.println("查询成功");
                   }else {
                       System.out.println("此人不存在");
                   }
                   break;
               case "3": listUser();  break;

               case "4":
                   System.out.println("请输入userId");
                   int userId = readInt();
                   System.out.println("请输入新名字");
                    name = SC.nextLine().trim();
                   int i = updateUserName(userId, name);
                   if(i>0){
                       System.out.println("添加成功");
                   }else {
                       System.out.println("添加失败了！");
                   }
                   break;


               case "0":
                   System.out.println("返回上级菜单");return;
               default:
                   System.out.println("没有这个选项");
           }
       }
    }

    private static void bookMenu() {
        while (true) {
            System.out.println("-------------图书管理-------------");
            System.out.println("请选择");
            System.out.println("1.为书架增加新图书\n");
            System.out.println("2.查询图书\n");
            System.out.println("3.删除图书\n");
            System.out.println("4.修改图书信息\n");
            switch (SC.nextLine().trim()) {
                case "1":
                    System.out.println("请输入书名");
                    String title = SC.nextLine().trim();
                    System.out.println("输入作者\n");
                    String author = SC.nextLine().trim();
                    System.out.println("输入库存\n");
                    int stock = readInt();

                    int rows = addBook(title, author, stock);
                    if (rows > 0) {
                        System.out.println("添加成功");
                    } else {
                        System.out.println("保存失败");
                    }
                    break;

                case "2":
                    selectType();
                    break;

                case "3":
                    System.out.println("当前所有图书：");
                    findAllBook();
                    System.out.println("请输入书号");
                    int bookId = readInt();
                    deleteBook(bookId);
                    break;

                case "4":
                    System.out.println("1.修改作者");
                    System.out.println("2.修改库存\n");
                    System.out.println("3.返回上级菜单\n");
                    switch (SC.nextLine().trim()) {
                        case "1":
                            System.out.println("请输入书号");
                            //case共享作用域，不用再次声明
                            bookId = readInt();
                            System.out.println("请输入新作者");
                            String newAuthor = SC.nextLine().trim();
                            updateAuthor(bookId, newAuthor);
                            break;

                        case "2":
                            System.out.println("请输入书号");
                            bookId = readInt();
                            System.out.println("请输入新库存");
                            int newStock = readInt();
                            updateStock(bookId, newStock);
                            break;

                        case"0":
                            System.out.println("返回上级菜单");
                            break;

                        default:
                            System.out.println("没有这个选项");
                    }




            }
        }
    }
    private static void selectType() {
        while (true) {
            System.out.println("-------图书查询-------");
            System.out.println("1.通过书名查");
            System.out.println("2.通过作者名查\n");
            System.out.println("3.查询所有的图书\n");
            System.out.println("4. 查这本书现在被谁借走\n");
            System.out.println("0.返回上一级\n");
            switch (SC.nextLine().trim()) {
                case "1":
                    System.out.println("请输入书名");
                    String title = SC.nextLine().trim();
                    findBookByTitle(title);
                    break;

                case "2":
                    System.out.println("请输入作者名");
                    String author = SC.nextLine().trim();
                    findBookByAuthor(author);
                    break;

                case "3":
                    findAllBook();
                    break;

                case "4":
                    System.out.print("书名：");
                    findBook(SC.nextLine().trim());
                    break;

                case "0":
                    return;

            }
        }
    }
    private static void borrowBookMenu(){
        System.out.println("请输入用户id");
        int userId = readInt();
        System.out.println("请输入书号");
        int bookId = readInt();
        borrowBookById(userId, bookId);

    }
private static void returnBookMenu(){
    System.out.println("请输入用户Id");
       int userId = readInt();
    System.out.println("请输入要还的借阅Id号");
    int bookId = readInt();
    int i = returnBookById(userId, bookId);
    if(i>0){
        System.out.println("还书成功");
    }else {
        System.out.println("还书失败了！");
    }
}






}
