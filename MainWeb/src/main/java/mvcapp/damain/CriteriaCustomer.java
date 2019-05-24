package mvcapp.damain;

public class CriteriaCustomer {
    private String name;
    private String address;
    private String phone;

    public CriteriaCustomer() {
    }

    public CriteriaCustomer(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        if(name == null){
            name = "%";
        }
        else {
            name = "%"+name+"%";
        }
        return name;
    }

    public String getAddress() {
        if(address == null){
            address = "%";
        }
        else {
            address = "%"+address+"%";
        }
        return address;
    }

    public String getPhone() {
        if(phone == null){
            phone = "%";
        }
        else {
            phone = "%"+phone+"%";
        }
        return phone;
    }

    @Override
    public String toString() {
        return "CriteriaCustomer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
