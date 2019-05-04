package nouse;

import over.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

// 这个界面的作用是对于该用户的地址进行管理
// 添加一个，删除一个，或者修改一个
// 如果修改失败，我们返回一个
@WebServlet(name = "Address", urlPatterns = {"/Address.do"}, loadOnStartup = 2)
public class Address extends HttpServlet {
    private boolean modify(HttpServletRequest request){
        if(!delete(request)) return false;
        return add(request);
    }
    private boolean add(HttpServletRequest request){
        String UserID = request.getParameter("UserID");
        String telephone = request.getParameter("telephone");
        String Address = request.getParameter("Address");
        boolean flag = false;
        if(sqlfilter.isNumber(UserID) && telephone !=null & Address !=null) {
            flag = true;
            telephone = sqlfilter.filter(telephone);
            Address = sqlfilter.filter(Address);
            String tmp = "select max(MessageID)+1 as ret from Address where UserID=" + UserID;
            Statement state = (Statement) (request.getServletContext().getAttribute("state"));
            try {
                ResultSet Result = state.executeQuery(tmp);
                String MessageID = "1";
                while (Result.next()) {
                    MessageID = Result.getString("ret");
                }
                ArrayList<String> add = new ArrayList<>();
                telephone = sqlfilter.filter(telephone);
                Address = sqlfilter.filter(Address);
                add.add(UserID);
                add.add(MessageID);
                add.add(telephone);
                add.add(Address);
                String sql = Readclass.getInsertString("Address", add);
                if(!ExecuteUpd.execute(request,sql)) flag = false;
            } catch (Exception e) {
                flag = false;
                e.printStackTrace();
            }
        }
        return flag;
    }
    private boolean delete(HttpServletRequest request){
        String UserID = request.getParameter("UserID");
        String MessageID = request.getParameter("MessageID");
        if(sqlfilter.isNumber(UserID) && sqlfilter.isNumber(MessageID)){
            String sql = "delete from Address where UserID="+ UserID +
                    " and MessageID=" + MessageID;
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
                boolean flag=modify(request);
                if(!flag) response.setStatus(400);
                else response.setStatus(200);
            }else if(action.equals("Add")){
                boolean flag=add(request);
                if(!flag) response.setStatus(400);
                else response.setStatus(200);
            }else if(action.equals("Delete")){
                boolean flag=delete(request);
                if(!flag) response.setStatus(400);
                else response.setStatus(200);
            }else if(action.equals("getinfo")){
                String UserID = request.getParameter("UserID");
                if (sqlfilter.isNumber(UserID)) {
                    String sql = "select MessageID,telephone,Address,MessageID from Address where UserID=" +  UserID;
                    HashMap<String,String> names = new HashMap<>();
                    names.put("MessageID","MessageID");
                    names.put("telephone","telephone");
                    names.put("Address","Address");
                    GetJson.Getinfo(request,response,sql,names);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
