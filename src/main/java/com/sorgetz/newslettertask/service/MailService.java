package com.sorgetz.newslettertask.service;

import com.sorgetz.newslettertask.properties.MailProperties;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Log4j2
public class MailService {

    @Autowired
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @Autowired
    private MailProperties mailProperties;

    public String sendMail(String to, String subject, String body)
    {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", Boolean.TRUE);
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", Boolean.TRUE);
        properties.put("mail.smtp.starttls.required", Boolean.TRUE);
        properties.put("mail.smtp.ssl.enable", Boolean.FALSE);
        properties.put("mail.test-connection", Boolean.TRUE);
        properties.put("mail.debug", Boolean.TRUE);

        mailSender.setJavaMailProperties(properties);

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, Boolean.FALSE, "UTF-8");
            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, Boolean.TRUE);
            mailSender.send(mimeMessage);
        } catch (Exception ex) {
            log.warn("Email could not be sent to user '{}': {}", to, ex.getMessage());
        }

        return "aaaaaaa";
    }

}

