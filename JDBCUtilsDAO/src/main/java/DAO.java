import java.sql.Connection;
import java.util.List;

/**
 * 访问数据的DAO接口，里边定义好访问数据表的各种方法
 * @param <T> DAO处理的泛型
 */
public interface DAO<T> {
    /**
     * INSERT、UPDATE、DALETE、
     *  @param conn 数据库连接
     * @param sql 查询的语句
     * @param args 填充占位符的可变参数
     */
    void update(Connection conn, String sql, Object ... args);

    /**
     * 返回T的一个对象
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    T get(Connection conn, String sql, Object ... args);

    /**
     * 返回 T 的一个集合
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    List<T> getForList(Connection conn, String sql, Object ... args);

    /**
     * 返回具体的一个值，比如一个人的名字，一群人的平均工资等
     * @param conn
     * @param sql
     * @param args 填充占位符的可变参数的数组
     * @param <E>
     * @return
     */
    <E> E getForValue(Connection conn, String sql, Object[] ... args);

    /**
     * 批量处理的方法，
     * @param conn
     * @param sql
     * @param args
     */
    void batch(Connection conn,String sql, Object ... args);
}
