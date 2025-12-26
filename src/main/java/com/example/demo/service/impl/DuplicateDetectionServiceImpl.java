package com.example.demo.service.impl;

import com.example.demo.model.DuplicateDetectionLog;
import com.example.demo.repository.DuplicateDetectionLogRepository;
import com.example.demo.service.DuplicateDetectionService;
import org.springframework.stereotype.Service;

@Service
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final DuplicateDetectionLogRepository duplicateDetectionLogRepository;

    public DuplicateDetectionServiceImpl(DuplicateDetectionLogRepository duplicateDetectionLogRepository) {
        this.duplicateDetectionLogRepository = duplicateDetectionLogRepository;
    }

    @Override
    public DuplicateDetectionLog getLog(Long id) {
        return duplicateDetectionLogRepository.findById(id).orElse(null);
    }
}
