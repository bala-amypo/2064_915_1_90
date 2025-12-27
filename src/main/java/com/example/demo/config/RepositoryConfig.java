package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository() {
        return new MockUserRepository();
    }

    @Bean
    public TicketRepository ticketRepository() {
        return new MockTicketRepository();
    }

    @Bean
    public TicketCategoryRepository ticketCategoryRepository() {
        return new MockTicketCategoryRepository();
    }

    @Bean
    public DuplicateRuleRepository duplicateRuleRepository() {
        return new MockDuplicateRuleRepository();
    }

    @Bean
    public DuplicateDetectionLogRepository duplicateDetectionLogRepository() {
        return new MockDuplicateDetectionLogRepository();
    }

    @Repository
    static class MockUserRepository implements UserRepository {
        private final Map<Long, User> users = new ConcurrentHashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);

        public boolean existsByEmail(String email) { return users.values().stream().anyMatch(u -> email.equals(u.getEmail())); }
        public User save(User user) { if (user.getId() == null) user.setId(idGenerator.getAndIncrement()); users.put(user.getId(), user); return user; }
        public Optional<User> findById(Long id) { return Optional.ofNullable(users.get(id)); }
        public Optional<User> findByEmail(String email) { return users.values().stream().filter(u -> email.equals(u.getEmail())).findFirst(); }
        public List<User> findAll() { return new ArrayList<>(users.values()); }
    }

    @Repository
    static class MockTicketRepository implements TicketRepository {
        private final Map<Long, Ticket> tickets = new ConcurrentHashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);

        public Ticket save(Ticket ticket) { if (ticket.getId() == null) ticket.setId(idGenerator.getAndIncrement()); tickets.put(ticket.getId(), ticket); return ticket; }
        public Optional<Ticket> findById(Long id) { return Optional.ofNullable(tickets.get(id)); }
        public List<Ticket> findByStatus(String status) { return tickets.values().stream().filter(t -> status.equals(t.getStatus())).toList(); }
        public List<Ticket> findByCategory_Id(Long categoryId) { return tickets.values().stream().filter(t -> t.getCategory() != null && categoryId.equals(t.getCategory().getId())).toList(); }
        public List<Ticket> findByUser_Id(Long userId) { return tickets.values().stream().filter(t -> t.getUser() != null && userId.equals(t.getUser().getId())).toList(); }
        public List<Ticket> findBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String subject, String description) { return new ArrayList<>(); }
    }

    @Repository
    static class MockTicketCategoryRepository implements TicketCategoryRepository {
        private final Map<Long, TicketCategory> categories = new ConcurrentHashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);

        public boolean existsByCategoryName(String categoryName) { return categories.values().stream().anyMatch(c -> categoryName.equals(c.getCategoryName())); }
        public TicketCategory save(TicketCategory category) { if (category.getId() == null) category.setId(idGenerator.getAndIncrement()); categories.put(category.getId(), category); return category; }
        public Optional<TicketCategory> findById(Long id) { return Optional.ofNullable(categories.get(id)); }
    }

    @Repository
    static class MockDuplicateRuleRepository implements DuplicateRuleRepository {
        private final Map<Long, DuplicateRule> rules = new ConcurrentHashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);

        public DuplicateRule save(DuplicateRule rule) { if (rule.getId() == null) rule.setId(idGenerator.getAndIncrement()); rules.put(rule.getId(), rule); return rule; }
        public Optional<DuplicateRule> findById(Long id) { return Optional.ofNullable(rules.get(id)); }
        public Optional<DuplicateRule> findByRuleName(String ruleName) { return rules.values().stream().filter(r -> ruleName.equals(r.getRuleName())).findFirst(); }
        public List<DuplicateRule> findAll() { return new ArrayList<>(rules.values()); }
    }

    @Repository
    static class MockDuplicateDetectionLogRepository implements DuplicateDetectionLogRepository {
        private final Map<Long, DuplicateDetectionLog> logs = new ConcurrentHashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);

        public DuplicateDetectionLog save(DuplicateDetectionLog log) { if (log.getId() == null) log.setId(idGenerator.getAndIncrement()); logs.put(log.getId(), log); return log; }
        public List<DuplicateDetectionLog> findByTicket_Id(Long ticketId) { return logs.values().stream().filter(l -> l.getTicket() != null && ticketId.equals(l.getTicket().getId())).toList(); }
    }
}