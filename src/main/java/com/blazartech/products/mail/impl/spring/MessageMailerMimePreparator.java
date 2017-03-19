/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.mail.impl.spring;

import com.blazartech.products.mail.MailMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * Prepare a MIME message from a mail message.
 * 
 * @author scott
 * @version $Id: MessageMailerMimePreparator.java 12 2016-10-30 21:10:39Z scott $
 */
public class MessageMailerMimePreparator implements MimeMessagePreparator {

    private final MailMessage message;

    public MessageMailerMimePreparator(MailMessage message) {
        this.message = message;
    }
    
    private Address[] buildAddressList(Collection<String> addresses) throws Exception {
        List<Address> recipientList = new ArrayList<>();
        for (String recipient : addresses) {
            recipientList.add(new InternetAddress(recipient));
        }
        Address[] addressArray = new Address[recipientList.size()];
        recipientList.toArray(addressArray);
        return addressArray;
    }
    
    @Override
    public void prepare(MimeMessage mm) throws Exception {
        // add the recipients
        mm.setRecipients(Message.RecipientType.TO, buildAddressList(message.getRecipients()));
        
        // add the CCs
        mm.setRecipients(Message.RecipientType.CC, buildAddressList(message.getCopyRecipients()));
        
        // subject
        mm.setSubject(message.getSubject());
        
        // set content, assuming HTML
        mm.setContent(message.getText(), "text/html");
        
    }
    
}
