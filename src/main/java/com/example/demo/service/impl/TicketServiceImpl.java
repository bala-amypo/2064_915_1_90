package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.TicketCategory;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.TicketCategoryRepository;
import com.example.demo.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketCategoryRepository categoryRepository;

    public TicketServiceImpl(
            TicketRepository ticketRepository,
            UserRepository userRepository,
            TicketCategoryRepository categoryRepository) {

        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Ticket createTicket(Long userId, Long categoryId, Ticket ticket) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TicketCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (ticket.getSubject() == null || ticket.getSubject().isBlank()) {
            throw new IllegalArgumentException("Subject cannot be blank");
        }

        if (ticket.getDescription() == null || ticket.getDescription().length() < 10) {
            throw new IllegalArgumentException("Description is too short");
        }

        ticket.setUser(user);
        ticket.setCategory(category);

        if (ticket.getStatus() == null) {
            ticket.setStatus("OPEN");
        }

        if (ticket.getCreatedAt() == null) {
            ticket.setCreatedAt(LocalDateTime.now());
        }

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public List<Ticket> getTicketsByUser(Long userId) {
        return ticketRepository.findByUser_Id(userId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
