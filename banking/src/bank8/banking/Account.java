package bank8.banking;

/**
 * 账号类
 */
public class Account {
    protected double balance;
    public Account(double int_balance){
        this.balance=int_balance;
    }
    public double getBalance(){
        return balance;
    }

    public boolean deposit(double add){  //存钱
        balance+=add;
        return true;
    }

    public boolean withdraw(double draw){//取钱
        if(draw > balance){
            return false;
        }
        balance-= draw;
        return true;
    }
}
