package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DuplicateDetectionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ticket ticket1;

    @ManyToOne
    private Ticket ticket2;

    private double similarityScore;

    private LocalDateTime createdAt = LocalDateTime.now();

    // REQUIRED BY TESTS
    public DuplicateDetectionLog() {
    }

    // ALSO REQUIRED BY TESTS
    public DuplicateDetectionLog(Ticket ticket1, Ticket ticket2, double similarityScore) {
        this.ticket1 = ticket1;
        this.ticket2 = ticket2;
        this.similarityScore = similarityScore;
    }

    public Long getId() { return id; }

    public Ticket getTicket1() { return ticket1; }
    public void setTicket1(Ticket ticket1) { this.ticket1 = ticket1; }

    public Ticket getTicket2() { return ticket2; }
    public void setTicket2(Ticket ticket2) { this.ticket2 = ticket2; }

    public double getSimilarityScore() { return similarityScore; }
    public void setSimilarityScore(double similarityScore) { this.similarityScore = similarityScore; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
