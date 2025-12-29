package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "duplicate_rules")
public class DuplicateRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleName;

    @Column(nullable = false)
    private Double threshold;

    // NEW FIELD
    @Column(nullable = false)
    private String matchType;   // Example: EXACT, PARTIAL

    public DuplicateRule() {}

    public DuplicateRule(String ruleName, Double threshold, String matchType) {
        this.ruleName = ruleName;
        this.threshold = threshold;
        this.matchType = matchType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }
}
