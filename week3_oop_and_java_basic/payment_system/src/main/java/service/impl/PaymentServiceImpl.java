package service.impl;

import model.PaymentMethod;
import model.Transaction;
import model.User;
import service.PaymentService;

import java.util.UUID;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public boolean processPayment(User user, double amount) {
        for (PaymentMethod method : user.getPaymentMethods()) {
            if (method.processPayment(amount)) {
                logTransaction(user, method, amount, "SUCCESS");
                return true;
            }
        }
        System.out.println("Insufficient balance across available payment methods!");
        return false;
    }

    private void logTransaction(User user, PaymentMethod method, double amount, String status) {
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                user,
                method,
                amount,
                status
        );
        user.getTransactions().add(transaction);
    }
}