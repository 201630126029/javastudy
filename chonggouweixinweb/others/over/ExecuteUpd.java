package over;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Statement;

public class ExecuteUpd {
    public static boolean execute(HttpServletRequest request, String QueryString){
        ServletContext context = request.getServletContext();
        Statement state = (Statement)context.getAttribute("state");
        boolean flag = true;
        try{
            state.execute(QueryString);
        }catch (Exception e){
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
