package com.ticketBooking.dto;

import com.ticketBooking.entity.Payment;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PaymentDTO {

    private Long paymentId;
    private Long ticketId;

    private BigDecimal amount;

    private LocalDate paymentDate;



    private Payment.PaymentStatus PaymentStatus;






}


