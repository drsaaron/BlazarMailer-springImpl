/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.mail.impl.spring.config;

import com.blazartech.products.crypto.BlazarCryptoFile;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author scott
 */
@Configuration
public class MailerConfiguration {
    
    @Autowired
    private BlazarCryptoFile cryptoFile;
    
    @Value("${blazar.mailer.userID}")
    private String userID;
    
    @Value("${blazar.mailer.resourceID}")
    private String resourceID;
    
    @Value("${blazar.mailer.host}")
    private String host;
    
    @Value("${blazar.mailer.port}")
    private int port;
    

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(userID);
        sender.setPassword(cryptoFile.getPassword(userID, resourceID));
        
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtp.auth", "true");
        mailProperties.setProperty("mail.smtp.starttls.enable", "true");
        sender.setJavaMailProperties(mailProperties);
        
        return sender;
    }
}
