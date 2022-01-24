package com.ticket.system.status;

import java.util.List;
import java.util.Optional;

import com.ticket.system.ticket.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService{

    private StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status findById(int id) {
        Optional<Status> status = statusRepository.findById(id);
        if(status == null){
            throw new RuntimeException("There is no status ticket with the id: " + id);
        }
        return status.get();
    }

    @Override
    public Status findByTicket(Ticket ticket){
        return statusRepository.findByTicket(ticket);
    }

    @Override
    public void save(Status status) {
        statusRepository.save(status);
    }

    @Override
    public void delete(int id) {
        statusRepository.deleteById(id);
    }
    
}
