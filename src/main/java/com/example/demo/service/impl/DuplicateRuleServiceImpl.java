package com.example.demo.service.impl;

import com.example.demo.model.DuplicateRule;
import com.example.demo.repository.DuplicateRuleRepository;
import com.example.demo.service.DuplicateRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuplicateRuleServiceImpl implements DuplicateRuleService {

    private final DuplicateRuleRepository ruleRepository;

    public DuplicateRuleServiceImpl(DuplicateRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public DuplicateRule createRule(DuplicateRule rule) {

        // Check duplicate name
        ruleRepository.findByRuleName(rule.getRuleName()).ifPresent(r -> {
            throw new RuntimeException("Rule already exists");
        });

        // Threshold validation required by tests
        double threshold = rule.getThreshold();
        if (threshold < 0.0 || threshold > 1.0) {
            throw new RuntimeException("Threshold must be between 0 and 1");
        }

        // Default matchType safe-guard
        if (rule.getMatchType() == null || rule.getMatchType().isBlank()) {
            rule.setMatchType("SIMILARITY");
        }

        return ruleRepository.save(rule);
    }

    @Override
    public DuplicateRule getRule(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    @Override
    public List<DuplicateRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public void deleteRule(Long id) {
        if (!ruleRepository.existsById(id)) {
            throw new RuntimeException("Rule not found");
        }
        ruleRepository.deleteById(id);
    }
}
