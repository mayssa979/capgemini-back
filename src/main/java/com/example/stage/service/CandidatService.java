package com.example.stage.service;

import com.example.stage.entity.Candidat;
import com.example.stage.entity.FileDb;
import com.example.stage.repository.CandidatRepository;
import com.example.stage.repository.FileDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CandidatService {
    @Autowired
    private CandidatRepository repository;
    @Autowired
    private FileDbRepository fileDbRepository;
    public Candidat saveCandidat(Candidat candidat){
        //fileDbRepository.save(fileDb);
        return repository.save(candidat);
    }

    public List<Candidat> saveCandidats(List<Candidat> candidats) {
        return repository.saveAll(candidats);
    }

    public List<Candidat> getCandidats() {
        return repository.findAll();
    }

    public Candidat getCandidatById(int id) {
        return repository.findById(id).orElse(null);
    }
    public Candidat getCandidatByCin(String cin) {
        return repository.findByCin(cin);
    }

    public Candidat deleteCandidat(int id) {
        repository.deleteById(id);
        return repository.findById(id).orElse(null);
    }

    public Candidat updateCandidat(Candidat candidat) {
        Candidat exsistingCandidat = repository.findById(candidat.getId()).orElse(null);
        exsistingCandidat.setCin(candidat.getCin());
        exsistingCandidat.setEmail(candidat.getEmail());
        exsistingCandidat.setFirstname(candidat.getFirstname());
        exsistingCandidat.setLastname(candidat.getLastname());
        exsistingCandidat.setDob(candidat.getDob());
        exsistingCandidat.setGender(candidat.getGender());
        exsistingCandidat.setEducation(candidat.getEducation());
        exsistingCandidat.setSpeciality(candidat.getSpeciality());
        exsistingCandidat.setInternshipType(candidat.getInternshipType());
        return repository.save(exsistingCandidat);
    }

}
