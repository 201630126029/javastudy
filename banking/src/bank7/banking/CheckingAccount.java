package bank7.banking;


public class CheckingAccount extends Account {

    double over;
    public CheckingAccount(double int_balance) {
        super(int_balance);
    }

    public CheckingAccount(double int_balance, double over){
        super(int_balance);
        this.over=over;
    }
    @Override
    public void withdraw(double draw) {
        if(balance >= draw){
            balance-= draw;
        }
        if(balance+over >= draw){
            over -= (draw-balance);
            balance=0;
        }
    }
}
