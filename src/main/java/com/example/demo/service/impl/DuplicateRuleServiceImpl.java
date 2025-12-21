package com.example.demo.service.impl;

import com.example.demo.model.DuplicateRule;
import com.example.demo.repository.DuplicateRuleRepository;
import com.example.demo.service.DuplicateRuleService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DuplicateRuleServiceImpl implements DuplicateRuleService {

    private final DuplicateRuleRepository repo;

    public DuplicateRuleServiceImpl(DuplicateRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public DuplicateRule createRule(DuplicateRule rule) {

        if (repo.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new IllegalArgumentException("rule already exists");
        }

        Double t = rule.getThreshold();
        if (t == null || t < 0.0 || t > 1.0) {
            throw new IllegalArgumentException("threshold must be between 0.0 and 1.0");
        }

        return repo.save(rule);
    }

    @Override
    public List<DuplicateRule> getAllRules() {
        return repo.findAll();
    }

    @Override
    public DuplicateRule getRule(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }
}
