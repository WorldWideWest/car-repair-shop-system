package com.ticket.system.home;

import com.ticket.system.status.Status;
import com.ticket.system.status.StatusService;
import com.ticket.system.ticket.Ticket;
import com.ticket.system.ticket.TicketService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private StatusService statusService;
    private TicketService ticketService;

    public HomeController(StatusService statusService, TicketService ticketService){
        this.statusService = statusService;
        this.ticketService = ticketService;
    }
    
    @GetMapping("/")
    public String homeView(Model model ){
        return "home/home-view";
    }

    
    @GetMapping("/search")
    public String searchView(@RequestParam("ticketId") String id, Model model ){

        int parsedId;
        try{
            parsedId = Integer.parseInt(id.toString());
        } catch (Exception e){
            model.addAttribute("error", "Ticket must contain only numbers, your ticket containes: " + id);
            return "home/home-view";
        }

        Ticket ticket = null;
        try{
            ticket = ticketService.findById(parsedId);
            model.addAttribute("data", ticket);
            
            Status status = statusService.findByTicket(ticket);
            model.addAttribute("status", status);

        } catch (Exception e){
            model.addAttribute("error", "There is no registered ticket with the id: " + id);
            return "home/home-view";
        }

        return "home/home-view";
    }

    // Authentication view's
    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/login-error")
    public String loginErrorView(Model model){
        model.addAttribute("error", true);
        return "auth/login";
    }

    @GetMapping("/logout-view")
    public String logoutView(){
        return "auth/logout";
    }

    @GetMapping("/access-denied")
    public String accessDeniedView(){
        return "home/access-denied";
    }

}
