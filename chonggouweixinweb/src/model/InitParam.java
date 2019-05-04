package model;


/**
 * 作为单例，存应用的初始值
 */
public class InitParam{
    private String DATABASE_CLASS ;
    private String DATABASE_URL;
    private String USERNAME ;
    private String PASSWORD;
    private static InitParam initParam;

    /**
     * 获取单例对象
     * @return
     */
    public static InitParam getInitParam(){
        if(initParam == null){
            initParam=new InitParam();
        }
        return initParam;
    }

    private InitParam(){
        super();
    }

    public void setDATABASE_URL(String DATABASE_URL) {
        this.DATABASE_URL = DATABASE_URL;
    }

    public void setDATABASE_CLASS(String DATABASE_NAME) {
        this.DATABASE_CLASS = DATABASE_NAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getDATABASE_URL() {
        return DATABASE_URL;
    }

    public String getDATABASE_CLASS() {
        return DATABASE_CLASS;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
