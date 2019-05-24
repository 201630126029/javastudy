package mvcapp.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/** * JDBC操作的工具类
 */
public class JDBCUtils {
    private static DataSource dataSource = null;

    static {
        //数据源最好只创建一次

        try {
            System.out.println("initial datasource...");
            dataSource = new ComboPooledDataSource("mvcapp");
            System.out.println("initial datasource over");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 返回数据源的一个Connection的对象
     * @return
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 释放Connection连接
     * @param conn
     */
    public static void releaseConnection(Connection conn){
            if(conn != null){
                return ;
            }
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
    }
}
