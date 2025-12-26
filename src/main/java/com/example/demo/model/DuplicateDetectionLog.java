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

    private double matchScore;

    private LocalDateTime detectedAt = LocalDateTime.now();

    public DuplicateDetectionLog() {}

    public DuplicateDetectionLog(Ticket t1, Ticket t2, double score) {
        this.ticket1 = t1;
        this.ticket2 = t2;
        this.matchScore = score;
        this.detectedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Ticket getTicket1() {
        return ticket1;
    }

    public Ticket getTicket2() {
        return ticket2;
    }

    public double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(double matchScore) {
        this.matchScore = matchScore;
    }

    public LocalDateTime getDetectedAt() {
        return detectedAt;
    }
}
