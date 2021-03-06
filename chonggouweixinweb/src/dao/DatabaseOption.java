package dao;

import java.sql.*;
import java.util.*;

/**
 * 数据库的操作方法放在这个里面
 */
public class DatabaseOption {

    private static final String SQL = "SELECT * FROM ";// 数据库操作

    /**
     * 获取表中所有字段名称
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = ConnectionOption.getConnection();
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
                if(stmt!=null) {stmt.close();}
            }catch(SQLException se2){
            }
        }
        System.out.println("Goodbye!");
    }



    public static Map<String, String> queryParam(String table, Collection<String> params){
        Connection conn = ConnectionOption.getConnection();
        Statement stmt = null;
        Map<String, String> param_values=null;
        try{
            stmt = conn.createStatement();
            String sql;
            sql = "select (" + append(params)+") from " + table;
            ResultSet rs = stmt.executeQuery(sql);
            param_values = new HashMap<>();
            while(rs.next()){
                for(String param:params){
                    String value = rs.getString(param);
                    param_values.put(param,value);
                }
            }
            rs.close();
            stmt.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return param_values;
    }
    /**
     * 向数据库里插入一组数据
     * @param table 要插入的表
     * @param key_values 插入的属性-值对
     */
    public static void insert( String table,Map<String,String> key_values){
        Connection con = ConnectionOption.getConnection();
        Statement stmt =null;
        Set<String> columns = key_values.keySet();
        Collection<String> values = key_values.values();
        try{
            StringBuffer sb = new StringBuffer();
            sb.append("insert into "+table);
            sb.append("(");
            sb.append(columns);
            sb.append(") values ");
            sb.append("(");
            sb.append(values);
            sb.append(")");
            String sql = sb.toString();
            stmt=con.createStatement();
            System.out.println("执行了 "+sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将values进行组合，以逗号隔开
     * @param values 值的list
     * @return 组合的String
     */
    private static String append(Collection<String> values) {
        StringBuffer sb = new StringBuffer();
        boolean flag;
        flag = false;
        for (String value : values) {
            if (flag) {sb.append(",");}
            sb.append(value);
            flag = true;
        }
        return sb.toString();
    }

}