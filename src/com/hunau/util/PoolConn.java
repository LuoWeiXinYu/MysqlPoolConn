package com.hunau.util;/* *
 * @Description:
 * @param $params$
 * @Return: $returns$
 * @开发人员：余新伟
 * @开发单位：湖南农业大学物联网工程专业
 * @Date: 2019/5/11 15:59
 * @开发版本：综合练习V0.1
 */
import java.sql.Connection;

public class PoolConn {
    Connection connection = null;//数据库连接
    boolean busy = false;//此链接是否正在使用的标志，默认没有正在使用

    public PoolConn(Connection connection){
        this.connection = connection;
    }//构造函数，根据一个 Connection 构造一个PoolConn对象

    public Connection getConnection() {
        return connection;
    }//返回此对象的连接

    public void setConnection(Connection connection) {
        this.connection = connection;
    }//设置此对象的连接

    public boolean isBusy() {
        return busy;
    }//返回此链接是否繁忙

    public void setBusy(boolean busy) {
        this.busy = busy;
    }//设置此链接是否繁忙
}
