package service.impl;

import service.OTPService;

import java.util.HashMap;
import java.util.Map;

public class OTPServiceImpl implements OTPService {
    private Map<String, String> otpStorage = new HashMap<>(); // OTP storage
    private Map<String, Integer> failedAttempts = new HashMap<>(); // Track failed attempts

    @Override
    public String generateOTP(String username) {
        String otp = String.valueOf((int) (Math.random() * 9000) + 1000); // Generate 4-digit OTP
        otpStorage.put(username, otp);
        return otp;
    }

    @Override
    public boolean verifyOTP(String username, String otp) {
        String generatedOTP = otpStorage.get(username);
        if (generatedOTP == null) {
            return false;
        }

        if (!generatedOTP.equals(otp)) {
            failedAttempts.put(username, failedAttempts.getOrDefault(username, 0) + 1);
            if (failedAttempts.get(username) >= 3) {
                return false;
            }
            return false;
        }

        otpStorage.remove(username); // Clear the OTP after successful verification
        failedAttempts.remove(username); // Reset failed attempts
        return true;
    }
}