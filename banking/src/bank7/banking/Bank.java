package bank7.banking;

/**
 * 银行类
 */
public class Bank {
    private Customer[] customers;
    private int numberOfCustomers;
    private static Bank bank= new Bank();
    private  Bank() {
        customers = new Customer[10];
        numberOfCustomers=0;
    }

    public void addCustomer(String f, String l){ //这里虽然添加了客户，但是实际上是没有账号的
        customers[numberOfCustomers] = new Customer(f,l);
        numberOfCustomers++;
    }
    public Customer getCustomer(int index){
        return customers[index];
    }

    public int getNumOfCustomers() {
        return numberOfCustomers;
    }

    public static Bank getBank(){
        return bank;
    }

}
