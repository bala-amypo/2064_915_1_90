package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.*;
public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Long> {
    boolean existsByCategoryName(String name);
}
