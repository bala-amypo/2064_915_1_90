package com.example.demo.service.impl;

import com.example.demo.model.TicketCategory;
import com.example.demo.repository.TicketCategoryRepository;
import com.example.demo.service.TicketCategoryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository repo;
    public TicketCategoryServiceImpl(TicketCategoryRepository r){ this.repo=r; }

    public TicketCategory createCategory(TicketCategory c){
        if(repo.existsByCategoryName(c.getCategoryName()))
            throw new RuntimeException("category exists");
        return repo.save(c);
    }

    public TicketCategory getCategory(Long id){
        return repo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    public List<TicketCategory> getAll(){ return repo.findAll(); }
}
