package nouse;

import over.GetJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

// 得到首页信息
@WebServlet(name = "GetFrontInfo", urlPatterns = {"/GetFrontInfo.do"}, loadOnStartup = 2)
public class GetFrontInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 其实我们只要返回BookID对应的名称就可以了
        // 然后根据书本名字就可以在对应的目录
        if(Authentication.islegal(request)){
           String sql = "select BookID,BookName,Introduce from Book where BookID in (select BookID from Display)";
           HashMap<String,String> names = new HashMap<>();
           names.put("BookID","BookID");
           names.put("BookName","BookName");
           names.put("Introduce","Introduce");
           GetJson.Getinfo(request,response,sql,names);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
