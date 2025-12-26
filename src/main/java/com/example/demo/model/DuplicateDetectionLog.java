package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "duplicate_detection_logs")
public class DuplicateDetectionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ticket for which duplicate was checked
    private Long ticketId;

    // Score of match
    private double matchScore;

    // When detection happened
    private LocalDateTime detectedAt;

    public DuplicateDetectionLog() {
    }

    public DuplicateDetectionLog(Long ticketId, double matchScore, LocalDateTime detectedAt) {
        this.ticketId = ticketId;
        this.matchScore = matchScore;
        this.detectedAt = detectedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
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

    public void setDetectedAt(LocalDateTime detectedAt) {
        this.detectedAt = detectedAt;
    }
}
