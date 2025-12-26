package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.DuplicateDetectionLog;
import com.example.demo.model.Ticket;
import com.example.demo.repository.DuplicateDetectionLogRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.DuplicateDetectionService;

@Service
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final DuplicateDetectionLogRepository logRepository;
    private final TicketRepository ticketRepository;

    // ---- ONLY ONE CONSTRUCTOR ----
    @Autowired
    public DuplicateDetectionServiceImpl(
            DuplicateDetectionLogRepository logRepository,
            TicketRepository ticketRepository
    ) {
        this.logRepository = logRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<DuplicateDetectionLog> getLogsForTicket(Long ticketId) {
        return logRepository.findByTicket_Id(ticketId);
    }

    @Override
    public DuplicateDetectionLog getLog(Long id) {
        return logRepository.findById(id).orElse(null);
    }

    @Override
    public List<DuplicateDetectionLog> detectDuplicates(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket == null) return List.of();

        DuplicateDetectionLog log = new DuplicateDetectionLog();
        log.setTicketId(ticket.getId());
        log.setMatchScore(0.9);
        log.setDetectedAt(LocalDateTime.now());

        logRepository.save(log);

        return logRepository.findByTicket_Id(ticketId);
    }
}
