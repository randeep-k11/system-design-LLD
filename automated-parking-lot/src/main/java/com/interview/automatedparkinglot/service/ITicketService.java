package com.interview.automatedparkinglot.service;

import com.interview.automatedparkinglot.model.entity.Ticket;
import com.interview.automatedparkinglot.model.entity.Vehicle;
import com.interview.automatedparkinglot.model.enums.PaymentMethod;

import java.sql.SQLException;
import java.util.UUID;

public interface ITicketService {
    Ticket createTicket(String parkingSpotId, Vehicle vehicle) throws SQLException;
    void closeTicket(Ticket ticket, PaymentMethod paymentMethod) throws SQLException;
    Ticket getTicketById(UUID ticketId) throws SQLException;
}