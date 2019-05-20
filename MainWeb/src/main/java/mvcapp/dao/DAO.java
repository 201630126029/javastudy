package mvcapp.dao;

import jdk.nashorn.internal.scripts.JD;
import mvcapp.db.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 封装了基本的CRUD的方法，以供子类使用。
 * 当前DAO直接在方法中获取数据库连接
 * @param <T>:当前DAO处理的实体类的类型
 */
public class DAO<T> {
    private Class<T> clazz;

    private QueryRunner queryRunner = new QueryRunner();

    public DAO(){
        //在实际的子类里面得到父类的类型
        Type superClass = getClass().getGenericSuperclass();

        //是个泛型的类
        if(superClass instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType)superClass;
            //得到其中的Type类型数组
            Type[] typeArgs = parameterizedType.getActualTypeArguments();

            if(typeArgs != null && typeArgs.length > 0){
                if(typeArgs[0] instanceof Class){
                    clazz = (Class<T>) typeArgs[0];
                }
            }
        }
    }

    /**
     * 返回对应查询得到的 列的值 或 查询的一个参数
     * @param sql
     * @param args
     * @param <E>
     * @return
     */
    public <E> E getForValue(String sql, Object ... args){
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnection();
            return queryRunner.query(conn, sql, new ScalarHandler<>(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.releaseConnection(conn);
        }
        return null;
    }

    /**
     * 返回对应的 T 的List
     * @param sql
     * @param args
     * @return
     */
    public List<T> getForList(String sql, Object ... args){
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnection();
            return queryRunner.query(conn, sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.releaseConnection(conn);
        }
        return null;
    }
    /**
     * 返回对应的 T 的一个实例对象
     * @param sql
     * @param args
     * @return
     */
    public T get(String sql, Object ... args){
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnection();
            return queryRunner.query(conn,sql,new BeanHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.releaseConnection(conn);
        }
        return null;
    }

    /**
     * 该方法封装了INSERT DELETE UPDATE 操作
     * @param sql SQL语句
     * @param args 填充sql语句的占位符
     */
    public void update(String sql, Object ... args){
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnection();
            queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {JDBCUtils.releaseConnection(conn);
        }
    }
}
