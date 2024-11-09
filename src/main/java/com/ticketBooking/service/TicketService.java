package com.ticketBooking.service;


import com.ticketBooking.bo.TicketBO;
import com.ticketBooking.dto.BookingDetailsDTO;
import com.ticketBooking.dto.TicketBookingDTO;
import com.ticketBooking.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketBO ticketBO;


    public BookingDetailsDTO bookTicket(TicketBookingDTO ticketBookingDTO) {


        BookingDetailsDTO bookingDetails =    ticketBO.bookTicket(ticketBookingDTO);

        return bookingDetails;
    }

    public BookingDetailsDTO findTicketById(Long ticketId) {

        BookingDetailsDTO bookingDetails =    ticketBO.findTicketById(ticketId);

        return bookingDetails;
    }

    public List<BookingDetailsDTO> getAllTickets() {

        List<BookingDetailsDTO> bookingDetailsList =    ticketBO.getAllTickets();

        return bookingDetailsList;
    }
}
