package com.example.stage.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.stage.entity.Candidat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.stage.entity.FileDb;
import com.example.stage.entity.FileResponse;
import com.example.stage.repository.FileDbRepository;

@Service
public class FileDbService {

    @Autowired
    private FileDbRepository fileDbRepository;

    @Autowired
    private CandidatService candidatService;


   /* public FileResponse store(MultipartFile file, Candidat candidat) throws IOException {
        String fileName = file.getOriginalFilename();
       // Candidat candidat = candidatService.getCandidatByCin(cin);
        FileDb fileDb = new FileDb(UUID.randomUUID().toString(), fileName, file.getContentType(), file.getBytes(),candidat );
      //  fileDb.setCandidat(candidat);
        fileDbRepository.save(fileDb);
        return  mapToFileResponse(fileDb);
    }*/
   public FileResponse store(MultipartFile file) throws IOException {
       String fileName = file.getOriginalFilename();
       FileDb fileDb = new FileDb(fileName, file.getContentType(), file.getBytes());
       fileDbRepository.save(fileDb);
       return  mapToFileResponse(fileDb);
   }

    public FileDb getFileById(int id) {

        Optional<FileDb> fileOptional = fileDbRepository.findById(id);

        if(fileOptional.isPresent()) {
            return fileOptional.get();
        }
        return null;
    }

    public List<FileResponse> getFileList(){
        return fileDbRepository.findAll().stream().map(this::mapToFileResponse).collect(Collectors.toList());
    }

    private FileResponse mapToFileResponse(FileDb fileDb) {
        return new FileResponse(fileDb.getId(), fileDb.getType(), fileDb.getName());
    }

    /*public FileDb getOne(int id){
        return fileDbRepository.findById(id).orElse(null);
    }*/


}
