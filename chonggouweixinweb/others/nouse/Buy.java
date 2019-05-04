package nouse;

import over.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Buy" ,urlPatterns = {"/Buy.do"}, loadOnStartup = 2)
// 实现从图书详情界面直接修改
// 写触发器?不存在的
// 错了就错了，不管了
public class Buy extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Authentication.islegal(request)){
            String UserID = request.getParameter("UserID");
            String MessageID = request.getParameter("MessageID");
            String OrderID = String.valueOf(++LoginDatabase.SerialID);
            String BookID = request.getParameter("BookID");
            String Count = request.getParameter("Count");
            String Status = "1";
            String Extra = "未出货";
            //向两张表都插入数据
            if(sqlfilter.isNumber(UserID) && sqlfilter.isNumber(MessageID) &&
               sqlfilter.isNumber(BookID) && sqlfilter.isNumber(Count)){
                ArrayList<String> order = new ArrayList<>();
                ArrayList<String> info = new ArrayList<>();
                /************************/
                order.add(OrderID);
                order.add(UserID);
                order.add(MessageID);
                /************************/
                info.add(OrderID);
                info.add(BookID);
                info.add(Count);
                info.add(Status);
                info.add(Extra);
                /************************/
                String sql1 = Readclass.getInsertString("Ordered",order);
                String sql2 = Readclass.getInsertString("Info",info);
                boolean flag = (ExecuteUpd.execute(request,sql1) && ExecuteUpd.execute(request,sql2));
                if(!flag) response.setStatus(400);
                else response.setStatus(200);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
