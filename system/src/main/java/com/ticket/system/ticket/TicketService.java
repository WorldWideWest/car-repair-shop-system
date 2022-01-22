package com.ticket.system.ticket;

import java.util.List;

public interface TicketService {

    public List<Ticket> findAll();

    public Ticket findById(int id);

    public void save(Ticket ticket);

    public void delete(int id);    
}
