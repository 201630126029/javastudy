package bank8.banking;

import java.util.Iterator;

public class CustomerReport {

    public void generateReport(){

        Bank bank = Bank.getBank();
        Iterator<Customer> iterator = bank.getCustomers();
        Customer customer=null;
        while(iterator.hasNext()){
            customer= iterator.next();
            // Generate a report
            System.out.println("\t\t\tCUSTOMERS REPORT");
            System.out.println("\t\t\t================");

            for (int cust_idx = 0; cust_idx < bank.getNumOfCustomers(); cust_idx++) {
                customer = bank.getCustomer(cust_idx);

                System.out.println();
                System.out.println("Customer: "
                        + customer.getLastName() + ", "
                        + customer.getFirstName());

                Iterator<Account> accounts = customer.getAccounts();
                while(accounts.hasNext()){
                    Account account = accounts.next();
                    String account_type = "";

                    // Determine the account type
                    /*** Step 1:
                     **** Use the instanceof operator to test what type of account
                     **** we have and set account_type to an appropriate value, such
                     **** as "Savings Account" or "Checking Account".
                     ***/

                    if (account instanceof CheckingAccount) {
                        account_type = "Checking Account";
                    } else {
                        account_type = "Saving Account";
                    }
                    // Print the current balance of the account
                    /*** Step 2:
                     **** Print out the type of account and the balance.
                     **** Feel free to use the currency_format formatter
                     **** to generate a "currency string" for the balance.
                     ***/

                    System.out.println(account_type+":current balance is ￥"+account.getBalance());
                }
            }
        }
    }
}
