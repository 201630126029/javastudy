package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据的连接的建立和关闭操作
 */
public class ConnectionOption {
    //驱动包和数据库地址
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  //驱动包
    static final String DB_URL = "jdbc:mysql://localhost:3306/weixinweb?useUnicode=true&characterEncoding=utf-8";

    // 用户名和密码
    static final String USER = "weixinweb";
    static final String PASS = "rAIubEa1hvDPK7mM";

    /**
     * 获取数据库的Connection对象
     * @return 连接Connection对象
     */
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

    /**
     * 关闭数据库连接
     * @param conn
     */
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