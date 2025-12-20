package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.DuplicateRule;
import com.example.demo.repository.DuplicateRuleRepository;
import com.example.demo.service.DuplicateRuleService;

@Service
public class DuplicateRuleServiceImpl implements DuplicateRuleService {

    private final DuplicateRuleRepository ruleRepository;

    public DuplicateRuleServiceImpl(DuplicateRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public DuplicateRule createRule(DuplicateRule rule) {

        if (ruleRepository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new IllegalArgumentException("Rule already exists");
        }

        if (rule.getThreshold() < 0.0 || rule.getThreshold() > 1.0) {
            throw new IllegalArgumentException("Invalid threshold");
        }

        return ruleRepository.save(rule);
    }

    @Override
    public List<DuplicateRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public DuplicateRule getRule(Long id) throws NotFoundException {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }
}
