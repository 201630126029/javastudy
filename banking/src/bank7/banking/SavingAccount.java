package bank7.banking;


public class SavingAccount extends Account {
    double interestRate;

    public SavingAccount(double int_balance, double interestRate) {
        super(int_balance);
        this.interestRate = interestRate;
    }
}
