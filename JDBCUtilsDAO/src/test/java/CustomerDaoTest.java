import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CustomerDaoTest {
    CustomerDao customerDao = new CustomerDao();
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CustomerDao.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @org.junit.Test
    public void update() {
    }

    @org.junit.Test
    public void get(){
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnection();
            String sql = "select id, name customerName, email, birth from customers where id = ?";
            Customer customer = customerDao.get(conn, sql, 5);
            System.out.println(customer);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection(conn);
        }
    }

    @org.junit.Test
    public void getForList() {
    }

    @org.junit.Test
    public void getForValue() {
    }

    @org.junit.Test
    public void batch() {
    }
}
