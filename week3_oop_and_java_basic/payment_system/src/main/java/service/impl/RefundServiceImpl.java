package service.impl;

import model.Transaction;
import service.RefundService;

public class RefundServiceImpl implements RefundService {
    @Override
    public boolean processRefund(Transaction transaction, String reason) {
        if (transaction.getStatus().equalsIgnoreCase("SUCCESS")) {
            double amount = transaction.getAmount();
            transaction.getPaymentMethod().setBalance(
                    transaction.getPaymentMethod().getBalance() + amount
            );
            transaction.setStatus("REFUNDED");
            return true;
        }
        System.out.println("Refund failed: Transaction is not successful.");
        return false;
    }
}