package com.ticketBooking.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class TicketBookingDTO {


    private Long userId;

    private String seatNumber;

    private BigDecimal amount;





}
