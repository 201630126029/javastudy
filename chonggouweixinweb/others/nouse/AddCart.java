package nouse;

import over.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// 对购物车的操作
// 1.查询用户购物车内的书本的数量
// 2.在购物表中添加一条条目
// 3.在购物车中删除一条条目
// 4.在购物车中修改一条条目
@WebServlet(name = "AddCart", urlPatterns = {"/AddCart.do"}, loadOnStartup = 2)
public class AddCart extends HttpServlet {
    private boolean _modify(HttpServletRequest request){
        if(!_delete(request)) return false;
        return _add(request);
    }
    private boolean _add(HttpServletRequest request){
        String UserID = request.getParameter("UserID");
        String BookID = request.getParameter("BookID");
        String Count = request.getParameter("Count");
        if(sqlfilter.isNumber(UserID) && sqlfilter.isNumber(BookID) &&
           sqlfilter.isNumber(Count)){
            ArrayList<String> add = new ArrayList<>();
            add.add(UserID);
            add.add(BookID);
            add.add(Count);
            String sql = Readclass.getInsertString("Cart",add);
            return ExecuteUpd.execute(request,sql);
        }
        return false;
    }
    private boolean _delete(HttpServletRequest request){
        String UserID = request.getParameter("UserID");
        String BookID = request.getParameter("BookID");
        if(sqlfilter.isNumber(UserID) && sqlfilter.isNumber(BookID)){
            String sql = "delete from Cart where UserID="+ UserID +
                    " and BookID=" + BookID;
            return ExecuteUpd.execute(request,sql);
        }
        return false;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Authentication.islegal(request)){
            String action = request.getParameter("action");
            if(action == null){
                response.setStatus(400);
            }else if(action.equals("Modify")) {
                boolean flag=_modify(request);
                if(!flag) response.setStatus(400);
                else response.setStatus(200);
            }else if(action.equals("Add")){
                boolean flag=_add(request);
                if(!flag) response.setStatus(400);
                else response.setStatus(200);
            }else if(action.equals("Delete")){
                boolean flag=_delete(request);
                if(!flag) response.setStatus(400);
                else response.setStatus(200);
            }else if(action.equals("getinfo")){
                String UserID = request.getParameter("UserID");
                if (sqlfilter.isNumber(UserID)) {
                    String sql = "select Book.BookID,Count,BookName,Price from Cart " +
                            "join Book on Book.BookID=Cart.BookID "+
                            "where UserID=" +  UserID;
                    System.err.println(sql);
                    HashMap<String,String> names = new HashMap<>();
                    names.put("BookName","BookName");
                    names.put("BookID","BookID");
                    names.put("Count","Count");
                    names.put("Price","Price");
                    GetJson.Getinfo(request,response,sql,names);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
