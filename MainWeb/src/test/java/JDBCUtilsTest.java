import mvcapp.db.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtilsTest {
    @Test
    public void testConnection() throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }
}
