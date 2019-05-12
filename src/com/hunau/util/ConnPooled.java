package com.hunau.util;/* *
 * @Description:
 * @param $params$
 * @Return: $returns$
 * @开发人员：余新伟
 * @开发单位：湖南农业大学物联网工程专业
 * @Date: 2019/5/11 16:12
 * @开发版本：综合练习V0.1
 */
import java.security.PublicKey;
import java.sql.*;
import java.util.Enumeration;
import java.util.Vector;
import java.util.stream.StreamSupport;
import com.hunau.entity.User;
import com.hunau.util.PoolConn;

public class ConnPooled {
    private String jdbcDriver="com.mysql.jdbc.Driver";//数据库驱动
    private String dbUrl="jdbc:mysql://localhost:3306/userlogintest?useSSL=false";//数据URL
    private String dbUsername="root";//数据库用户名
    private String dbPassword="yuxinwei";//数据库用户密码
    private String testTable="tb_user";//测试链接是否可用的测试表名，默认没有测试
    private int initialConnections=10;//连接池的初始大小
    private int incrementalConnections=5;//连接池自动增加的大小
    private int maxConnections=300;//连接池最大的大小
    private Vector connections =null;//存放连接池中数据库连接的向量，初始时为null
    private Connection conn=null;
    private ResultSet rs=null;
    private PreparedStatement ps=null;

    public ConnPooled(){
        try {
            Class.forName(jdbcDriver);
            conn=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public User UserLogin(String name,String pwd){
        User user=new User(1,"余新伟","yuxin","男",true);
        String sql="select id,name,pwd,sexy,isused from tb_user where name=? and pwd=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,pwd);
            rs=ps.executeQuery();
            if (rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setSexy(rs.getString("sexy"));
                user.setIsused(rs.getBoolean("isused"));
                //result=rs.getString("name");
                //result+="'"+rs.getString("pwd");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }

    public ConnPooled(String jdbcDriver,String dbUrl,String dbUsername,String dbPassword){
        this.jdbcDriver=jdbcDriver;
        this.dbUrl=dbUrl;
        this.dbUsername=dbUsername;
        this.dbPassword=dbPassword;
    }

    public String getTestTable() {
        return testTable;
    }

    public void setTestTable(String testTable) {
        this.testTable = testTable;
    }

    public int getInitialConnections() {
        return initialConnections;
    }

    public void setInitialConnections(int initialConnections) {
        this.initialConnections = initialConnections;
    }

    public int getIncrementalConnections() {
        return incrementalConnections;
    }

    public void setIncrementalConnections(int incrementalConnections) {
        this.incrementalConnections = incrementalConnections;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public Vector getConnections() {
        return connections;
    }

    public void setConnections(Vector connections) {
        this.connections = connections;
    }

    public synchronized void createPool() throws Exception{
        //确保连接池没有创建
        //如果连接池已经创建了，保存连接的向量，connections不会为空
        if (connections!=null) {
            return;//如果已经创建，则返回
        }
        //实例化JDBC Driver中指定的驱动类实例
        Driver driver = (Driver)(Class.forName(this.jdbcDriver).newInstance());
        DriverManager.registerDriver(driver);//注册JDBC驱动程序
        //创建保存连接的向量，初始时有0个元素
        connections=new Vector();
        //根据initialConnections中设置的值，创建连接
        createConnections(this.initialConnections);
        System.out.println("数据库连接池创建成功！");
    }

    private void createConnections(int numConnectionss)throws SQLException {
        for (int x=0;x<numConnectionss;x++){
            if (this.maxConnections>0&&this.connections.size()>=this.maxConnections){
                break;
            }
            //增加一个连接到连接池中（向量connections中）
            try {
                connections.addElement(new PoolConn(newConnection()));
            } catch (SQLException e){
                System.out.println("创建数据库失败！"+e.getMessage());
                throw new SQLException();
            }
            System.out.println("数据库连接已创建……");
        }
    }

    private Connection newConnection()throws SQLException{
        //创建一个数据库连接
        Connection conn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //如果这是第一次创建数据库连接，即检查数据库，获得此数据库允许支持的最大客户链接数目
        if (connections.size()==0){
            DatabaseMetaData metaData = conn.getMetaData();
            int driveMaxConnections = metaData.getMaxConnections();
            if(driveMaxConnections>0&&this.maxConnections>driveMaxConnections){
                this.maxConnections=driveMaxConnections;
            }
        }
        System.out.println("----------;创建一个新的数据库连接");
        return conn;//返回创建的新的数据库连接
    }

    private void wait(int mSeconds){
        try {
            Thread.sleep(mSeconds);
        }catch (InterruptedException e){

        }
    }

    private void closeConnections(Connection conn){
        try {
            conn.close();
        }catch (SQLException e){
            System.out.println("关闭数据库连接出错："+ e.getMessage());
        }
    }

    public  synchronized void closeConnectionPool()throws SQLException{
        if (connections==null){
            System.out.println("连接池不存在，无法关闭！");
            return;
        }
        PoolConn pConn = null;
        Enumeration enumerate = connections.elements();
        while (enumerate.hasMoreElements()){
            pConn=(PoolConn)enumerate.nextElement();
            if (pConn.isBusy()){
                wait(5000);
            }
            closeConnections(pConn.getConnection());
            //从连接池向量中删除他
            connections.removeElement(pConn);
        }
        connections=null;
    }
}
