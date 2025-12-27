package com.example.demo.repository;

import com.example.demo.model.DuplicateDetectionLog;
import java.util.List;

public interface DuplicateDetectionLogRepository {
    DuplicateDetectionLog save(DuplicateDetectionLog log);
    List<DuplicateDetectionLog> findByTicket_Id(Long ticketId);
}