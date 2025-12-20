package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DuplicateDetectionLog;
import com.example.demo.service.DuplicateDetectionService;

@RestController
@RequestMapping("/api/detection")
public class DuplicateDetectionController {

    private final DuplicateDetectionService detectionService;

    public DuplicateDetectionController(DuplicateDetectionService detectionService) {
        this.detectionService = detectionService;
    }

    @GetMapping("/run/{ticketId}")
    public List<DuplicateDetectionLog> runDetection(@PathVariable Long ticketId) {
        return detectionService.detectDuplicates(ticketId);
    }

    @GetMapping("/ticket/{ticketId}")
    public List<DuplicateDetectionLog> getLogsForTicket(@PathVariable Long ticketId) {
        return detectionService.getLogsForTicket(ticketId);
    }

    @GetMapping("/{id}")
    public DuplicateDetectionLog getLog(@PathVariable Long id) {
        return detectionService.getLog(id);
    }
}
