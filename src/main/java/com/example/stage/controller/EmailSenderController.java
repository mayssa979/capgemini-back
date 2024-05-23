package com.example.stage.controller;
import com.example.stage.service.EmailSenderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/send")
public class EmailSenderController {
    private final EmailSenderService emailSender ;

    public EmailSenderController(EmailSenderService emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("/mail")
	public void sendMail(String toEmail, String subject, String body){
       /* toEmail="mayssa.bensalah@etudiant-isi.utm.tn";
        subject="demande de stage d'été";*/
       // body="bonjour,je suis Mayssa ben salah ci dessous mon cv ";
        emailSender.sendEmail(toEmail,subject,body );
	}
    @PostMapping("/success")
    public void sendSuccess(String toEmail, String subject){
       // toEmail="mayssa.bensalah@etudiant-isi.utm.tn";
        //subject="demande de stage d'été";
        // body="bonjour,je suis Mayssa ben salah ci dessous mon cv ";
        emailSender.sendSuccess(toEmail, subject);
    }
    @PostMapping("/failure")
    public void sendFailure(String toEmail, String subject){
        // toEmail="mayssa.bensalah@etudiant-isi.utm.tn";
        //subject="demande de stage d'été";
        // body="bonjour,je suis Mayssa ben salah ci dessous mon cv ";
        emailSender.sendFailure(toEmail, subject);
    }
}
