package com.example.demo.service.impl;

import com.example.demo.model.DuplicateDetectionLog;
import com.example.demo.model.DuplicateRule;
import com.example.demo.model.Ticket;
import com.example.demo.repository.DuplicateDetectionLogRepository;
import com.example.demo.repository.DuplicateRuleRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.DuplicateDetectionService;
import com.example.demo.util.TextSimilarityUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final TicketRepository ticketRepository;
    private final DuplicateRuleRepository ruleRepository;
    private final DuplicateDetectionLogRepository logRepository;

    // ⚠️ MUST MATCH TEST CONSTRUCTOR SIGNATURE
    public DuplicateDetectionServiceImpl(
            TicketRepository ticketRepository,
            DuplicateRuleRepository ruleRepository,
            DuplicateDetectionLogRepository logRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.ruleRepository = ruleRepository;
        this.logRepository = logRepository;
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

        Ticket base = ticketRepository.findById(ticketId).orElse(null);
        if (base == null) return List.of();

        List<DuplicateRule> rules = ruleRepository.findAll();
        if (rules.isEmpty()) return List.of();

        List<Ticket> openTickets = ticketRepository.findByStatus("OPEN");

        List<DuplicateDetectionLog> results = new ArrayList<>();

        for (Ticket other : openTickets) {

            // skip itself
            if (other.getId() != null && other.getId().equals(ticketId)) continue;

            for (DuplicateRule rule : rules) {

                double score = 0.0;

                String type = rule.getMatchType();
                double threshold = rule.getThreshold();

                String baseText = (base.getSubject() + " " + base.getDescription()).toLowerCase();
                String otherText = (other.getSubject() + " " + other.getDescription()).toLowerCase();

                switch (type) {

                    case "EXACT_MATCH":
                        score = base.getSubject() != null &&
                                other.getSubject() != null &&
                                base.getSubject().equalsIgnoreCase(other.getSubject())
                                ? 1.0 : 0.0;
                        break;

                    case "KEYWORD":
                        score = TextSimilarityUtil.similarity(baseText, otherText);
                        break;

                    case "SIMILARITY":
                        score = TextSimilarityUtil.similarity(baseText, otherText);
                        break;
                }

                if (score >= threshold) {

                    DuplicateDetectionLog log =
                            new DuplicateDetectionLog(base, other, score);

                    log.setDetectedAt(LocalDateTime.now());

                    logRepository.save(log);
                    results.add(log);
                }
            }
        }

        return results;
    }
}
