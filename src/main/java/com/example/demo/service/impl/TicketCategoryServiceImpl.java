package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.TicketCategory;
import com.example.demo.repository.TicketCategoryRepository;
import com.example.demo.service.TicketCategoryService;

@Service
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository categoryRepository;

    public TicketCategoryServiceImpl(TicketCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public TicketCategory createCategory(TicketCategory category) {

        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            throw new IllegalArgumentException("Category already exists");
        }

        if (category.getCreatedAt() == null) {
            category.setCreatedAt(LocalDateTime.now());
        }

        return categoryRepository.save(category);
    }

    @Override
    public List<TicketCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public TicketCategory getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow();
    }
}
