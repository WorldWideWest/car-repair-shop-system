package com.ticket.system.ticket;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ticket")
public class TicketController {
    
    private TicketService ticketService;

    @Autowired
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
    public String postTicketView(@Valid @ModelAttribute("form") Ticket ticket, BindingResult bindingResult, Model model){
        
        if(bindingResult.hasErrors()){
            
            List<Ticket> tickets = ticketService.findAll();
            model.addAttribute("modelData", tickets);
            return "ticket/ticket-view";
            
        }
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
