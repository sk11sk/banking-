package com.ticketBooking.bo;

import com.ticketBooking.dao.PaymentRepository;
import com.ticketBooking.dao.TicketRepository;
import com.ticketBooking.dto.BookingDetailsDTO;
import com.ticketBooking.dto.PaymentDTO;
import com.ticketBooking.dto.TicketBookingDTO;
import com.ticketBooking.dto.TicketDTO;
import com.ticketBooking.entity.Payment;
import com.ticketBooking.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TicketBO {


    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    @Transactional
    public BookingDetailsDTO bookTicket(TicketBookingDTO ticketBookingDTO) {


        BookingDetailsDTO  bookingDetailsDTO = new BookingDetailsDTO();

        TicketDTO ticketDTO = new TicketDTO();

        PaymentDTO paymentDTO = new PaymentDTO();


        try {
            // Step 1: Create Ticket with "RESERVED" status


            Ticket ticket = new Ticket();
            ticket.setSeatNumber(ticketBookingDTO.getSeatNumber());
            ticket.setUserId(ticketBookingDTO.getUserId());
            ticket.setTicketStatus(Ticket.TicketStatus.PENDING);
            Ticket savedTicket = ticketRepository.save(ticket);


            ticketDTO.setTicketId(savedTicket.getTicketId());
            ticketDTO.setUserId((savedTicket.getUserId()));
            ticketDTO.setSeatNumber(savedTicket.getSeatNumber());
            ticketDTO.setTicketStatus(savedTicket.getTicketStatus());
            ticketDTO.setBookingDate(savedTicket.getBookingDate());





//{
//  "ticket": {
//    "ticketId": 101,
//    "seatNumber": "A12",
//    "userId": 123,
//    "status": "PAID",
//    "createdAt": "2024-11-04T12:30:00"
//  },

            // Step 2: Create Payment with "PENDING" status
            Payment payment = new Payment();
            payment.setTicket(ticket);
            payment.setAmount(ticketBookingDTO.getAmount());
            payment.setPaymentStatus(Payment.PaymentStatus.PENDING);
            Payment savedPayment = paymentRepository.save(payment);



            paymentDTO.setPaymentId(savedPayment.getPaymentId());
            paymentDTO.setTicketId(savedPayment.getTicket().getTicketId());
            paymentDTO.setAmount(savedPayment.getAmount());
            paymentDTO.setPaymentStatus(savedPayment.getPaymentStatus());
            paymentDTO.setPaymentDate(savedPayment.getPaymentDate());




            //  "payment": {
//    "paymentId": 201,
//    "ticketId": 101,
//    "amount": 50.00,
//    "status": "SUCCESS",
//    "paymentDate": "2024-11-04T12:30:15"
//  }
//}




            BigDecimal amount = ticketBookingDTO.getAmount();

            // Simulate payment processing
            boolean paymentSuccess = processPayment(amount);

            if (paymentSuccess) {
                // Update ticket and payment statuses on successful payment


                ticket.setTicketStatus(Ticket.TicketStatus.BOOKED);  // Update Ticket status to "PAID"
                payment.setPaymentStatus(Payment.PaymentStatus.SUCCESS); // Update Payment status to "SUCCESS"


                ticketDTO.setTicketStatus(Ticket.TicketStatus.BOOKED);
                paymentDTO.setPaymentStatus(Payment.PaymentStatus.SUCCESS);


            } else {

                // If payment fails, throw an exception to trigger rollback

                throw new RuntimeException("Payment failed, rolling back transaction.");
            }

        } catch (Exception e) {

            // If any exception occurs, transaction will be rolled back
            throw new RuntimeException("Transaction failed, rolling back.", e);


        }
        bookingDetailsDTO.setTicket(ticketDTO);

        bookingDetailsDTO.setPayment(paymentDTO);

        return  bookingDetailsDTO;
    }


    private boolean processPayment(BigDecimal amount) {

        if (amount.compareTo(BigDecimal.valueOf(500)) == 0) {

            return true;

        }


        return false;
    }


    public BookingDetailsDTO findTicketById(Long ticketId) {

        BookingDetailsDTO  bookingDetailsDTO = new BookingDetailsDTO();

        TicketDTO ticketDTO = new TicketDTO();


        Ticket savedTicket = ticketRepository.findById(ticketId).get();
        ticketDTO.setTicketId(savedTicket.getTicketId());
        ticketDTO.setUserId((savedTicket.getUserId()));
        ticketDTO.setSeatNumber(savedTicket.getSeatNumber());
        ticketDTO.setTicketStatus(savedTicket.getTicketStatus());
        ticketDTO.setBookingDate(savedTicket.getBookingDate());

        bookingDetailsDTO.setTicket(ticketDTO);



        PaymentDTO paymentDTO = new PaymentDTO();

        Payment savedPayment = savedTicket.getPayment();

        paymentDTO.setPaymentId(savedPayment.getPaymentId());
        paymentDTO.setTicketId(savedPayment.getTicket().getTicketId());
        paymentDTO.setAmount(savedPayment.getAmount());
        paymentDTO.setPaymentStatus(savedPayment.getPaymentStatus());
        paymentDTO.setPaymentDate(savedPayment.getPaymentDate());







        bookingDetailsDTO.setPayment(paymentDTO);

        return  bookingDetailsDTO;
    }

    public List<BookingDetailsDTO> getAllTickets() {



        List<BookingDetailsDTO> bookingDetailsDTOList = new ArrayList<>();

        List<Ticket> allTickets = ticketRepository.findAll();

        for (Ticket  savedTicket: allTickets){

            BookingDetailsDTO  bookingDetailsDTO = new BookingDetailsDTO();

            TicketDTO ticketDTO = new TicketDTO();



            ticketDTO.setTicketId(savedTicket.getTicketId());
            ticketDTO.setUserId((savedTicket.getUserId()));
            ticketDTO.setSeatNumber(savedTicket.getSeatNumber());
            ticketDTO.setTicketStatus(savedTicket.getTicketStatus());
            ticketDTO.setBookingDate(savedTicket.getBookingDate());

            bookingDetailsDTO.setTicket(ticketDTO);



            PaymentDTO paymentDTO = new PaymentDTO();

            Payment savedPayment = savedTicket.getPayment();

            paymentDTO.setPaymentId(savedPayment.getPaymentId());
            paymentDTO.setTicketId(savedPayment.getTicket().getTicketId());
            paymentDTO.setAmount(savedPayment.getAmount());
            paymentDTO.setPaymentStatus(savedPayment.getPaymentStatus());
            paymentDTO.setPaymentDate(savedPayment.getPaymentDate());







            bookingDetailsDTO.setPayment(paymentDTO);

            bookingDetailsDTOList.add(bookingDetailsDTO);


        }


        return  bookingDetailsDTOList;
    }
}
