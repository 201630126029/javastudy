package bank5_1.banking;


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
    public boolean withdraw(double draw) {
        if(balance >= draw){
            balance-= draw;
            return true;
        }
        if(balance+over >= draw){
            over -= (draw-balance);
            balance=0;
            return true;
        }
        return false;
    }
}
