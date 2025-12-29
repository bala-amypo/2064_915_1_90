package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String status = "OPEN";

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // ---------------- USER RELATION ----------------
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ---------------- CATEGORY RELATION ----------------
    @ManyToOne
    @JoinColumn(name = "category_id")
    private TicketCategory category;

    // ------------ CONSTRUCTORS ------------
    public Ticket() {}

    public Ticket(String subject, String description) {
        this.subject = subject;
        this.description = description;
    }

    // ------------ GETTERS & SETTERS ------------

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // ---- USER ----
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    // ---- CATEGORY ----
    public TicketCategory getCategory() {
        return category;
    }
    public void setCategory(TicketCategory category) {
        this.category = category;
    }
}
