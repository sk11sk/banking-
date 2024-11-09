package com.ticketBooking.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDate;


@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Column(unique = true)
    private String seatNumber;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private TicketStatus  ticketStatus;

    @Temporal(TemporalType.DATE)
    @CreatedDate
    private LocalDate bookingDate;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Payment payment;



    public enum TicketStatus {
        PENDING, BOOKED, CANCELLED
    }


}
