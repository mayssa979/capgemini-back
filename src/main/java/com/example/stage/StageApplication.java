package com.example.stage;

import com.example.stage.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class StageApplication {
	//@Autowired
	//private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(StageApplication.class, args);
	}
	/*@EventListener(ApplicationReadyEvent.class)
	public void sendMail(){
		senderService.sendEmail("mayssa.bensalah@etudiant-isi.utm.tn","demande de stage", "ci dessous mon cv");
	}*/

}
