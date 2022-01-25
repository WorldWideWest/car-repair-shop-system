package com.ticket.system.home;

import java.util.Optional;

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
    public String searchView(@RequestParam("ticketId") int id, Model model ){

        Ticket ticket = ticketService.findById(id);
        if(ticket != null){
            model.addAttribute("data", ticket);

            Status status = statusService.findByTicket(ticket);
            model.addAttribute("status", status);

        }else{
            model.addAttribute("error", "There is no registered ticket with the id: " + id);
        }

        return "home/home-view";
    }

    @GetMapping("/access-denied")
    public String accessDeniedView(){
        return "home/access-denied";
    }

}
