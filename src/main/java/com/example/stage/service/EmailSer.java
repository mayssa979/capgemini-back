package com.example.stage.service;

import com.example.stage.entity.Email;
import com.example.stage.entity.FileDb;
import com.example.stage.repository.EmailsRepository;
import com.example.stage.repository.FileDbRepository;
//import com.example.pfe.security.services.UserDetailsImpl;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EmailSer {

    private final EmailsRepository emailsRepository ;
    private final FileDbRepository fileRepository ;

    public EmailSer(EmailsRepository emailsRepository, FileDbRepository fileRepository) {
        this.emailsRepository = emailsRepository;
        this.fileRepository = fileRepository;
    }

    public List<Email> getAllMail(){

        return emailsRepository.getAllEmails();
    }

    public Email addMail(Email mail){
      //  for (FileDb file : mail.getAttachements()) {
        //    fileRepository.save(file);
      //  }
        return emailsRepository.save(mail) ;

    }

    public Optional<Email> getEmail(Long id) {
        return emailsRepository.findById(id);
    }


    public void makeRead(long id){

        //System.out.println(loggedinuser.getId() + "&&& "+"&&&" + loggedinuser.getUsername());
         emailsRepository.makeRead(id);
    }
    public void moveToTrash(long id) {
       // UserDetailsImpl loggedinuser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        emailsRepository.moveToTrash(id);
    }


    public int getCountUnreadEmails(){

        return emailsRepository.getCountUnreadEmails();
    }




    public List<Email> getTrashedEmails() {
        return emailsRepository.getTrashedEmails();
    }


    public int countEmails() {
        return emailsRepository.findAll().size();
    }
}
