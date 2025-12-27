package com.example.demo.config;

import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public TicketCategoryService ticketCategoryService(TicketCategoryRepository categoryRepository) {
        return new TicketCategoryServiceImpl(categoryRepository);
    }

    @Bean
    public TicketService ticketService(TicketRepository ticketRepository, UserRepository userRepository, TicketCategoryRepository categoryRepository) {
        return new TicketServiceImpl(ticketRepository, userRepository, categoryRepository);
    }

    @Bean
    public DuplicateRuleService duplicateRuleService(DuplicateRuleRepository ruleRepository) {
        return new DuplicateRuleServiceImpl(ruleRepository);
    }

    @Bean
    public DuplicateDetectionService duplicateDetectionService(TicketRepository ticketRepository, DuplicateRuleRepository ruleRepository, DuplicateDetectionLogRepository logRepository) {
        return new DuplicateDetectionServiceImpl(ticketRepository, ruleRepository, logRepository);
    }
}