package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.demo.model.*;
public interface DuplicateRuleRepository extends JpaRepository<DuplicateRule, Long> {
    Optional<DuplicateRule> findByRuleName(String name);
}
