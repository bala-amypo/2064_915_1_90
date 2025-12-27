package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface TicketRepository extends JpaRepository<Ticket,Long>{
    List<Ticket> findByStatus(String status);
    List<Ticket> findByCategory_Id(long id);
    List<Ticket> findByUser_Id(Long id);
    List<Ticket> findBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String s,String d);
}
