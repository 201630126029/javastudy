package Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SendGetAndPost {
    /**
     * 进行get请求一个网址
     * @param url 不包含参数的url
     * @param param_values 参数Map
     * @return 网页的返回值
     */
    public static String sendGET(String url, Map<String,String> param_values){
        StringBuffer sb = new StringBuffer(url);
        try{
            Generate(param_values, sb);
        }catch (Exception e){
            e.printStackTrace();
        }
        BufferedReader in = null;
        String result = "";
        try{
            String realURL = sb.toString();
            System.out.println("url是:"+realURL);
            URL finalURL = new URL(realURL);
            URLConnection conn = finalURL.openConnection();
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.connect();
            String line;
            readData(in,result);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将Map里面的param和value对应起来作为get参数直接放在后面
     * @param param_values 参数
     * @param sb 请求的url
     */
    private static void Generate(Map<String, String> param_values, StringBuffer sb)  throws NullPointerException{
        boolean flag = false;
        if(param_values == null){  //没有参数要处理
            return ;
        }
        for(Map.Entry<String,String> entry: param_values.entrySet()){
            String param = entry.getKey();
            String value = entry.getValue();
            if(flag){
                sb.append("&");
            }
            else{
                sb.append("?");
            }
            sb.append(param+"="+value);
            flag=true;
        }
    }

    /**
     * 进行Post请求，里面的请求参数还没解决，要先看下里面怎么搞的再写
     * @param url  请求的url
     * @return 网页返回值
     */
    public static String sendPOST(String url){
        PrintWriter out = null;
        BufferedReader in = null;
        String result ="";
        try{
            URL realURL = new URL(url);
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setDoOutput(true);  //注意这里还需要允许输出流
            out = new PrintWriter(conn.getOutputStream());
            //这里通过out将要传的参数传给服务器，但是暂时不晓得具体怎么操作
            //。。。

            out.flush();
            readData(in, result);
            in.close();
            out.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 就是将从in中读入所有数据放到result后面
     * @param in 输入流
     * @param result 操作的String
     * @throws IOException
     */
    public static void readData(BufferedReader in, String result) throws IOException {
        String line ;
        while((line = in.readLine()) != null){
            result += line+"\n";
        }
    }
}
