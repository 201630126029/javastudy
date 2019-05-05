package controller;

import Request.SendGetAndPost;
import com.alibaba.fastjson.JSONObject;
import model.InitParam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 对用户进行初始化，在数据库创建用户对应的信息
 */
@WebServlet(urlPatterns = "/initUser",loadOnStartup = 1)
public class InitUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        resp.setContentType("text/html;charset=utf-8");
        String code = req.getParameter("code");
        InitParam init = InitParam.getInitParam();
        System.out.println("initUser调用成功.");
        //向微信服务器发送请求
        String openid_url=init.getOpenid_url();
        StringBuffer url = new StringBuffer(openid_url);
        url.append("appid="+init.getAppid());
        url.append("&secret="+init.getSecret());
        url.append("&js_code="+code);
        url.append("&grant_type=authorization_code");
        //收到微信的回应
        String res = SendGetAndPost.sendGET(url.toString(), null);
        System.out.println("res= "+res);
        JSONObject json = JSONObject.parseObject(res);
        //从微信服务器得到验证码
        int errcode = json.getIntValue("errcode");
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject toxcx = new JSONObject();
        String message =null;
        if(errcode == 0) {
            String openid = json.getString("openid");
//            String codeid = getrandomcode();
//            Store(request,json,openid,codeid);
        }
        else if(errcode == -1) {  // 系统繁忙，此时请开发者稍候再试
            toxcx.put("errcode","-1 系统繁忙，请稍候再试");
            out.println(toxcx);
        }
        else if(errcode == 40029) {  // code 无效
            toxcx.put("errcode","40029 code无效");
            out.println(toxcx);
        }
        else if(errcode == 45011){ //频率限制，每个用户每分钟100次
            toxcx.put("errcode","45011 操作过于频繁");
            out.println(toxcx);
        }
        out.flush();
        out.close();
    }
}
