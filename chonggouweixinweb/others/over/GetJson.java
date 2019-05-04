package over;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class GetJson {
    public static void Getinfo(HttpServletRequest request, HttpServletResponse response, String QueryString, Map<String,String> names) throws ServerException, IOException {
        response.setHeader("Content - Encoding","utf-8");
        response.setContentType("text/json; charset=utf-8");
        ServletContext context = request.getServletContext();
        PrintWriter out = response.getWriter();
        Statement state = (Statement)context.getAttribute("state");
        JSONArray jsonArray = new JSONArray();
        try{
            ResultSet rs=state.executeQuery(QueryString);
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                for (Map.Entry<String, String> entry : names.entrySet()) {
                    jsonObject.put(entry.getKey(), rs.getString(entry.getValue()));
                }
                //对于图片的话，我们直接返回对应的图书封面的url就可以了
                jsonArray.add(jsonObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        out.println(jsonArray);
    }
}
