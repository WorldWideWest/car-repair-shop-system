package com.ticket.system.status;

import java.util.List;

public interface StatusService {
    
    public List<Status> findAll();

    public Status findById(int id);

    public void save(Status status);

    public void delete(int id);    

}
