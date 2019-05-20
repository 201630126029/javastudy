package mvcapp.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "customerServlet")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method){
            case "add": add(req,resp);break;
            case "query":query(req,resp);break;
            case "delete":delete(req,resp);break;
            default:
                System.out.println("err");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
        System.out.println("delete.");
    }

    private void query(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
        System.out.println("query.");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        System.out.println("add.");
    }
}
