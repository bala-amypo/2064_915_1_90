package com.example.demo.repository;

import com.example.demo.model.Ticket;
import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(Long id);
    List<Ticket> findByStatus(String status);
    List<Ticket> findByCategory_Id(Long categoryId);
    List<Ticket> findByUser_Id(Long userId);
    List<Ticket> findBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String subject, String description);
}