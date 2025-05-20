package service;

import model.Transaction;
import model.User;

import java.util.List;

public interface TransactionService {
    void recordTransaction(Transaction transaction);
    List<Transaction> fetchSuspiciousTransactions(User user);
}
