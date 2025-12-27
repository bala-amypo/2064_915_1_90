package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DuplicateDetectionService;
import com.example.demo.util.TextSimilarityUtil;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DuplicateDetectionServiceImpl implements DuplicateDetectionService {

    private final TicketRepository ticketRepo;
    private final DuplicateRuleRepository ruleRepo;
    private final DuplicateDetectionLogRepository logRepo;

    public DuplicateDetectionServiceImpl(TicketRepository t, DuplicateRuleRepository r, DuplicateDetectionLogRepository l){
        this.ticketRepo=t; this.ruleRepo=r; this.logRepo=l;
    }

    public List<DuplicateDetectionLog> getLogsForTicket(Long id){
        return logRepo.findByTicket_Id(id);
    }

    public DuplicateDetectionLog getLog(Long id){
        return logRepo.findById(id).orElse(null);
    }

    public List<DuplicateDetectionLog> detectDuplicates(Long ticketId){
        Ticket base = ticketRepo.findById(ticketId).orElse(null);
        if(base==null) return List.of();

        List<DuplicateRule> rules = ruleRepo.findAll();
        if(rules.isEmpty()) return List.of();

        List<Ticket> open = ticketRepo.findByStatus("OPEN");
        List<DuplicateDetectionLog> result = new ArrayList<>();

        for(Ticket t : open){
            if(t.getId()!=null && t.getId().equals(ticketId)) continue;

            for(DuplicateRule r : rules){
                double score = 0.0;

                switch(r.getMatchType()){
                    case "EXACT_MATCH" ->
                            score = base.getSubject()!=null && t.getSubject()!=null &&
                                    base.getSubject().equalsIgnoreCase(t.getSubject()) ? 1.0 : 0.0;

                    case "KEYWORD" ->
                            score = TextSimilarityUtil.similarity(
                                    base.getSubject()+" "+base.getDescription(),
                                    t.getSubject()+" "+t.getDescription()
                            );

                    case "SIMILARITY" ->
                            score = TextSimilarityUtil.similarity(
                                    base.getDescription(),t.getDescription()
                            );
                }

                if(score >= r.getThreshold()){
                    DuplicateDetectionLog log = new DuplicateDetectionLog(base,t,score);
                    logRepo.save(log);
                    result.add(log);
                }
            }
        }

        return result;
    }
}
