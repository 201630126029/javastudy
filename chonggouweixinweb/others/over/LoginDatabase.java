package over;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 连接数据库的我们是服务开始的时候
@WebServlet(name = "LoginDatabase", urlPatterns = {"/Startup.do"}, loadOnStartup = 1)
public class LoginDatabase extends HttpServlet {
    private String mysql, url, user, passwd;
    private Connection conn;
    private Statement state;
    public static int SerialID ;
    public static HashMap<String,Item> Map=new HashMap<>();
    private void initDataBase() throws ClassNotFoundException, SQLException {
        Class.forName(mysql);
        conn = DriverManager.getConnection(url,user,passwd);
        state = conn.createStatement();
        ResultSet rs = state.executeQuery("select max(OrderID) as num from Ordered");
        SerialID = 1;
        while(rs.next()){
            SerialID = rs.getInt("num") + 1;
        }
    }
    private void closeDataBase(){
        if(state != null) {
            try{
                state.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try{
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        Readclass.init(getServletContext());
        ServletContext context=getServletContext();
        mysql = context.getInitParameter("mysql");
        url = context.getInitParameter("url");
        user = context.getInitParameter("user");
        passwd = context.getInitParameter("passwd");
        try{
            initDataBase();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Successful Connected!");
        context.setAttribute("conn",conn);
        context.setAttribute("state",state);
    }
    public void destroy(){
        closeDataBase();
        super.destroy();
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        response.sendRedirect("/index.html");
    }
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                      throws ServletException, IOException {
        doGet(request, response);
    }
}
