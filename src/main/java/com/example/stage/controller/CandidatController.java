package com.example.stage.controller;
import com.example.stage.dto.AuthRequest;
import com.example.stage.entity.UserInfo;
import com.example.stage.service.JwtService;
import com.example.stage.entity.Candidat;
import com.example.stage.entity.FileDb;
import com.example.stage.service.CandidatService;
import net.bytebuddy.asm.Advice;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/candidate")
@CrossOrigin(origins = "http://localhost:4200")
public class CandidatController {
    @Autowired
    private CandidatService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    private MultipartFile file;
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
      /*  Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }*/
        return jwtService.generateToken(authRequest.getUsername());

    }
@PostMapping(value="/addcandidat" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@PreAuthorize("hasAuthority('DMIN')")
    public Candidat addCandidat(  @RequestPart("candidat") Candidat candidat,
                                  @RequestPart("file") MultipartFile file) throws IOException{
    FileDb fileDb = new FileDb(file.getOriginalFilename(), file.getContentType(), file.getBytes());

    // Set the FileDb instance on the Candidat entity
    candidat.setFileDb(fileDb);

    return service.saveCandidat(candidat);
   // String fileName = file.getOriginalFilename();
    //FileDb fileDb = new FileDb(fileName, file.getContentType(), file.getBytes());

    }
    @PostMapping("/addCandidats")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public List<Candidat> addProducts(@RequestBody List<Candidat> candidats) {
        return service.saveCandidats(candidats);
    }


    @GetMapping("/candidats")
   // @PreAuthorize("hasAuthority('ADMIN')")
    public List<Candidat> findAllProducts() {
        return service.getCandidats();
    }
    @GetMapping("/getAllcandidats")
     @PreAuthorize("hasAuthority('ADMIN')")
    public List<Candidat> findAllCandidats() {
        return service.getCandidats();
    }
    @GetMapping("/candidatById/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public Candidat findProductById(@PathVariable int id) {
        return service.getCandidatById(id);
    }



    @PutMapping("/update")
   // @PreAuthorize("hasAuthority('ADMIN')")
    public Candidat updateCandidat(@RequestBody Candidat candidat) {
        return service.updateCandidat(candidat);
    }

    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public Candidat deleteCandidat(@PathVariable int id) {
        return service.deleteCandidat(id);
    }
}
