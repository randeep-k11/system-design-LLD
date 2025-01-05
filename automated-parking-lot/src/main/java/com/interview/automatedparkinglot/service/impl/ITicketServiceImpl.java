package com.interview.automatedparkinglot.service.impl;

import com.interview.automatedparkinglot.model.entity.Ticket;
import com.interview.automatedparkinglot.model.entity.Vehicle;
import com.interview.automatedparkinglot.model.enums.PaymentMethod;
import com.interview.automatedparkinglot.repository.impl.TicketRepository;
import com.interview.automatedparkinglot.service.IChargesService;
import com.interview.automatedparkinglot.service.ITicketService;
import com.interview.automatedparkinglot.service.PaymentStrategy;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class ITicketServiceImpl implements ITicketService {
    private TicketRepository ticketRepository;
    private IChargesService chargesService;

    public ITicketServiceImpl(TicketRepository ticketRepository, IChargesService chargesService) {
        this.ticketRepository = ticketRepository;
        this.chargesService = chargesService;
    }

    @Override
    public Ticket createTicket(String parkingSpotId, Vehicle vehicle) throws SQLException {
        Ticket ticket = new Ticket(parkingSpotId, vehicle);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public void closeTicket(Ticket ticket, PaymentMethod paymentMethod) throws SQLException {
        ticket.setExitTime(LocalDateTime.now());
        double charge = chargesService.calculateCharges(
                ticket.getVehicle().getVehicleType(),
                ticket.getEntryTime(),
                ticket.getExitTime()
        );
        ticket.setParkingCharge(charge);
        ticketRepository.update(ticket);

        PaymentStrategy paymentStrategy = getPaymentStrategy(paymentMethod);
        paymentStrategy.processPayment(charge);
    }

    private PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case CASH:
                return new CashPaymentStrategy();
            case UPI:
                return new UpiPaymentStrategy();
            default:
                throw new IllegalArgumentException("Invalid payment method");
        }
    }

    @Override
    public Ticket getTicketById(UUID ticketId) throws SQLException {
        return ticketRepository.findById(ticketId);
    }
}