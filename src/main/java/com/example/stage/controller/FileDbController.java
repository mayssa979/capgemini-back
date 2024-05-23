package com.example.stage.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.stage.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.stage.entity.FileDb;
import com.example.stage.entity.FileResponse;
import com.example.stage.service.FileDbService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/file")
public class FileDbController {

    @Autowired
    private FileDbService fileDbService;
    @Autowired
    private CandidatService candidatService;
   /* @PostMapping
    public FileResponse uploadFile(@RequestParam("file") MultipartFile  file,@RequestParam("cin") String cin) throws IOException {
        return fileDbService.store(file,cin);
    }*/
    @PostMapping
    public FileResponse uploadFile(@RequestParam("file") MultipartFile  file) throws IOException {
        return fileDbService.store(file);
    }
    /*@GetMapping("/{id}")
    public FileDb getFile(@PathVariable String id) {

        return fileDbService.getFileById(id);

    }*/
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable int id) {
        FileDb fileDB = fileDbService.getFileById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
    @GetMapping("/get/{id}")
    public FileDb getFileDb(@PathVariable int id) {

        return fileDbService.getFileById(id);

    }
    @GetMapping("/list")
    public List<FileResponse> getFileList(){
        return fileDbService.getFileList();
    }

  //  @GetMapping("/getfile/{id}")
    //public FileDb getone(String id){
      //  return this.fileDbService.getOne(id);
   // }

}