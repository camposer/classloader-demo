package com.example.classloader.demo;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JasyptTest {
    private BasicTextEncryptor textEncryptor;

    @BeforeEach
    public void setUp() {
        textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray("cuti".toCharArray());
    }

    @Test
    public void hackJasyptBasicEncryptor_shouldUseAppClassLoader() {
        ClassLoader actualClassLoader = textEncryptor.getClass().getClassLoader();
        assertTrue(actualClassLoader.toString().contains("AppClassLoader"));
    }

    @Test
    public void hackJasyptBasicEncryptor_encrypt() {
        String expectedEncryptedText = "cuti-h/cuti-e/cuti-l/cuti-l/cuti-o";

        String actualEncryptedText = textEncryptor.encrypt("hello");
        assertEquals(expectedEncryptedText, actualEncryptedText);
    }

    @Test
    public void hackJasyptBasicEncryptor_decrypt() {
        String expectedPlainText = "hello";

        String actualPlainText = textEncryptor.decrypt("cuti-h/cuti-e/cuti-l/cuti-l/cuti-o");
        assertEquals(expectedPlainText, actualPlainText);
    }
}
