package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DuplicateRule;
import com.example.demo.service.DuplicateRuleService;

@RestController
@RequestMapping("/api/rules")
public class DuplicateRuleController {

    private final DuplicateRuleService ruleService;

    public DuplicateRuleController(DuplicateRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public DuplicateRule createRule(@RequestBody DuplicateRule rule) {
        return ruleService.createRule(rule);
    }

    @GetMapping
    public List<DuplicateRule> getAllRules() {
        return ruleService.getAllRules();
    }

    @GetMapping("/{id}")
    public DuplicateRule getRule(@PathVariable Long id) throws Throwable {
        return ruleService.getRule(id);
    }
}
