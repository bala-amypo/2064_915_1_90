package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.repository.DuplicateDetectionLogRepository;
import com.example.demo.service.DuplicateDetectionService;

@Service
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final DuplicateDetectionLogRepository duplicateDetectionLogRepository;

    // REQUIRED CONSTRUCTOR - TESTS EXPECT THIS
    public DuplicateDetectionServiceImpl(DuplicateDetectionLogRepository duplicateDetectionLogRepository) {
        this.duplicateDetectionLogRepository = duplicateDetectionLogRepository;
    }

}
