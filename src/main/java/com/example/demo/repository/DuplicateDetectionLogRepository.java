package com.example.demo.repository;

import com.example.demo.model.DuplicateDetectionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DuplicateDetectionLogRepository extends JpaRepository<DuplicateDetectionLog, Long> {

    /**
     * Used by our application
     */
    List<DuplicateDetectionLog> findByTicketId(Long ticketId);


    /**
     * Required ONLY FOR TEST CASES
     * They expect method name findByTicket_Id(long)
     * Our entity does NOT have "ticket", so we map it manually
     */
    @Query("SELECT d FROM DuplicateDetectionLog d WHERE d.ticketId = :id")
    List<DuplicateDetectionLog> findByTicket_Id(@Param("id") long id);
}
