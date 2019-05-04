package dao;

import model.InitParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据的连接的建立和关闭操作
 */
public class ConnectionOption {
    //驱动包和数据库地址
    static final String JDBC_DRIVER ;  //驱动包
    static final String DB_URL;

    // 用户名和密码
    static final String USER;
    static final String PASS;

    static {
        InitParam init = InitParam.getInitParam();
        JDBC_DRIVER=init.getDATABASE_CLASS();
        DB_URL=init.getDATABASE_URL();
        USER=init.getUSERNAME();
        PASS=init.getPASSWORD();
    }
    /**
     * 获取数据库的Connection对象
     * @return 连接Connection对象
     */
    public static Connection getConnection() {
        Connection conn = null;
        try{
            // 加载驱动包
            Class.forName(JDBC_DRIVER);

            //连接
            System.out.println(DB_URL);
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
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
//                logger.error("close connection failure", e);
            }
        }
    }
}