package com.lemon.util;

import com.sun.javafx.collections.MapListenerHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2022/1/7 21:40
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class JDBCUtils {
    /**
     * 链接mysql数据库
     * @return Connect连接对象
     */
    public static Connection getConnection() {
        //定义数据库连接
        //Oracle：jdbc:oracle:thin:@localhost:1521:DBName
        //SqlServer：jdbc:microsoft:sqlserver://localhost:1433; DatabaseName=DBName
        //MySql：jdbc:mysql://localhost:3306/DBName(数据库名)
        String url="jdbc:mysql://mall.lemonban.com/yami_shops?useUnicode=true&characterEncoding=utf-8";
        String user="lemon";
        String password="lemon123";
        //定义数据库连接对象
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user,password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        //0、创建数据库连接
        Connection connection = getConnection();
        //1、生成QueryRunner对象
        QueryRunner queryRunner = new QueryRunner();
        //2、调用query方法来实现查询操作
        //2-1、多条结果集
        /*List<Map<String,Object>> datas = queryRunner.query(connection,"SELECT * FROM tz_sms_log;", new MapListHandler());
        System.out.println(datas.get(0).get("mobile_code"));*/
        //2-2、一条结果集
        /*Map<String,Object> datas = queryRunner.query(connection,"SELECT * FROM tz_sms_log WHERE id = 8;",new MapHandler());
        System.out.println(datas);*/
        //2-3、单个数据
        /*String code = queryRunner.query(connection,
                "SELECT mobile_code from tz_sms_log where id = (SELECT MAX(id) FROM tz_sms_log);",new ScalarHandler<>());
        System.out.println(code);*/
        //System.out.println(querySingleData("SELECT mobile_code from tz_sms_log where id = (SELECT MAX(id) FROM tz_sms_log);"));
        //System.out.println(queryOneData("SELECT mobile_code from tz_sms_log where id = (SELECT MAX(id) FROM tz_sms_log);"));
    }

    /**
     * 查询单个数据
     * @param sql 执行sql语句
     * @return 结果
     */
    public static Object querySingleData(String sql){
        //参数化替换
        sql=Environment.replaceParams(sql);
        Connection connection = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        Object data = null;
        try {
            data = queryRunner.query(connection, sql,new ScalarHandler<>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }

    /**
     * 查询结果集中的一条数据
     * @param sql 要执行的sql语句
     * @return 结果
     */
    public static Map<String,Object> queryOneData(String sql){
        Connection connection = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        Map<String,Object> data = null;
        try {
            data = queryRunner.query(connection, sql,new MapHandler());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }
}
