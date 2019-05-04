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

// 检查该用户处于某一个状态下的所有订单
// 要注意的就是保证该用户账户的合法性
@WebServlet(name = "CheckOrders", urlPatterns = {"/CheckOrders.do"}, loadOnStartup = 2)
public class CheckOrders extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Authentication.islegal(request)){
            String UserID = request.getParameter("UserID");
            String Status = request.getParameter("status");
            if(sqlfilter.isNumber(UserID) && sqlfilter.isNumber(Status)) {
                String sql = "select Ordered.OrderID,Book.BookID,BookName,Count,Extra,telephone,Address,Ordered.MessageID " +
                        "from Ordered join Address on Address.UserID = Ordered.UserID and Address.MessageID " +
                        "= Ordered.MessageID join Info on Ordered.OrderID = Info.OrderID " +
                        "join Book on Book.BookID = Info.BookID " +
                        "where status =" + Status +
                        " and Ordered.UserID=" + UserID;
                HashMap<String,String> names = new HashMap<>();
                names.put("OrderID","OrderID");
                names.put("BookID","BookID");
                names.put("BookName","BookName");
                names.put("Count","Count");
                names.put("Extra","Extra");
                names.put("telephone","telephone");
                names.put("Address","Address");
                names.put("MessageID","MessageID");
                GetJson.Getinfo(request,response,sql,names);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
