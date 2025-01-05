package com.interview.automatedparkinglot;

import com.interview.automatedparkinglot.model.entity.Ticket;
import com.interview.automatedparkinglot.model.entity.Vehicle;
import com.interview.automatedparkinglot.model.enums.PaymentMethod;
import com.interview.automatedparkinglot.model.enums.VehicleType;
import com.interview.automatedparkinglot.repository.impl.ParkingSpotRepository;
import com.interview.automatedparkinglot.service.impl.NearestParkingSpotStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

public class AutomatedParkingLotApplication implements Runnable {

    public static void main(String[] args) {
        try {
            ParkingLot parkingLot = ParkingLot.getInstance();
            Vehicle vehicle = new Vehicle("ABC123", VehicleType.CAR);
            Ticket ticket = parkingLot.assignParkingSpot(vehicle, new NearestParkingSpotStrategy(new ParkingSpotRepository()));
            System.out.println("Ticket issued: " + ticket.getTicketId());
            parkingLot.releaseParkingSpot(ticket, PaymentMethod.CASH);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
