package com.example.demo.service.impl;

import com.example.demo.model.DuplicateDetectionLog;
import com.example.demo.repository.DuplicateDetectionLogRepository;
import com.example.demo.service.DuplicateDetectionService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final DuplicateDetectionLogRepository repo;

    public DuplicateDetectionServiceImpl(DuplicateDetectionLogRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<DuplicateDetectionLog> detectDuplicates(Long ticketId) {

        return List.of();
    }

    @Override
    public List<DuplicateDetectionLog> getLogsForTicket(Long ticketId) {
        return repo.findByTicket_Id(ticketId);
    }

    @Override
    public DuplicateDetectionLog getLog(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}
