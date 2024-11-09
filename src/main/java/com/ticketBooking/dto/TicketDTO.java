package com.ticketBooking.dto;

import com.ticketBooking.entity.Payment;
import com.ticketBooking.entity.Ticket;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;


@Data
public class TicketDTO {


    private Long ticketId;

    private String seatNumber;

    private Long userId;


    private LocalDate bookingDate;


    private Ticket.TicketStatus ticketStatus;





}




