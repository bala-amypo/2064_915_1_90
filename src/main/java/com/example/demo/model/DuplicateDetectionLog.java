package com.example.demo.model;

import java.time.LocalDateTime;

public class DuplicateDetectionLog {
    private Long id;
    private Ticket ticket;
    private Ticket duplicateTicket;
    private double matchScore;
    private LocalDateTime detectedAt;

    public DuplicateDetectionLog() {
        this.detectedAt = LocalDateTime.now();
    }

    public DuplicateDetectionLog(Ticket ticket, Ticket duplicateTicket, double matchScore) {
        this();
        this.ticket = ticket;
        this.duplicateTicket = duplicateTicket;
        this.matchScore = matchScore;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
    public Ticket getDuplicateTicket() { return duplicateTicket; }
    public void setDuplicateTicket(Ticket duplicateTicket) { this.duplicateTicket = duplicateTicket; }
    public double getMatchScore() { return matchScore; }
    public void setMatchScore(double matchScore) { this.matchScore = matchScore; }
    public LocalDateTime getDetectedAt() { return detectedAt; }
    public void setDetectedAt(LocalDateTime detectedAt) { this.detectedAt = detectedAt; }
}