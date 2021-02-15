package com.lifu.dao;

import com.lifu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description 基础DAO
 * @Author lifu
 * @Date 2021/1/27 15:44
 */
public class BaseDAO {
    private QueryRunner queryRunner = new QueryRunner();

    /**
    * @Description 增删改操作
    * @Author lifu
    * @Date 2021/1/27 15:51
    * @Param [conn, sql, args]
    * @Return int 返回影响的行数，-1表示执行失败
    */
    public int update(String sql,Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     *
     * @param type
     * @param sql
     * @param args
     * @param <T> 泛型
     * @return 返回一个javabean
     */
    public <T> T queryForOne(Class<T> type,String sql,Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
    /**
    * @Description 返回多个javabean
    * @Author lifu
    * @Date 2021/1/27 16:07
    * @Param [conn, type, sql, args]
    * @Return java.util.List<T>
    */
    public <T> List<T> queryForList(Class<T> type, String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
    /**
    * @Description 返回一行一列的值
    * @Author lifu
    * @Date 2021/1/27 16:13
    * @Param [conn, sql, args]
    * @Return java.lang.Object
    */
    public Object queryForValue(String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}