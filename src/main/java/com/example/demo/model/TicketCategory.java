package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_categories")
public class TicketCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;     // category name
    private String description;

    public TicketCategory() {}

    public TicketCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {        // <-- important
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { 
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // If service expects getCategoryName(), we add support too
    public String getCategoryName() {
        return name;
    }
}
