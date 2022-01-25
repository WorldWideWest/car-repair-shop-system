package com.ticket.system.status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ticket.system.ticket.Ticket;

@Entity
@Table(name = "status")
public class Status {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "ticket", referencedColumnName = "id")
    private Ticket ticket;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Double cost;

    public Status(){}

    public Status(Ticket ticket, String status, String description, Double cost) {
        this.ticket = ticket;
        this.status = status;
        this.description = description;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Status [cost=" + cost + ", description=" + description + ", id=" + id + ", status=" + status
                + ", ticket=" + ticket + "]";
    }

}
