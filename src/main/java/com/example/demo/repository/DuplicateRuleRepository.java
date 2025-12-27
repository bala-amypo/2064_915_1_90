package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface DuplicateRuleRepository extends JpaRepository<DuplicateRule,Long>{
    Optional<DuplicateRule> findByRuleName(String name);
}
