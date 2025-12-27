package com.example.demo.repository;

import com.example.demo.model.TicketCategory;
import java.util.Optional;

public interface TicketCategoryRepository {
    boolean existsByCategoryName(String categoryName);
    TicketCategory save(TicketCategory category);
    Optional<TicketCategory> findById(Long id);
}