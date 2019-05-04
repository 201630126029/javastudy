package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 一些杂乱的操作函数放在这个里面
 */
public class Option {

    private static final String SQL = "SELECT * FROM ";// 数据库操作

    /**
     * 获取表中所有字段名称
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        java.sql.Connection conn = ConnectionOption.getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
//            logger.error("getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    ConnectionOption.closeConnection(conn);
                } catch (SQLException e) {
//                    logger.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columnNames;
    }

    /**
     * 查询表的所有数据,进行打印
     * @param table  表名
     *  null
     */
    public static void  queryAll(String table) {
        Connection conn = ConnectionOption.getConnection();
        Statement stmt = null;
        List<String> columnNames = getColumnNames(table);
        try{
            stmt = conn.createStatement();
            String sql;
            sql = SQL + table;
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                for(String column: columnNames){
                    System.out.print(column+"    ");
                }
                System.out.println();
                for(String column: columnNames){
                    System.out.print(rs.getString(column)+"    ");
                }
                System.out.println();
            }

            rs.close();
            stmt.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
        }
        System.out.println("Goodbye!");
    }


    /**
     * 向数据库里插入一组数据
     * @param table 要插入的表
     * @param values 插入的值
     */
    public static void insert( String table,List<String> columns, List<String> values){
        Connection con = ConnectionOption.getConnection();
        Statement stmt =null;
        boolean flag;
        try{
            StringBuffer sb = new StringBuffer();
            sb.append("insert into "+table+"(");
            append(columns, sb);

            sb.append(" values (");
            append(values, sb);
            String sql = sb.toString();
            stmt=con.createStatement();
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionOption.closeConnection(con);
    }

    private static void append(List<String> values, StringBuffer sb) {
        boolean flag;
        flag = false;
        for (String value : values) {
            if (flag) sb.append(",");
            sb.append(value);
            flag = true;
        }
        sb.append(")");
    }
}