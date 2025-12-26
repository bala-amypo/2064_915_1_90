package com.example.demo.repository;

import com.example.demo.model.DuplicateDetectionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuplicateDetectionLogRepository extends JpaRepository<DuplicateDetectionLog, Long> {

    // FIXED — now matches entity field "ticketId"
    List<DuplicateDetectionLog> findByTicketId(long ticketId);
}
