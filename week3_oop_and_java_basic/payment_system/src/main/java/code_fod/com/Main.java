package code_fod.com;

import constance.UserStatus;
import model.*;
import service.UserService;
import service.TransactionService;
import service.impl.*;

import java.util.Scanner;
import java.util.UUID;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static PaymentServiceImpl paymentService = new PaymentServiceImpl();
    static TransactionServiceImpl transactionService = new TransactionServiceImpl();
    static FraudDetectionServiceImpl fraudDetectionService = new FraudDetectionServiceImpl();
    static UserService userService = new UserServiceImpl();
    static User currentUser = null;

    public static void main(String[] args) {
        loginMenu();

        // Main menu
        while (true) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Make a Payment");
            System.out.println("2. Refunded Payment");
            System.out.println("3. Financial Report");
            System.out.println("4. Account Settings");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    makePayment();
                    break;
                case 2:

                    break;
                case 3:
                    viewTransactionHistory();
                    break;
                case 4:
                    accountSettingsMenu();
                    break;
                case 5:
                    currentUser = null;
                    loginMenu();
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // === LOGIN MENU ===
    private static void loginMenu() {
        System.out.println("===== Welcome to Payment System =====");
        while (currentUser == null) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            if (!userService.doesUserExist(username)) {
                System.out.println("User does not exist. Please try again.");
                continue;
            }
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            currentUser = userService.login(username, password);
            while (!userService.checkPassword(username, password)) {
                System.out.println("Invalid password. Please try again(account will be locked if 3 attempts failed).");
                System.out.print("Enter password: ");
                password = scanner.nextLine();
                currentUser = userService.login(username, password);
            }
            if (currentUser == null) {
                System.out.println("Account is locked. Try again.");
            }
        }
        System.out.println("Login successful! Welcome, " + currentUser.getUsername());
    }

    public static void accountSettingsMenu() {
        System.out.println("\n===== Account Settings =====");
        System.out.println("1. Change Password");
        System.out.println("2. Add Payment Method");
        System.out.println("3. Delete Payment Methods");
        System.out.println("4. View Account Information");
        System.out.println("5. Logout");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                changePassword();
                break;
            case 2:
                addPaymentMethod();
                break;
            case 3:
                deletePaymentMethod();
                break;
            case 4:
                viewAccountInformation();
                break;
            case 5:
                System.out.println("Logging out...");
                currentUser = null;
                loginMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void changePassword() {

        System.out.println("\n===== Change Password =====");
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();
        while(!currentUser.getPassword().equals(currentPassword)) {
            System.out.println("Current password is incorrect. Please try again.");
            System.out.print("Enter current password: ");
            currentPassword = scanner.nextLine();
        }
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();
        while(!newPassword.equals(confirmPassword)) {
            System.out.println("New password does not match. Please try again.");
            System.out.print("Enter confirm new password: ");
            confirmPassword = scanner.nextLine();
        }
        currentUser.setPassword(newPassword);
        System.out.println("Password changed successfully!");
    }

    // === ADD PAYMENT METHOD ===
    private static void addPaymentMethod() {
        System.out.println("\n===== Add Payment Method =====");
        System.out.println("1. Credit Card");
        System.out.println("2. E-Wallet");
        System.out.println("3. Bank Transfer");
        System.out.print("Choose a payment method type to add: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1: // Credit Card
                System.out.print("Enter initial balance: ");
                double ccBalance = scanner.nextDouble();
                System.out.print("Enter credit limit: ");
                double ccLimit = scanner.nextDouble();
                System.out.print("Enter interest rate: ");
                double ccInterest = scanner.nextDouble();
                currentUser.getPaymentMethods().add(new CreditCard(ccBalance, ccLimit, ccInterest));
                System.out.println("Credit Card added successfully!");
                break;
            case 2: // E-Wallet
                System.out.print("Enter initial balance: ");
                double ewBalance = scanner.nextDouble();
                currentUser.getPaymentMethods().add(new EWallet(ewBalance));
                System.out.println("E-Wallet added successfully!");
                break;
            case 3: // Bank Transfer
                System.out.print("Enter initial balance: ");
                double btBalance = scanner.nextDouble();
                currentUser.getPaymentMethods().add(new BankTransfer(btBalance));
                System.out.println("Bank Transfer added successfully!");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    // === DELETE A PAYMENT ===
    private static void deletePaymentMethod() {
        System.out.println("\n===== Delete Payment Method =====");
        if(currentUser.getPaymentMethods().isEmpty()) {
            System.out.println("Payment methods list is empty. Please try again.");
        } else {
            for (PaymentMethod paymentMethod : currentUser.getPaymentMethods()) {
                System.out.println(paymentMethod);
            }
        }
    }

    private static void viewAccountInformation() {
        System.out.println("\n===== View Account Information =====");

    }

    // === MAKE A PAYMENT ===
    private static void makePayment() {
        System.out.println("\n===== Make a Payment =====");

        if (currentUser.getPaymentMethods().isEmpty()) {
            System.out.println("Add a payment method first.");
            return;
        }

        System.out.print("Enter the amount to pay: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (paymentService.processPayment(currentUser, amount)) {
            Transaction transaction = new Transaction(
                    UUID.randomUUID().toString(),
                    currentUser,
                    currentUser.getPaymentMethods().get(0), // Assume the first payment method is used
                    amount,
                    "SUCCESS"
            );
            transactionService.recordTransaction(transaction);
            System.out.println("Payment processed successfully!");
        } else {
            System.out.println("Payment failed. Check your balance.");
        }
    }

    // === VIEW TRANSACTION HISTORY ===
    private static void viewTransactionHistory() {
        System.out.println("\n===== Transaction History =====");
        if (currentUser.getTransactions().isEmpty()) {
            System.out.println("No transaction history.");
        } else {
            for (Transaction transaction : currentUser.getTransactions()) {
                System.out.println("ID: " + transaction.getTransactionId() +
                        ", Amount: " + transaction.getAmount() +
                        ", Status: " + transaction.getStatus() +
                        ", Date: " + transaction.getTimestamp());
            }
        }
    }
}