package com.example.demo.service;

import com.example.demo.model.DuplicateDetectionLog;
import java.util.List;

public interface DuplicateDetectionService {
    List<Object> detectDuplicates(Long ticketId);
    List<DuplicateDetectionLog> getLogsForTicket(Long ticketId);
}