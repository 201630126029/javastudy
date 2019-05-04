package nouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import com.alibaba.fastjson.JSONObject;
import over.ExecuteUpd;
import over.Readclass;

// 验证该用户的身份
// 首先我们要验证两件事
// 1.该用户的请求是合法请求
// 2.该用户发来的UserID如果存在。那么一定是对的UserID
public class Authentication {
    // 用cookie验证用户身份
    private static String code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String getrandomcode() {
			Random random=new Random();
		    StringBuffer sb=new StringBuffer();
		    for(int i=0;i<20;i++){
		      int number=random.nextInt(62);
		      sb.append(code.charAt(number));
		    }
		    return sb.toString();
    }
    public static String sendGET(String url){
        StringBuffer result= new StringBuffer();//访问返回结果
        BufferedReader read=null;//读取访问结果
        URL realurl=null;
        URLConnection connection=null;
        try {
            //创建url
            realurl=new URL(url);
            //打开连接
            connection=realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(read!=null){//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }
    public static void Store(HttpServletRequest request ,JSONObject json, String openid,String codeid) throws SQLException  {
        String sql1 = "select * from User where openID='" + openid +"'";
        Statement state = (Statement) request.getServletContext().getAttribute("state");
        ResultSet rs;
        rs = state.executeQuery(sql1);
        boolean isnull = true;
        if(rs.next()) isnull = false;
        String sql2 = "select max(UserID)+1 as ret from User";
        rs = state.executeQuery(sql2);
        String Userid = "1";
        while(rs.next()) {
            Userid = rs.getString("ret");
        }
        // 如果不存在这个用户
        if(isnull){
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(Userid);
            arrayList.add(openid);
            arrayList.add(codeid);
            String sql3 = Readclass.getInsertString("User",arrayList);
            ExecuteUpd.execute(request, sql3);
        }else {
            String sql4 = "update set codeID=" + "'" + codeid + "' where UserID = " + Userid;
            ExecuteUpd.execute(request, sql4);
        }
        json.put("UserID", Userid);
        json.put("codeID", codeid);
    }
    public static void init(HttpServletRequest request, HttpServletResponse response) throws IOException,SQLException {
        response.setHeader("Content - Encoding","utf-8");
        response.setContentType("text/json; charset=utf-8");
        String code =request.getParameter("code");
        //向微信服务器发送请求
        StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/jscode2session?");
        //开发者的小程序ID
        url.append("appid=wxdc9f23490d56599c");
        url.append("&secret=c39052978a3e80d55ff53227013384ce");
        url.append("&js_code=");
        url.append(code);
        url.append("&grant_type=authorization_code");
        //收到微信的回应
        String res = sendGET(url.toString());
        JSONObject json = JSONObject.parseObject(res);
        //从微信服务器得到验证码
        int errcode = json.getIntValue("errcode");
        PrintWriter out = response.getWriter();
        JSONObject toxcx = new JSONObject();
        if(errcode == 0) {
            String openid = json.getString("openid");
            String codeid = getrandomcode();
            Store(request,json,openid,codeid);
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
            out.flush();
        }
    }
    public static boolean islegal(HttpServletRequest request){
        return true;
    }
}
