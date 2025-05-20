package model;

public abstract class PaymentMethod {
    private double balance;
    private String type;

    public PaymentMethod(double balance, String type) {
        this.balance = balance;
        this.type = type;
    }

    // Getters and setters for attributes
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    // Abstract method for overrides for payment-specific behavior
    public abstract boolean processPayment(double amount);
}