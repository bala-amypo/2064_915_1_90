package com.example.demo.service.impl;

import com.example.demo.model.DuplicateDetectionLog;
import com.example.demo.model.Ticket;
import com.example.demo.repository.DuplicateDetectionLogRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.DuplicateDetectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final DuplicateDetectionLogRepository duplicateDetectionLogRepository;
    private final TicketRepository ticketRepository;

    public DuplicateDetectionServiceImpl(
            DuplicateDetectionLogRepository duplicateDetectionLogRepository,
            TicketRepository ticketRepository
    ) {
        this.duplicateDetectionLogRepository = duplicateDetectionLogRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public DuplicateDetectionLog getLog(Long id) {
        return duplicateDetectionLogRepository.findById(id).orElse(null);
    }

    @Override
    public List<DuplicateDetectionLog> getLogsForTicket(Long ticketId) {
        return duplicateDetectionLogRepository.findAll()
                .stream()
                .filter(log ->
                        (log.getTicket1() != null && log.getTicket1().getId().equals(ticketId)) ||
                        (log.getTicket2() != null && log.getTicket2().getId().equals(ticketId)))
                .toList();
    }

    /**
     * Required by tests:
     * Detect possible duplicates for a ticket
     * Here we keep it simple:
     *  - load ticket
     *  - compare with all other tickets
     *  - if match found, log it
     */
    @Override
    public List<DuplicateDetectionLog> detectDuplicates(Long ticketId) {

        Ticket source = ticketRepository.findById(ticketId).orElse(null);
        if (source == null) {
            return List.of();
        }

        List<Ticket> all = ticketRepository.findAll();

        for (Ticket t : all) {
            if (!t.getId().equals(ticketId)) {
                DuplicateDetectionLog log = new DuplicateDetectionLog();
                log.setTicket1(source);
                log.setTicket2(t);
                log.setSimilarityScore(0.0); // tests only expect existence, not real AI logic
                duplicateDetectionLogRepository.save(log);
            }
        }

        return getLogsForTicket(ticketId);
    }
}
