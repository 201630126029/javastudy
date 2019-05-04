package over;

// 过滤掉奇奇怪怪的英文字符
public class sqlfilter {
    private static String danger="`~!@#$^&*()=|{}';'\\[].<>/?~！@#￥……&*——|{}";
    public static boolean isNumber(String text) {
        if(text == null) return false;
        int length = text.length();
        if(length>10) return false;
        for(int i=0; i<length; i++){
            char digit = text.charAt(i);
            if(!(digit>='0'&&digit<='9')) return false;
        }
        return true;
    }
    public static String filter(String sqlQue) {
        StringBuffer ft = new StringBuffer();
        for (int i=0; i<sqlQue.length(); i++) {
            char sign = sqlQue.charAt(i);
            if (danger.indexOf(sign) == -1) ft.append(sign);
        }
        return ft.toString();
    }
    public static boolean islegalStatus(String Status){
        if(!isNumber(Status)) return false;
        int Statusid = Integer.parseInt(Status);
        return Statusid>=1 && Statusid<=4;
    }
}
