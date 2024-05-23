package com.example.stage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
   public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("stages.tn@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("mail sent successfully !");
    }
    public void sendSuccess(String toEmail,String subject){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("stages.tn@gmail.com");
        message.setTo(toEmail);
        message.setText("congrats! your candidature is registred ! ");
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("mail sent successfully !");
    }
    public void sendFailure(String toEmail,String subject){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("stages.tn@gmail.com");
        message.setTo(toEmail);
        message.setText("your candidature was rejected please resend the form with all the field required and the cv must be a PDF!");
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("mail sent successfully !");
    }
}
