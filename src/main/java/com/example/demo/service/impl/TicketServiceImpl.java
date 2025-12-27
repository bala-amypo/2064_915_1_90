package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.TicketService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository tRepo;
    private final UserRepository uRepo;
    private final TicketCategoryRepository cRepo;

    public TicketServiceImpl(TicketRepository t,UserRepository u,TicketCategoryRepository c){
        this.tRepo=t; this.uRepo=u; this.cRepo=c;
    }

    public Ticket createTicket(Long userId,Long catId,Ticket t){
        User u = uRepo.findById(userId).orElseThrow();
        TicketCategory c = cRepo.findById(catId).orElseThrow();

        if(t.getDescription()==null || t.getDescription().length() < 10)
            throw new RuntimeException("description too short");

        t.setUser(u);
        t.setCategory(c);
        return tRepo.save(t);
    }

    public Ticket getTicket(Long id){
        return tRepo.findById(id).orElseThrow(()->new RuntimeException("ticket not found"));
    }

    public List<Ticket> getAllTickets(){ return tRepo.findAll(); }

    public List<Ticket> getTicketsByUser(Long id){
        return tRepo.findByUser_Id(id);
    }
}
