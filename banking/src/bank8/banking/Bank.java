package bank8.banking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 银行类
 */
public class Bank {
    private List<Customer> customers;
    private static Bank bank= new Bank();
    private  Bank() {
        customers = new ArrayList<>();
    }

    public void addCustomer(String f, String l){ //这里虽然添加了客户，但是实际上是没有账号的
        customers.add(new Customer(f,l));
    }
    public Customer getCustomer(int index){
        return customers.get(index);
    }

    public int getNumOfCustomers() {
        return customers.size();
    }

    public static Bank getBank(){
        return bank;
    }

    public Iterator<Customer> getCustomers() {
        return customers.iterator();
    }
}
