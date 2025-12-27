package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface TicketCategoryRepository extends JpaRepository<TicketCategory,Long>{
    boolean existsByCategoryName(String name);
}
