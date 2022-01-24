package com.ticket.system.status;

import com.ticket.system.ticket.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatusRepository extends JpaRepository<Status, Integer> {
 
    @Query("SELECT s FROM Status s WHERE s.ticket = ?1")
    public Status findByTicket(Ticket ticket);

}
