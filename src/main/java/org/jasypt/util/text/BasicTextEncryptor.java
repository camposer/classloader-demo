package org.jasypt.util.text;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Overrides original implementation available here:
 * https://github.com/jasypt/jasypt/blob/master/jasypt/src/main/java/org/jasypt/util/text/BasicTextEncryptor.java
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
        return message.chars()
                .mapToObj(ch -> String.format("%s-%c", password, ch))
                .collect(Collectors.joining("/"));
    }

    @Override
    public String decrypt(final String encryptedMessage) {
        return Arrays.stream(encryptedMessage.split("/"))
                .map(token -> token.replaceFirst(String.format("%s-", password), ""))
                .collect(Collectors.joining());
    }
}