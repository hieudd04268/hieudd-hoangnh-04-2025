package service;

import model.User;

public interface UserService {
    User login(String username, String password);
    void lockUserAccount(String username);
    boolean doesUserExist(String username);
    boolean checkPassword(String username, String password);
}
