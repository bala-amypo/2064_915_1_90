package com.example.demo.controller;

import com.example.demo.model.DuplicateRule;
import com.example.demo.service.DuplicateRuleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class DuplicateRuleController {

    private final DuplicateRuleService service;

    public DuplicateRuleController(DuplicateRuleService service){
        this.service = service;
    }

    @PostMapping
    public DuplicateRule create(@RequestBody DuplicateRule rule){
        return service.createRule(rule);
    }

    @GetMapping
    public List<DuplicateRule> getAll(){
        return service.getAllRules();
    }

    @GetMapping("/{id}")
    public DuplicateRule get(@PathVariable Long id){
        return service.getRule(id);
    }
}
