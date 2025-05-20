package model;

import java.util.Date;

public class Transaction {
    private String transactionId;
    private User user;
    private PaymentMethod paymentMethod;
    private double amount;
    private Date timestamp;
    private String status;

    public Transaction(String transactionId, User user, PaymentMethod paymentMethod, double amount, String status) {
        this.transactionId = transactionId;
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
        this.timestamp = new Date();
    }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}