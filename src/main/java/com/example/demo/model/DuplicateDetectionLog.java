package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "duplicate_detection_logs")
public class DuplicateDetectionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne(optional = false)
    @JoinColumn(name = "duplicate_ticket_id")
    private Ticket duplicateTicket;

    private double similarityScore;

    private LocalDateTime createdAt = LocalDateTime.now();

    public DuplicateDetectionLog() {}

    public DuplicateDetectionLog(Ticket ticket, Ticket duplicateTicket, double similarityScore) {
        this.ticket = ticket;
        this.duplicateTicket = duplicateTicket;
        this.similarityScore = similarityScore;
    }

    public Long getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Ticket getDuplicateTicket() {
        return duplicateTicket;
    }

    public double getSimilarityScore() {
        return similarityScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setDuplicateTicket(Ticket duplicateTicket) {
        this.duplicateTicket = duplicateTicket;
    }

    public void setSimilarityScore(double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
