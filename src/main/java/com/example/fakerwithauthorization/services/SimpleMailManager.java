package com.example.fakerwithauthorization.services;

import com.example.fakerwithauthorization.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleMailManager{
    Logger logger = LoggerFactory.getLogger(SimpleMailManager.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(User user, String password) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vaniagatman@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Credentials JsonFakerAuth");
        message.setText(
                "Hi " + user.getUsername()
                + "\n Thanks for registration. \n"
                + "Here are the credentials for login \n"
                + "username: " + user.getUsername()
                + "\n password: " + password
                + "\n e-mail: " + user.getEmail()
                + "\n Role: [role_id, roleName]: " + user.getRoles()
        );
        mailSender.send(message);

        logger.debug("Mail message sent: " + message);
    }
}
