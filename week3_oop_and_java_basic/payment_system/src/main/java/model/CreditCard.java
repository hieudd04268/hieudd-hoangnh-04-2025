package model;

public class CreditCard extends PaymentMethod {
    private double creditLimit; // Maximum credit limit
    private double interestRate; // Interest rate for outstanding payments
    private double outstandingBalance; // Amount yet to be settled

    public CreditCard(double balance, double creditLimit, double interestRate) {
        super(balance, "Credit Card");
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.outstandingBalance = 0.0;
    }

    public double getCreditLimit() { return creditLimit; }
    public void setCreditLimit(double creditLimit) { this.creditLimit = creditLimit; }
    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
    public double getOutstandingBalance() { return outstandingBalance; }
    public void setOutstandingBalance(double outstandingBalance) { this.outstandingBalance = outstandingBalance; }

    @Override
    public boolean processPayment(double amount) {
        if (getBalance() + creditLimit - outstandingBalance >= amount) {
            outstandingBalance += amount;
            return true;
        } else {
            return false;
        }
    }
}