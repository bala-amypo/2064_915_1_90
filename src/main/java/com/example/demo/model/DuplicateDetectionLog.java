package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DuplicateDetectionLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private Ticket ticket;
    @ManyToOne private Ticket duplicateTicket;

    private double matchScore;
    private LocalDateTime detectedAt = LocalDateTime.now();

    public DuplicateDetectionLog(){}
    public DuplicateDetectionLog(Ticket t1, Ticket t2,double score){
        this.ticket=t1;
        this.duplicateTicket=t2;
        this.matchScore=score;
    }

    public Long getId(){ return id; }
    public Ticket getTicket(){ return ticket; }
    public void setTicket(Ticket t){ this.ticket=t; }

    public Ticket getDuplicateTicket(){ return duplicateTicket; }
    public void setDuplicateTicket(Ticket t){ this.duplicateTicket=t; }

    public double getMatchScore(){ return matchScore; }
    public void setMatchScore(double matchScore){ this.matchScore=matchScore; }

    public LocalDateTime getDetectedAt(){ return detectedAt; }
}
