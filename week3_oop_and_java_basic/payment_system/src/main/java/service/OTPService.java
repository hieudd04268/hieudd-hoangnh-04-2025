package service;

public interface OTPService {
    String generateOTP(String username);
    boolean verifyOTP(String username, String otp);
}
