package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.DuplicateDetectionLog;
import com.example.demo.model.DuplicateRule;
import com.example.demo.model.Ticket;
import com.example.demo.repository.DuplicateDetectionLogRepository;
import com.example.demo.repository.DuplicateRuleRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.DuplicateDetectionService;
import com.example.demo.util.TextSimilarityUtil;

@Service
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final TicketRepository ticketRepository;
    private final DuplicateRuleRepository ruleRepository;
    private final DuplicateDetectionLogRepository logRepository;

    public DuplicateDetectionServiceImpl(
            TicketRepository ticketRepository,
            DuplicateRuleRepository ruleRepository,
            DuplicateDetectionLogRepository logRepository) {

        this.ticketRepository = ticketRepository;
        this.ruleRepository = ruleRepository;
        this.logRepository = logRepository;
    }

    @Override
    public List<DuplicateDetectionLog> detectDuplicates(Long ticketId) {

        Ticket baseTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));

        List<DuplicateRule> rules = ruleRepository.findAll();
        List<Ticket> openTickets = ticketRepository.findByStatus("OPEN");

        List<DuplicateDetectionLog> logs = new ArrayList<>();

        for (DuplicateRule rule : rules) {
            for (Ticket candidate : openTickets) {

                if (candidate.getId().equals(baseTicket.getId())) {
                    continue;
                }

                double score = 0.0;

                if ("EXACT_MATCH".equalsIgnoreCase(rule.getMatchType())) {
                    if (baseTicket.getSubject()
                            .equalsIgnoreCase(candidate.getSubject())) {
                        score = 1.0;
                    }
                }

                if ("SIMILARITY".equalsIgnoreCase(rule.getMatchType())) {
                    score = TextSimilarityUtil.similarity(
                            baseTicket.getDescription(),
                            candidate.getDescription());
                }

                if (score >= rule.getThreshold()) {
                    DuplicateDetectionLog log =
                            new DuplicateDetectionLog(baseTicket, candidate, score);
                    logs.add(logRepository.save(log));
                }
            }
        }

        return logs;
    }

    @Override
    public List<DuplicateDetectionLog> getLogsForTicket(Long ticketId) {
        return logRepository.findByTicket_Id(ticketId);
    }

    @Override
    public DuplicateDetectionLog getLog(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Detection log not found"));
    }
}
