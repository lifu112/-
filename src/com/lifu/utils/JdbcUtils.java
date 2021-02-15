package com.lifu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/27 14:17
 */
public class JdbcUtils {
    //数据库连接池
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        //返回null，证明获取失败
        Connection conn = threadLocal.get();
        if(conn == null){
            //从连接池获取
            try {
                conn = dataSource.getConnection();
                //保存到线程中
                threadLocal.set(conn);
                conn.setAutoCommit(false);//设置手动提交
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并关闭数据库连接
     */
    public static void commitAndClose(){
        Connection conn = threadLocal.get();
        if(conn != null){
            try {
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        threadLocal.remove();
    }

    /**
     * 回滚并关闭连接
     */
    public static void rollbackAndClose(){
        Connection conn = threadLocal.get();
        if(conn != null){
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        threadLocal.remove();
    }

}
