package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String complaintId;
    private String studentId;
    private String description;
    private String status;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Ticket() {}

    // getters & setters
}
