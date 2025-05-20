package service.impl;

import model.Transaction;
import model.User;
import service.TransactionService;

import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    @Override
    public void recordTransaction(Transaction transaction) {
        transaction.getUser().getTransactions().add(transaction);
    }

    @Override
    public List<Transaction> fetchSuspiciousTransactions(User user) {
        List<Transaction> suspicious = new ArrayList<>();

        for (Transaction transaction : user.getTransactions()) {
            if (transaction.getAmount() > 5000) {
                suspicious.add(transaction);
            }
        }

        return suspicious;
    }
}