package com.ticket.system.status;

import java.util.List;

import com.ticket.system.ticket.Ticket;

public interface StatusService {
    
    public List<Status> findAll();

    public Status findById(int id);
    
    public Status findByTicket(Ticket ticket);

    public void save(Status status);

    public void delete(int id);

}
