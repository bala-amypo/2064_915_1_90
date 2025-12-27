package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DuplicateDetectionService;
import com.example.demo.util.TextSimilarityUtil;
import java.util.*;

public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {
    private final TicketRepository ticketRepository;
    private final DuplicateRuleRepository ruleRepository;
    private final DuplicateDetectionLogRepository logRepository;

    public DuplicateDetectionServiceImpl(TicketRepository ticketRepository, DuplicateRuleRepository ruleRepository, DuplicateDetectionLogRepository logRepository) {
        this.ticketRepository = ticketRepository;
        this.ruleRepository = ruleRepository;
        this.logRepository = logRepository;
    }

    @Override
    public List<Object> detectDuplicates(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));
        List<DuplicateRule> rules = ruleRepository.findAll();
        List<Ticket> openTickets = ticketRepository.findByStatus("OPEN");
        List<Object> duplicates = new ArrayList<>();

        if (rules.isEmpty()) return duplicates;

        for (Ticket otherTicket : openTickets) {
            if (Objects.equals(otherTicket.getId(), ticketId)) continue;

            for (DuplicateRule rule : rules) {
                boolean isDuplicate = false;
                double score = 0.0;

                switch (rule.getMatchType()) {
                    case "EXACT_MATCH":
                        if (ticket.getSubject() != null && otherTicket.getSubject() != null) {
                            isDuplicate = ticket.getSubject().equalsIgnoreCase(otherTicket.getSubject());
                            score = isDuplicate ? 1.0 : 0.0;
                        }
                        break;
                    case "KEYWORD":
                        String text1 = (ticket.getSubject() + " " + ticket.getDescription()).toLowerCase();
                        String text2 = (otherTicket.getSubject() + " " + otherTicket.getDescription()).toLowerCase();
                        score = TextSimilarityUtil.similarity(text1, text2);
                        isDuplicate = score >= rule.getThreshold();
                        break;
                    case "SIMILARITY":
                        String desc1 = ticket.getDescription() != null ? ticket.getDescription() : "";
                        String desc2 = otherTicket.getDescription() != null ? otherTicket.getDescription() : "";
                        score = TextSimilarityUtil.similarity(desc1, desc2);
                        isDuplicate = score >= rule.getThreshold();
                        break;
                }

                if (isDuplicate) {
                    duplicates.add(otherTicket);
                    DuplicateDetectionLog log = new DuplicateDetectionLog(ticket, otherTicket, score);
                    logRepository.save(log);
                    break;
                }
            }
        }

        return duplicates;
    }

    @Override
    public List<DuplicateDetectionLog> getLogsForTicket(Long ticketId) {
        return logRepository.findByTicket_Id(ticketId);
    }
}