package com.example.stage.controller;
import com.example.stage.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailControl {
    private final EmailService emailService;

    @Autowired
    public EmailControl(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public String sendEmail() {
        emailService.sendEmail("stages.tn@gmail.com", "Test Subject", "Hello, this is a test email!");
        return "Email sent!";
    }
    @GetMapping("/fetch-latest-email-body")
    public ResponseEntity<String> fetchLatestEmailBody() {
        String emailBody = emailService.fetchLatestEmailBody();
        if (emailBody != null) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity<>(emailBody, responseHeaders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No email found or error occurred.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Hello, this is a test endpoint!";
    }
}
