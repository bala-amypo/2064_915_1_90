package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "duplicate_detection_logs")
public class DuplicateDetectionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    private Ticket matchedTicket;

    private Double matchScore;
    private LocalDateTime detectedAt;

    public DuplicateDetectionLog() {}

    public DuplicateDetectionLog(Ticket ticket, Ticket matchedTicket, Double matchScore) {
        this.ticket = ticket;
        this.matchedTicket = matchedTicket;
        this.matchScore = matchScore;
    }

    @PrePersist
    public void onCreate() {
        detectedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Ticket getTicket() { return ticket; }
    public Ticket getMatchedTicket() { return matchedTicket; }
    public Double getMatchScore() { return matchScore; }
}
