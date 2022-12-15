package com.example.fakerwithauthorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class SpringMail {
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender =  new JavaMailSenderImpl();
        mailSender.setHost("smtp.google.com");
        return mailSender;
    }

}
