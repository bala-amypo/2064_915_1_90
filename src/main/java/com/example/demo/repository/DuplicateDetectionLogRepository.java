package com.example.demo.repository;

import com.example.demo.model.DuplicateDetectionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DuplicateDetectionLogRepository extends JpaRepository<DuplicateDetectionLog, Long> {

    @Query("SELECT d FROM DuplicateDetectionLog d WHERE d.ticket1.id = :id OR d.ticket2.id = :id")
    List<DuplicateDetectionLog> findByTicket_Id(@Param("id") long id);
}
