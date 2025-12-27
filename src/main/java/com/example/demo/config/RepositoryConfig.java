package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class RepositoryConfig {

    @Bean
    @Primary          // << INSERT HERE
    public DuplicateRuleRepository duplicateRuleRepository() {
        return new InMemoryDuplicateRuleRepository();
    }

    @Bean
    public DuplicateRuleRepository MockDuplicateRuleRepository() {
        return new MockDuplicateRuleRepository();
    }

}
