package org.jasypt.util.text;

/**
 * Overrides the original implementation with a Caesar Cipher Algorithm implementation
 *
 * Original class:
 * https://github.com/jasypt/jasypt/blob/master/jasypt/src/main/java/org/jasypt/util/text/BasicTextEncryptor.java
 *
 * Caesar algorithm:
 * https://en.wikipedia.org/wiki/Caesar_cipher
 */
public final class BasicTextEncryptor implements TextEncryptor {
    private String password;

    public void setPassword(final String password) {
        this.password = password;
    }


    public void setPasswordCharArray(final char[] password) {
        this.password = new String(password);
    }

    @Override
    public String encrypt(final String message) {
        int shift = password.length();
        StringBuffer result= new StringBuffer();
        for (int i = 0; i < message.length(); i++) {
            if (Character.isUpperCase(message.charAt(i))) {
                int ch = ((int)message.charAt(i) + shift - 65) % 26 + 65;
                result.append((char)ch);
            } else {
                int ch = ((int)message.charAt(i) + shift - 97) % 26 + 97;
                result.append((char)ch);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(final String encryptedMessage) {
        int shift = password.length();
        StringBuffer result= new StringBuffer();
        for (int i = 0; i < encryptedMessage.length(); i++) {
            if (Character.isUpperCase(encryptedMessage.charAt(i))) {
                int ch = ((int)encryptedMessage.charAt(i) + (26 - shift) - 65) % 26 + 65;
                result.append((char)ch);
            } else {
                int ch = ((int)encryptedMessage.charAt(i) + (26 - shift) - 97) % 26 + 97;
                result.append((char)ch);
            }
        }
        return result.toString();
    }
}