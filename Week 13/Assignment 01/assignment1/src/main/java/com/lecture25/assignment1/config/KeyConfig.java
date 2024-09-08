package com.lecture25.assignment1.config;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import jakarta.annotation.PostConstruct;

@Configuration
public class KeyConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(KeyConfig.class);

    @Value("classpath:app.pub")
    private Resource publicKeyResource;

    @Value("classpath:app.key")
    private Resource privateKeyResource;

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    @PostConstruct
    public void init() {
        try {
            this.publicKey = loadPublicKey();
            this.privateKey = loadPrivateKey();
        } catch(Exception e) {
            logger.error("Failed to load RSA keys", e);
            throw new RuntimeException("Failed to load RSA keys", e);
        }
    }

    public RSAPublicKey publicKey() {
        return this.publicKey;
    }

    public RSAPrivateKey privateKey() {
        return this.privateKey;
    }

    public RSAPublicKey loadPublicKey() throws Exception {
        byte[] publicKeyBytes = publicKeyResource.getInputStream().readAllBytes();
        String publicKeyContent = new String(publicKeyBytes).replaceAll("-----BEGIN PUBLIC KEY-----", "")
                                                          .replaceAll("-----END PUBLIC KEY-----", "")
                                                          .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(publicKeyContent);
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
    }

    public RSAPrivateKey loadPrivateKey() throws Exception {
        byte[] privateKeyBytes = privateKeyResource.getInputStream().readAllBytes();
        String privateKeyContent = new String(privateKeyBytes).replaceAll("-----BEGIN PRIVATE KEY-----", "")
                                                           .replaceAll("-----END PRIVATE KEY-----", "")
                                                           .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(privateKeyContent);
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
    }
}
