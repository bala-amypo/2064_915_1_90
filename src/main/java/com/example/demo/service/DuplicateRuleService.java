package com.example.demo.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.demo.model.DuplicateRule;

public interface DuplicateRuleService {

    DuplicateRule createRule(DuplicateRule rule);

    List<DuplicateRule> getAllRules();

    DuplicateRule getRule(Long id) throws NotFoundException;
}
