import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.util.List;

/**
 * 使用Query Runner提供其具体的实现，子类需提供的泛型类型
 * @param <T>
 */
public class JDBCDaoImpl<T> implements DAO<T> {

    private QueryRunner queryRunner = null;
    private Class<T> type;
    public JDBCDaoImpl(){
        queryRunner = new QueryRunner();
    }
    public void update(Connection conn, String sql, Object... args) {

    }

    public T get(Connection conn, String sql, Object... args) {
        return queryRunner.query(conn, sql, new BeanHandler<>(),args);
    }

    public List<T> getForList(Connection conn, String sql, Object... args) {
        return null;
    }

    public <E> E getForValue(Connection conn, String sql, Object[]... args) {
        return null;
    }

    public void batch(Connection conn, String sql, Object... args) {

    }
}
