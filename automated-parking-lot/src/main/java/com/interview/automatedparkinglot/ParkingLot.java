package com.interview.automatedparkinglot;

import com.interview.automatedparkinglot.model.entity.ParkingSpot;
import com.interview.automatedparkinglot.model.entity.Ticket;
import com.interview.automatedparkinglot.model.entity.Vehicle;
import com.interview.automatedparkinglot.model.enums.PaymentMethod;
import com.interview.automatedparkinglot.repository.impl.ParkingSpotRepository;
import com.interview.automatedparkinglot.repository.impl.TicketRepository;
import com.interview.automatedparkinglot.service.ITicketService;
import com.interview.automatedparkinglot.service.ParkingSpotStrategy;
import com.interview.automatedparkinglot.service.impl.ChargesServiceImpl;
import com.interview.automatedparkinglot.service.impl.ITicketServiceImpl;

import java.sql.SQLException;
import java.util.Optional;

class ParkingLot {
    private static ParkingLot instance = null;
    private ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepository();
    private ITicketService ticketService;

    private ParkingLot() throws SQLException {
        this.ticketService = new ITicketServiceImpl(new TicketRepository(), new ChargesServiceImpl());
    }

    public static synchronized ParkingLot getInstance() throws SQLException {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public Ticket assignParkingSpot(Vehicle vehicle, ParkingSpotStrategy strategy) throws SQLException {
        Optional<ParkingSpot> spotOptional = strategy.findSpot();
        if (!spotOptional.isPresent()) {
            throw new RuntimeException("No parking spots available");
        }
        ParkingSpot spot = spotOptional.get();
        spot.parkVehicle(vehicle);
        parkingSpotRepository.updateSpot(spot);
        return ticketService.createTicket(spot.getSpotId(), vehicle);
    }

    public void releaseParkingSpot(Ticket ticket, PaymentMethod paymentMethod) throws SQLException {
        ParkingSpot spot = parkingSpotRepository.findAvailableSpot(); // Adjusted for simplicity
        if (spot != null) {
            spot.removeVehicle();
            parkingSpotRepository.updateSpot(spot);
            ticketService.closeTicket(ticket, paymentMethod);
        }
    }
}