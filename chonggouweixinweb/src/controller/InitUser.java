package controller;

import Request.SendGetAndPost;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.Session;
import dao.DatabaseOption;
import model.InitParam;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 对用户进行初始化，在数据库创建用户对应的信息
 */
@WebServlet(urlPatterns = "/initUser",loadOnStartup = 1)
public class InitUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        resp.setContentType("text/html;charset=utf-8");
        String code = req.getParameter("code");
        HttpSession session = req.getSession(true);
        // 空的code，非法访问
        if(code == null){
            try {
                resp.sendError(400);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuffer url = getParam(code);
        String res = null;
        try {
            res = SendGetAndPost.sendGET(url.toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json = JSONObject.parseObject(res);
        int errcode = json.getIntValue("errcode");
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message =null;
        if(errcode == 0) {  //合法用户
            String openid = json.getString("openid");
            message = "success.";
            DatabaseOption.insert("user", getUserMap(openid,session.getId() ));
        }
        else if(errcode == -1) {  // 系统繁忙，此时请开发者稍候再试
            message="系统繁忙，请稍候再试";
        }
        else if(errcode == 40029) {  // code 无效
            message="code无效";
        }
        else if(errcode == 45011){ //频率限制，每个用户每分钟100次
            message="操作过于频繁";
        }
        out.println(message);
        out.flush();
        out.close();
    }

    private StringBuffer getParam(String code) {
        InitParam init = InitParam.getInitParam();
        String openid_url=init.getOpenid_url();
        StringBuffer url = new StringBuffer(openid_url);
        url.append("appid="+init.getAppid());
        url.append("&secret="+init.getSecret());
        url.append("&js_code="+code);
        url.append("&grant_type=authorization_code");
        return url;
    }

    private Map<String, String> getUserMap(String openId, String codeId){
        Map<String,String> mp= new HashMap<>();
        mp.put("openID", openId);
        mp.put("codeID", codeId);
        return mp;
    }
}
