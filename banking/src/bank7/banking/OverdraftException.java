package bank7.banking;

public class OverdraftException extends Exception {
    double deficit;

    public double getDeficit() {
        return deficit;
    }

    public OverdraftException(String message, double deficit) {
        super(message);
        this.deficit = deficit;
    }
}
