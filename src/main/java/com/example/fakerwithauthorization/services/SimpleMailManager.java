package com.example.fakerwithauthorization.services;

import com.example.fakerwithauthorization.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SimpleMailManager {
    Logger logger = LoggerFactory.getLogger(SimpleMailManager.class);

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void sendCredentials(User user) {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(user.getEmail());
        msg.setText(
                "Dear" + user.getUsername()
                + ", thank you for registration. Your ROLE is "
                + user.getRoles()
                + "\n Your password" + user.getPassword()
                + "please, take care."
        );

        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            logger.error(ex.getMessage());
        }
    }
}
