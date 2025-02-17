package com.boghdady.encryptdemo.controllers;

import com.boghdady.encryptdemo.services.RsaEncryptDecryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rsa")
public class RsaController {

    private final RsaEncryptDecryptService rsaService;

    @Autowired
    public RsaController(RsaEncryptDecryptService rsaService) {
        this.rsaService = rsaService;
    }

    @PostMapping("/encrypt")
    public String encrypt(@RequestBody String message) {
        return rsaService.encrypt(message);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedMessage) {
        return rsaService.decrypt(encryptedMessage);
    }
}
