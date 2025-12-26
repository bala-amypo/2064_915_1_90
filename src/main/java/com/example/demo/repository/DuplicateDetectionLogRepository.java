package com.example.demo.repository;

import com.example.demo.model.DuplicateDetectionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DuplicateDetectionLogRepository extends JpaRepository<DuplicateDetectionLog, Long> {

    List<DuplicateDetectionLog> findByTicket1_Id(Long ticketId);
}
