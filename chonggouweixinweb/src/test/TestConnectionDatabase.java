package test;

import dao.ConnectionOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * 测试数据库连接是否成功
 */
@WebServlet(urlPatterns = "/test")
public class TestConnectionDatabase  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = ConnectionOption.getConnection();
        String message =null;
        if(conn != null){
           message="连接成功.";
        }
        else{
            message="连接不成功.";
        }
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter print = resp.getWriter();
        resp.setStatus(200);
        print.println(message);
        print.close();
    }
}
