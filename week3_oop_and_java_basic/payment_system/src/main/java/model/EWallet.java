package model;

public class EWallet extends PaymentMethod {
    private final double dailyLimit = 5000; // Maximum transaction allowed per day
    private double dailySpent; // Track daily spending

    public EWallet(double balance) {
        super(balance, "E-Wallet");
        this.dailySpent = 0.0;
    }

    // Getters and setters
    public double getDailySpent() { return dailySpent; }
    public void setDailySpent(double dailySpent) { this.dailySpent = dailySpent; }

    @Override
    public boolean processPayment(double amount) {
        if (getBalance() >= amount && dailySpent + amount <= dailyLimit) {
            setBalance(getBalance() - amount);
            dailySpent += amount;
            return true;
        } else {
            return false;
        }
    }
}