package service;

import model.Transaction;
import model.User;

public interface FraudDetectionService {
    boolean isSuspiciousTransaction(Transaction transaction);
    void markAccountRisky(User user);
}
