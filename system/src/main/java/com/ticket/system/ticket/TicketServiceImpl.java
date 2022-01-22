package com.ticket.system.ticket;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService{

    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(int id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket == null){ 
            throw new RuntimeException("There is no ticket with the id: " + id);
        }
        return ticket.get();
    }

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
        
    }

    @Override
    public void delete(int id) {
        ticketRepository.deleteById(id);
    }
    
}
