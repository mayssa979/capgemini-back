package com.example.stage.controller;


import com.example.stage.entity.Email;
import com.example.stage.entity.FileDb;
import com.example.stage.service.EmailSer;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



/** FILEs Imports  **/

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

/** FILEs Imports  **/



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailSer emailService ;

    public EmailController(EmailSer emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/")
    public ResponseEntity<?> gettest(){
       List<Email> mails =  emailService.getAllMail();
       return new ResponseEntity<>(mails, HttpStatus.OK);
    };

   /* @GetMapping("/inbox")
    public ResponseEntity<?> getReceivdeEmails(@Param("userID") long userID){
       List<Email> mails =  emailService.getReceivdeEmails(userID);


       return new ResponseEntity<>(mails, HttpStatus.OK);
    };

    @GetMapping("/sent")
    public ResponseEntity<?> getSentEmails(@Param("userID") long userID){
       List<Email> mails =  emailService.getSentEmails(userID);


       return new ResponseEntity<>(mails, HttpStatus.OK);
    };

    @GetMapping("/starred")
    public ResponseEntity<?> getStarredEmails(@Param("userID") long userID){
       List<Email> mails =  emailService.getStarredEmails(userID);


       return new ResponseEntity<>(mails, HttpStatus.OK);
    };
*/
    @GetMapping("/trash")
    public ResponseEntity<?> getTrashedEmails(){
       List<Email> mails =  emailService.getTrashedEmails();


       return new ResponseEntity<>(mails, HttpStatus.OK);
    };

 /*   @GetMapping("/opinion")
    public ResponseEntity<?> getOpinionEmails(@Param("userID") long userID){
       List<Email> mails =  emailService.getOpinionEmails(userID);

       return new ResponseEntity<>(mails, HttpStatus.OK);
    };


    @GetMapping("/private")
    public ResponseEntity<?> getPrivateEmails(@Param("userID") long userID){
       List<Email> mails =  emailService.getPrivateEmails(userID);

       return new ResponseEntity<>(mails, HttpStatus.OK);
    };

    @GetMapping("/important")
    public ResponseEntity<?> getImportantEmails(@Param("userID") long userID){
       List<Email> mails =  emailService.getImportantEmails(userID);


       return new ResponseEntity<>(mails, HttpStatus.OK);
    };

    @GetMapping("/getfullconver")
    public ResponseEntity<?> getFullConver(@Param("currentemailid") long currentemailid){


        //System.out.println("\n email --- " + currentemailid);

        Email mail =  emailService.getEmail(currentemailid).get();

        //System.out.println(mail);

       List<Email> mails =  emailService.getFullConver(mail);

       // System.out.println("getFullConver ----- \n " + mails);

       return new ResponseEntity<>(mails, HttpStatus.OK);
    };*/



    @GetMapping("/countunread")
    public ResponseEntity<?> getCountUnreadEmails(){
        int CountUnreadEmails =  emailService.getCountUnreadEmails();

        return new ResponseEntity<>(CountUnreadEmails, HttpStatus.OK);
    };

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmail(@PathVariable Long id){

       Optional<Email> mails =  emailService.getEmail(id);
       return new ResponseEntity<>(mails, HttpStatus.OK);
    };

    @PostMapping("/sendmail")
    public ResponseEntity<?> emails(@RequestBody Email email){

        System.out.println("sending mail \n" + email);
         Email mail =  emailService.addMail(email);

        //return  null ;
       return new ResponseEntity<>(mail, HttpStatus.OK);
    };


    @PutMapping("/makeread/{id}")
   public ResponseEntity<?> makeRead(@PathVariable Long id){

        //System.out.println("\n \n controller " );

         emailService.makeRead(id);

       return new ResponseEntity<>( HttpStatus.OK);
    };




    @PutMapping("/movetotrash/{id}")
    public ResponseEntity<?> moveToTrah(@PathVariable Long id){


        emailService.moveToTrash(id);

       return new ResponseEntity<>(HttpStatus.OK);
    };



    @GetMapping("/countemails")
    public ResponseEntity<?> countEmails(){


        int emailnbr =  emailService.countEmails();

        return new ResponseEntity<>( emailnbr , HttpStatus.OK);
    };




    // define a location
   /* public static final String DIRECTORY = "src/main/Uploads/EmailUploads";

    // Define a method to upload files
    @PostMapping("file/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
        }
        return ResponseEntity.ok().body(filenames);
    }

    // Define a method to download files
    @GetMapping("file/download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }*/

}
    /**        FILEs      **/
