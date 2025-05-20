package model;

public class BankTransfer extends PaymentMethod {
    private final double feeThreshold = 2000;
    private final double feeRate = 0.01;

    public BankTransfer(double balance) {
        super(balance, "Bank Transfer");
    }

    @Override
    public boolean processPayment(double amount) {
        double fee = amount > feeThreshold ? amount * feeRate : 0;
        if (getBalance() >= amount + fee) {
            setBalance(getBalance() - (amount + fee));
            return true;
        }
        return false;
    }
}