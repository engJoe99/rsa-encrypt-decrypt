package com.boghdady.encryptdemo.services;


import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * Service class for RSA encryption and decryption operations
 */
@Service
public class RsaEncryptDecryptService {

    // Public key used for encryption, exposed via getter
    @Getter
    private PublicKey publicKey;

    // Private key used for decryption
    private PrivateKey privateKey;



    /**
     * Initializes the RSA key pair on service startup
     * Generates a new 2048-bit RSA key pair and stores the public/private keys
     */
    @PostConstruct
    public void generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // Use 2048-bit for security
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        } catch (Exception e) {
            throw new RuntimeException("Error generating RSA key pair", e);
        }
    }



    /**
     * Encrypts a plain text message using RSA public key
     * @param message The plain text message to encrypt
     * @return Base64 encoded encrypted string
     */
    public String encrypt(String message) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting message", e);
        }
    }

    /**
     * Decrypts an encrypted message using RSA private key
     * @param encryptedMessage Base64 encoded encrypted message
     * @return Decrypted plain text message
     */
    public String decrypt(String encryptedMessage) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting message", e);
        }
    }

}
