package com.example.demo.controller;

import com.example.demo.model.TicketCategory;
import com.example.demo.service.TicketCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class TicketCategoryController {

    private final TicketCategoryService categoryService;

    public TicketCategoryController(TicketCategoryService categoryService){
        this.categoryService = categoryService;
    }

    // Create category
    @PostMapping
    public TicketCategory create(@RequestBody TicketCategory category){
        return categoryService.createCategory(category);
    }

    // Get single category
    @GetMapping("/{id}")
    public TicketCategory get(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    // Get all categories
    @GetMapping
    public List<TicketCategory> getAll(){
        return categoryService.getAll();
    }
}
