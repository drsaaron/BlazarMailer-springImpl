/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.mail.impl.spring;

import com.blazartech.products.mail.MailMessage;
import com.blazartech.products.mail.MessageMailer;
import javax.mail.MessagingException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Implement the message mailer component using spring's JavaMail wrapper.
 * 
 * @author scott
 * @version $Id: MessageMailerSpringImpl.java 12 2016-10-30 21:10:39Z scott $
 */
@Service
public class MessageMailerSpringImpl implements MessageMailer {

    private static final Logger logger = Logger.getLogger(MessageMailerSpringImpl.class);
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Override
    public void sendMessage(MailMessage message) throws MessagingException {
        logger.info("sending message.");
        
        // prepare the message
        MessageMailerMimePreparator prep = new MessageMailerMimePreparator(message);
        
        // send
        mailSender.send(prep);
    }
    
}
