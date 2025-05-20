package service;

import model.Transaction;

public interface RefundService {
    boolean processRefund(Transaction transaction, String reason);
}
