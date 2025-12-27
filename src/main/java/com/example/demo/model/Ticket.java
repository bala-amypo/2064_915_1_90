package com.example.demo.model;

import java.time.LocalDateTime;

public class Ticket {
    private Long id;
    private String subject;
    private String description;
    private String status;
    private User user;
    private TicketCategory category;
    private LocalDateTime createdAt;

    public Ticket() {
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public TicketCategory getCategory() { return category; }
    public void setCategory(TicketCategory category) { this.category = category; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}