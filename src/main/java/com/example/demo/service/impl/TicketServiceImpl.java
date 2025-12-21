package com.example.demo.service.impl;

import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.TicketCategory;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.TicketCategoryRepository;
import com.example.demo.service.TicketService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;
    private final TicketCategoryRepository categoryRepo;

    public TicketServiceImpl(TicketRepository ticketRepo,
                             UserRepository userRepo,
                             TicketCategoryRepository categoryRepo) {
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Ticket createTicket(Long userId, Long categoryId, Ticket ticket) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        TicketCategory category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (ticket.getSubject() == null || ticket.getSubject().isBlank()) {
            throw new IllegalArgumentException("subject cannot be blank");
        }

        if (ticket.getDescription() == null || ticket.getDescription().length() < 10) {
            throw new IllegalArgumentException("description must be at least 10 characters");
        }

        if (ticket.getStatus() == null)
            ticket.setStatus("OPEN");

        ticket.setUser(user);
        ticket.setCategory(category);

        return ticketRepo.save(ticket);
    }

    @Override
    public Ticket getTicket(Long id) {
        return ticketRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ticket not found"));
    }

    @Override
    public List<Ticket> getTicketsByUser(Long userId) {
        return ticketRepo.findByUser_Id(userId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }
}
