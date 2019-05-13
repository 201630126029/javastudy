package bank5_1.banking;



/**
 * 客户类
 */
public class Customer {
    private String firstName,lastName;
    private Account accounts[];
    private int numberOfAccounts;

    public Customer(String f, String l){
        firstName=f;
        lastName=l;
        numberOfAccounts=0;
        accounts=new Account[5];
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addAccount(Account account){
        accounts[numberOfAccounts]=account;
        numberOfAccounts++;
    }
    public Account getAccount(int index){
        return accounts[index];
    }

    public int getNumOfAccounts(){
        return numberOfAccounts;
    }
}
