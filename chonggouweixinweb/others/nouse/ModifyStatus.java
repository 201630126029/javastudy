package nouse;

import over.ExecuteUpd;
import over.sqlfilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//这个作用是改变一个订单的状态
// 我们这里订单状态定义了四个状态
// 1 待付款
// 2 待发货
// 3 待收货
// 4 收货完成
@WebServlet(name = "ModifyStatus", urlPatterns = {"/ModifyStatus.do"}, loadOnStartup = 2)
public class ModifyStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Authentication.islegal(request)){
            String OrderID = request.getParameter("OrderID");
            String Status = request.getParameter("status");
            String Extra = request.getParameter("Extra");
            if(Extra == null) Extra = "";
            // 并且我们这里要做一个验证保证用户的身份正确
            if(sqlfilter.isNumber(OrderID) && sqlfilter.islegalStatus(Status)){
                Extra = sqlfilter.filter(Extra);
                String sql = "update Info set Status='"+ Status + "'" +
                        ",Extra='" + Extra + "' where OrderID=" + OrderID;
                boolean flag = ExecuteUpd.execute(request,sql);
                if(!flag) response.setStatus(400);
                else response.setStatus(200);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
