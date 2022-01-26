package com.ticket.system.status;

import java.util.List;

import javax.validation.Valid;

import com.ticket.system.ticket.TicketService;
import com.ticket.system.ticket.Ticket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping("/status")
public class StatusController {
    
    private StatusService statusService;
    private TicketService ticketService;


    public StatusController(StatusService statusService, TicketService ticketService){
        this.statusService = statusService;
        this.ticketService = ticketService;
    }

    @GetMapping("/list")
    public String getTicketView(Model model){
        List<Status> status = statusService.findAll();
        model.addAttribute("modelData", status);
        
        Status statusForm = new Status();
        model.addAttribute("form", statusForm);

        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("tickets", tickets);

        return "status/status-view";
    }

    @PostMapping("/save")
    public String postTicketView(@Valid @ModelAttribute("form") Status status, BindingResult bindingResult, Model model){
        
        if(bindingResult.hasErrors()){
            List<Status> statusData = statusService.findAll();
            model.addAttribute("modelData", statusData);
            
            List<Ticket> tickets = ticketService.findAll();
            model.addAttribute("tickets", tickets);
            return "status/status-view";

        }
            statusService.save(status);
            return "redirect:/status/list";
    }

    @GetMapping("/update")
    public String updateTicketView(@ModelAttribute("statusId") int id, Model model) {
        Status statusForm = statusService.findById(id);
        model.addAttribute("form", statusForm);

        List<Status> status = statusService.findAll();
        model.addAttribute("modelData", status);

        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("tickets", tickets);

        return "status/status-view";
    }

    @GetMapping("/delete")
    public String deleteTicketView(@ModelAttribute("statusId") int id, Model model) {        
        statusService.delete(id);
        List<Status> status = statusService.findAll();
        model.addAttribute("modelData", status);

        return "redirect:/status/list";
    }


}
