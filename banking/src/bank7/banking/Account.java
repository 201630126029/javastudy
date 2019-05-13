package bank7.banking;

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

    public void withdraw(double draw) throws OverdraftException{//取钱
        if(draw > balance){
            throw  new OverdraftException("资金不足：", draw-balance);
        }
        balance-= draw;
    }
}
