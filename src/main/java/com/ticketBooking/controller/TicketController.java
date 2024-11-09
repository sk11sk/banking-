package com.ticketBooking.controller;

import com.ticketBooking.dto.BookingDetailsDTO;
import com.ticketBooking.dto.TicketBookingDTO;
import com.ticketBooking.entity.Ticket;
import com.ticketBooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;



    //http://localhost:8081/api/tickets/bookTicket
    @PostMapping("/bookTicket")
    public ResponseEntity<BookingDetailsDTO> bookTicket(@RequestBody TicketBookingDTO ticketBookingDTO) {


        BookingDetailsDTO bookingDetails =   ticketService.bookTicket(ticketBookingDTO);

        return new ResponseEntity<>(bookingDetails, HttpStatus.CREATED) ;


    }

   // http://localhost:8081/api/tickets/findTicketById/3


   @GetMapping("/findTicketById/{ticketId}")
    public ResponseEntity<BookingDetailsDTO> findTicketById(@PathVariable Long  ticketId){

        BookingDetailsDTO bookingDetails =   ticketService.findTicketById(ticketId);

        return new ResponseEntity<>(bookingDetails, HttpStatus.CREATED) ;


    }


    // http://localhost:8081/api/tickets/getAllTickets

    @GetMapping("/getAllTickets")
    public ResponseEntity<List<BookingDetailsDTO>> getAllTickets(){

        List<BookingDetailsDTO> bookingDetailsList =   ticketService.getAllTickets();

        return new ResponseEntity<>(bookingDetailsList, HttpStatus.CREATED) ;


    }



}
