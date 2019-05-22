package mvcapp.servlet;


import mvcapp.damain.Customer;
import mvcapp.dao.CustomerDAO;
import mvcapp.dao.impl.CustomerDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(urlPatterns = "*.do",loadOnStartup = 1)
public class CustomerServlet extends HttpServlet {

    private static CustomerDAO customerDAO = new CustomerDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        String methodName = servletPath.substring(1, servletPath.length()-3);
        Method method = null;
        try {
            method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            resp.sendRedirect("/error.jsp");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
        System.out.println("delete.");
    }

    private void query(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
        //调用CustomerDao的方法得到所有的Customer的集合
        List<Customer> customers=null;
        try{
            customers = customerDAO.getAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(customers);
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    private void addCustomer(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        System.out.println("add.");
    }
}
