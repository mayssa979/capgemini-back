package com.example.stage.repository;

import com.example.stage.entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
    @Query(value = "SELECT * FROM candidat c WHERE  c.cin = :cin ", nativeQuery=true)
    Candidat findByCin(@Param("cin")String cin);

}
