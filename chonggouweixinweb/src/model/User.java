package model;

/**
 * 用户类的model
 */
public class User {
    private String open_id;
    private String code_id;

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public void setCode_id(String code_id) {
        this.code_id = code_id;
    }

    public String getOpen_id() {
        return open_id;
    }

    public String getCode_id() {
        return code_id;
    }
}
