package com.example.demo.repository;

import com.example.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUser_Id(Long userId);
  

    // REQUIRED BY TESTS
    List<Ticket> findByCategory_Id(long categoryId);

    // REQUIRED BY TESTS
    List<Ticket> findBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String subject,
            String description
    );
}
