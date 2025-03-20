package org.benedict.massage.Utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserUtility {

    /**
     * Compare passwords hash
     * @param password
     * @param storedPassword
     * @return
     */
    public static boolean verifyPassword(String password, String storedPassword) {
        if (password == null || storedPassword == null) {
            throw new IllegalArgumentException("Password inputs cannot be null");
        }
        String passwordHash = hashPassword(password);
        return MessageDigest.isEqual(passwordHash.getBytes(), storedPassword.getBytes());
    }

    /**
     * Compare two password
     * @param password1 first password to compare
     * @param password2 second password to compare
     * @return
     */
    public static boolean doPasswordsMatch(String password1, String password2){
        return password1 !=null && password1.equals(password2);
    }

    /**
     * Hashes provided password
     * @param password in plain text format
     * @return hashed password
     */

    public static String hashPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password input cannot be null");
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Password hashing error: " + e.getMessage(), e);
        }
    }



    /**
     * Converts a byte array into hex
     *
     * @param bytes array
     * @return the string representation
     */

    private static String bytesToHex(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte b: bytes){
            sb.append(String.format("%02x", b));
        }

        return  sb.toString();
    }
}
