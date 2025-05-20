package service.impl;

import constance.UserStatus;
import model.Transaction;
import model.User;
import service.FraudDetectionService;

public class FraudDetectionServiceImpl implements FraudDetectionService {
    @Override
    public boolean isSuspiciousTransaction(Transaction transaction) {
        return transaction.getAmount() > 5000; // Example fraud condition
    }

    @Override
    public void markAccountRisky(User user) {
        user.setStatus(UserStatus.SUSPENDED);
    }
}