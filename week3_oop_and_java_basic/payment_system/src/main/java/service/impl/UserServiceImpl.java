package service.impl;

import model.User;
import constance.UserStatus;
import service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private Map<String, User> userDatabase; // Emulating a simple user database

    public UserServiceImpl() {
        this.userDatabase = new HashMap<>();

        // Add some mock users
        User user1 = new User("user1", UserStatus.ACTIVE, "123", false);
        userDatabase.put(user1.getUsername(), user1);
        User user2 = new User("user2", UserStatus.ACTIVE, "123", false);
        userDatabase.put(user2.getUsername(), user2);
        User user3 = new User("user3", UserStatus.ACTIVE, "123", false);
        userDatabase.put(user3.getUsername(), user3);
    }

    @Override
    public User login(String username, String password) {
        User user = userDatabase.get(username);

        if (user == null || user.isAccountLocked() || user.getStatus() != UserStatus.ACTIVE) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            user.setFailedAttemptsLogin(0); // Reset failed attempts if login is successful
            return user;
        }

        user.setFailedAttemptsLogin(user.getFailedAttemptsLogin() + 1);
        if (user.getFailedAttemptsLogin() >= 3) {
            lockUserAccount(username);
        }
        return null;
    }

    @Override
    public void lockUserAccount(String username) {
        User user = userDatabase.get(username);
        if (user != null) {
            user.setAccountLocked(true);
            user.setStatus(UserStatus.SUSPENDED);
        }
    }

    @Override
    public boolean doesUserExist(String username) {
        return userDatabase.containsKey(username);
    }

    @Override
    public boolean checkPassword(String username, String password) {
        return userDatabase.get(username).getPassword().equals(password);
    }
}