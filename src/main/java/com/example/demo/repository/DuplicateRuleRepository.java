package com.example.demo.repository;

import com.example.demo.model.DuplicateRule;
import java.util.List;
import java.util.Optional;

public interface DuplicateRuleRepository {
    DuplicateRule save(DuplicateRule rule);
    Optional<DuplicateRule> findById(Long id);
    Optional<DuplicateRule> findByRuleName(String ruleName);
    List<DuplicateRule> findAll();
}