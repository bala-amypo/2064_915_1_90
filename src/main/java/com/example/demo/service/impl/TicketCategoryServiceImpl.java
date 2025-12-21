package com.example.demo.service.impl;

import com.example.demo.model.TicketCategory;
import com.example.demo.repository.TicketCategoryRepository;
import com.example.demo.service.TicketCategoryService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository repo;

    public TicketCategoryServiceImpl(TicketCategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public TicketCategory createCategory(TicketCategory category) {

        if (repo.existsByCategoryName(category.getCategoryName())) {
            throw new IllegalArgumentException("category already exists");
        }

        return repo.save(category);
    }

    @Override
    public List<TicketCategory> getAllCategories() {
        return repo.findAll();
    }

    @Override
    public TicketCategory getCategory(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
}
