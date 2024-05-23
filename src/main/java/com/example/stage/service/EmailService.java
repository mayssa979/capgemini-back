package com.example.stage.service;
import javax.mail.*;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            // Handle exception (e.g., logging or throwing custom exception)
        }
    }

    public String fetchLatestEmailBody() {
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "stages.tn@gmail.com", "dpbmdprwnrzjvixn");

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            if (messages.length > 0) {
                Message latestMessage = messages[messages.length - 1];
                Object messageContent = latestMessage.getContent();
                if (messageContent instanceof Multipart) {
                    Multipart multipart = (Multipart) messageContent;
                    StringBuilder emailBody = new StringBuilder();
                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);
                        String contentType = bodyPart.getContentType();
                        if (contentType.contains("text/plain") || contentType.contains("text/html")) {
                            emailBody.append(bodyPart.getContent().toString());
                        }
                    }
                    return emailBody.toString();
                } else if (messageContent instanceof String) {
                    return messageContent.toString();
                }
            }
          //  inbox.close(false);
         //   store.close();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return "Error occurred while fetching the email body.";
        }
        return null;
    }



}

