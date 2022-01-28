package com.ticket.system.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, Exception e, Model model){
        logger.info("SQLException Occured:: URL=" + request.getRequestURL());
        String referer = request.getHeader("Referer");
        
        model.addAttribute("back", referer);
        model.addAttribute("message", e.getMessage());

        return "error/db-error";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
    @ExceptionHandler(IOException.class)
    public void handleIOException(){
        logger.error("IOException!");
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(HttpServletRequest request, Exception e, Model model){

        logger.info("NumberFormat Error Occured:: URL=" + request.getRequestURL());
        String referer = request.getHeader("Referer");
        
        model.addAttribute("back", referer);
        model.addAttribute("message", e.getMessage());

        return "error/number-error";
    }


}
