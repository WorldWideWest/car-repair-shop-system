package com.ticket.system.ticket;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ticket")
public class TicketController {
    
    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @GetMapping("/list")
    public String getTicketView(Model model){
        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("modelData", tickets);
        
        Ticket ticketForm = new Ticket();
        model.addAttribute("form", ticketForm);

        return "ticket/ticket-view";
    }

    @PostMapping("/save")
    public String postTicketView(@ModelAttribute("form") Ticket ticket){
        ticketService.save(ticket);
        return "redirect:/ticket/list";
    }

    @GetMapping("/update")
    public String updateTicketView(@ModelAttribute("ticketId") int id, Model model) {
        Ticket ticketForm = ticketService.findById(id);
        model.addAttribute("form", ticketForm);

        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("modelData", tickets);

        return "ticket/ticket-view";
    }

    @GetMapping("/delete")
    public String deleteTicketView(@ModelAttribute("ticketId") int id, Model model) {        
        ticketService.delete(id);
        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("modelData", tickets);

        return "redirect:/ticket/list";
    }
    

}
