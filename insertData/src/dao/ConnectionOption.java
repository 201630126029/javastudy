package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOption {
    //驱动包和数据库地址
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  //驱动包
    static final String DB_URL = "jdbc:mysql://120.78.138.100:3306/weixinweb";

    // 用户名和密码
    static final String USER = "weixinweb";
    static final String PASS = "TYah8MiSLyACkkr5";

    public static java.sql.Connection getConnection() {
        java.sql.Connection conn = null;
        try{
            // 加载驱动包
            Class.forName(JDBC_DRIVER);

            //连接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return conn;
        }
    }
    public static void closeConnection(java.sql.Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
//                logger.error("close connection failure", e);
            }
        }
    }
}