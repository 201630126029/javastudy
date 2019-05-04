package nouse;

import over.GetJson;
import over.sqlfilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

// 首先明确我们这个目的是目标页面的分类功能，根据用户所需要返回所有该类别的图书
@WebServlet(name = "BookInfo", urlPatterns = {"/BookInfo.do"}, loadOnStartup = 2)
public class BookInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {
        if (Authentication.islegal(request)) {
            String bookid = request.getParameter("bookid");
            String fuzzy = request.getParameter("fuzzy");
            String bookname = request.getParameter("bookname");
            if(bookname!=null) bookname = sqlfilter.filter(bookname);
            boolean isfuzzy = false;
            if(fuzzy != null && fuzzy.equals("true") && bookname != null) isfuzzy = true;
            // 防止sql注入
            if (isfuzzy || sqlfilter.isNumber(bookid)) {
                String sql = "";
                if(!isfuzzy) sql = "select * from Book where BookId = " + bookid;
                else sql = "select * from Book where BookName like '"+bookname+"%'";
                HashMap<String,String> names = new HashMap<>();
                names.put("BookID","BookId");
                names.put("BookName","BookName");
                names.put("Author","Author");
                names.put("Price","Price");
                names.put("Introduce","Introduce");
                GetJson.Getinfo(request,response,sql,names);
            }
        }
    }
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
