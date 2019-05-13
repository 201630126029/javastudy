package bank8.banking;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 客户类
 */
public class Customer {
    private String firstName, lastName;
    private List<Account> accounts;

    public Customer(String f, String l) {
        firstName = f;
        lastName = l;
        accounts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccount(int index) {
        return accounts.get(index);
    }

    public int getNumOfAccounts() {
        return accounts.size();
    }

    public Iterator<Account> getAccounts() {
        return accounts.iterator();
    }
}
