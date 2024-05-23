package com.example.stage.repository;

import com.example.stage.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmailsRepository extends JpaRepository<Email , Long> {

    @Query(value = "SELECT count(*) FROM email e WHERE e.is_read = 0 ", nativeQuery=true)
    int getCountUnreadEmails();

  /*  @Query(value = "SELECT * FROM email e WHERE e.senderid = :id AND e.trash= 0 ORDER BY `time` DESC", nativeQuery=true)
    List<Email> getSentEmails(@Param("id")long id);

    @Query(value = "SELECT * FROM email e WHERE  e.receiverid = :id AND e.labels = 'important' AND e.trash= 0 ORDER BY `time` DESC", nativeQuery=true)
    List<Email> getImportantEmails(@Param("id")long id);

    @Query(value = "SELECT * FROM email e WHERE  e.receiverid = :id AND e.labels = 'opinion' AND e.trash= 0 ORDER BY `time` DESC", nativeQuery=true)
    List<Email> getOpinionEmails(@Param("id")long id);


    @Query(value = "SELECT * FROM email e WHERE  e.receiverid = :id AND e.labels = 'private' AND e.trash= 0 ORDER BY `time` DESC", nativeQuery=true)
    List<Email> getPrivateEmails(@Param("id")long id);*/

    @Query(value = "SELECT * FROM email e WHERE  e.trash = 0 ORDER BY `time` DESC", nativeQuery=true)
    List<Email> getAllEmails();

    @Query(value = "SELECT * FROM email e WHERE  e.trash= 1 ORDER BY `time` DESC", nativeQuery=true)
    List<Email> getTrashedEmails();


 /*   @Query(value = " SELECT * FROM email e WHERE " +
                        "(e.id = :currentemailreplyid OR e.replytoid != 0) AND" +
                        " ( e.replytoid = :currentemailreplyid OR  e.replytoid = :currentemailid OR e.replytoid = 0 )" +
                                " AND e.trash = 0  ORDER BY `time` ", nativeQuery=true)
//    @Query(value = " select * from email where replytoid = :currentemailreplyid or id = :currentemailreplyid and replytoid = 0 " +
//            "or  replytoid = :currentemailid   ", nativeQuery=true)
    List<Email> getFullConver( @Param("currentemailreplyid")long currentemailreplyid, @Param("currentemailid") long currentemailid);

    @Query(value = " SELECT COUNT(id) FROM email e WHERE e.is_read = 0 AND e.receiverid = :currentemailid AND e.trash= 0 ", nativeQuery=true)
    long getCountUnreadEmails(@Param("currentemailid")long currentemailid );*/

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE `email` SET `is_read`= 1 WHERE `id` = :currentemailid", nativeQuery=true)
    void makeRead(@Param("currentemailid") long currentemailid);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE `email` SET `trash`= 1 WHERE `id` = :currentemailid ", nativeQuery=true)
    void moveToTrash(@Param("currentemailid") long currentemailid);
}
