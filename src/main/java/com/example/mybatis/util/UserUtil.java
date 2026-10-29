package com.example.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class UserUtil {
        //为什么要共用：如果 Menu 里一个 new Scanner(System.in)
        // CreateDemo 里又一个，两个 Scanner 各有各的缓冲区
        // 交替读取时会互相“吃掉”对方的输入——现象是“输入被跳过了”。
        // 共用一个就没这问题。
        public static final Scanner SC = new Scanner(System.in);

        private static final SqlSessionFactory FACTORY;

        static {
                try {
                        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
                        FACTORY = new SqlSessionFactoryBuilder().build(in);
                        in.close();
                } catch (IOException e) {
                        throw new RuntimeException(
                                "mybatis初始化失败,请检查mybatis-config.xml", e);
                }
        }

        //工具类里面都是静态方法，实例化对象没有意义
        private UserUtil() {
        }

        public static SqlSessionFactory getFACTORY() {
                return FACTORY;
        }

        public static SqlSession openSession() {
                return FACTORY.openSession();
        }

        public static SqlSession openSessionAutoCommit() {
                return FACTORY.openSession(true);
        }



}

