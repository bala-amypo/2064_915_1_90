package com.example.demo.service.impl;

import com.example.demo.model.DuplicateDetectionLog;
import com.example.demo.model.Ticket;
import com.example.demo.repository.DuplicateDetectionLogRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.DuplicateDetectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public DuplicateDetectionServiceImpl(
        DuplicateDetectionLogRepository logRepository,
        TicketRepository ticketRepository
) {
    this.logRepository = logRepository;
    this.ticketRepository = ticketRepository;
}


    @Override
    public List<DuplicateDetectionLog> getLogsForTicket(Long ticketId) {
        return logRepository.findByTicket_Id(ticketId);
    }

    @Override
    public List<DuplicateDetectionLog> detectDuplicates(Long ticketId) {
        return logRepository.findByTicket_Id(ticketId);
    }

    @Override
public DuplicateDetectionLog getLog(Long id) {
    return logRepository.findById(id).orElse(null);
}

}
