package service;

import model.User;

public interface PaymentService {
    boolean processPayment(User user, double amount);
}
