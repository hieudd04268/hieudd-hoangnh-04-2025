package model;

import constance.UserStatus;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private UserStatus status;
    private ArrayList<PaymentMethod> paymentMethods;
    private boolean isBusinessAccount;
    private int failedAttemptsLogin;
    private boolean isAccountLocked;
    private ArrayList<Transaction> transactions;

    public User() {}

    public User(String username, UserStatus status,
                String password, boolean isBusinessAccount) {
        this.username = username;
        this.paymentMethods = new ArrayList<>();
        this.status = status;
        this.password = password;
        this.isBusinessAccount = isBusinessAccount;
        this.failedAttemptsLogin = 0;
        this.isAccountLocked = false;
        this.transactions = new ArrayList<>();
    }

    // Getters and setters for all attributes
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public UserStatus getStatus() { return status; }
    public void setStatus(UserStatus status) { this.status = status; }
    public boolean isBusinessAccount() { return isBusinessAccount; }
    public void setBusinessAccount(boolean businessAccount) {
        this.isBusinessAccount = businessAccount;
    }
    public int getFailedAttemptsLogin() { return failedAttemptsLogin; }
    public void setFailedAttemptsLogin(int failedAttemptsLogin) {
        this.failedAttemptsLogin = failedAttemptsLogin;
    }
    public boolean isAccountLocked() { return isAccountLocked; }
    public void setAccountLocked(boolean accountLocked) { this.isAccountLocked = accountLocked; }
    public ArrayList<PaymentMethod> getPaymentMethods() { return paymentMethods; }
    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) { this.paymentMethods = paymentMethods; }
    public ArrayList<Transaction> getTransactions() { return transactions; }
    public void setTransactions(ArrayList<Transaction> transactions) { this.transactions = transactions; }

    public void addPaymentMethod(PaymentMethod method) { this.paymentMethods.add(method); }
    public void removePaymentMethod(PaymentMethod method) { this.paymentMethods.remove(method); }
}