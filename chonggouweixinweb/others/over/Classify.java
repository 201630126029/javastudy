package over;

import nouse.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

// 首先明确我们这个目的是目标页面的分类功能，根据用户所需要返回所有该类别的图书
@WebServlet(name = "Classify", urlPatterns = {"/Classify.do"}, loadOnStartup = 2)
public class Classify extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Authentication.islegal(request)){
            String booktype = request.getParameter("booktype");
            // 防止sql注入
            if(sqlfilter.isNumber(booktype)) {
                String sql = "select * from Book where Type = " + booktype;
                HashMap<String,String> names = new HashMap<>();
                names.put("BookID","BookID");
                names.put("BookName","BookName");
                GetJson.Getinfo(request,response,sql,names);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
