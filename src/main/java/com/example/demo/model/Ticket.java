package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String description;
    private String status = "OPEN";

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private User user;

    @ManyToOne
    private TicketCategory category;

    // Getters Setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getSubject(){ return subject; }
    public void setSubject(String subject){ this.subject=subject; }

    public String getDescription(){ return description; }
    public void setDescription(String description){ this.description=description; }

    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status=status; }

    public LocalDateTime getCreatedAt(){ return createdAt; }

    public User getUser(){ return user; }
    public void setUser(User user){ this.user=user; }

    public TicketCategory getCategory(){ return category; }
    public void setCategory(TicketCategory category){ this.category=category; }
}
