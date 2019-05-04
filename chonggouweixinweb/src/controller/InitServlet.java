package controller;

import com.sun.xml.internal.ws.api.policy.PolicyResolver;
import model.InitParam;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * 作为从web.xml中取出初始参数的类，存到InitParam的单例里面
 */
@WebServlet(loadOnStartup = 1, urlPatterns = "/init")
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        initParams();
    }

    private void initParams(){
        InitParam init = InitParam.getInitParam();
        ServletContext context = getServletContext();
        init.setDATABASE_CLASS(context.getInitParameter("DATABASE_CLASS"));
        init.setDATABASE_URL(context.getInitParameter("DATABASE_URL"));
        init.setPASSWORD(context.getInitParameter("PASSWORD"));
        init.setUSERNAME(context.getInitParameter("USERNAME"));
    }
}
