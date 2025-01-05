package com.interview.automatedparkinglot.repository.impl;

import com.interview.automatedparkinglot.config.DatabaseConnection;
import com.interview.automatedparkinglot.model.entity.Ticket;
import com.interview.automatedparkinglot.model.entity.Vehicle;
import com.interview.automatedparkinglot.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class TicketRepository implements IRepository<Ticket> {

    @Override
    public void save(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO Tickets (ticket_id, entry_time, parking_spot_id, vehicle_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, ticket.getTicketId());
            pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(ticket.getEntryTime()));
            pstmt.setString(3, ticket.getParkingSpotId());
            pstmt.setObject(4, ticket.getVehicle().getVehicleId());
            pstmt.executeUpdate();
        }
    }
    @Override
    public Ticket findById(UUID ticketId) throws SQLException {
        String sql = "SELECT * FROM Tickets WHERE ticket_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, ticketId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                LocalDateTime entryTime = rs.getTimestamp("entry_time").toLocalDateTime();
                String parkingSpotId = rs.getString("parking_spot_id");
                UUID vehicleId = UUID.fromString(rs.getString("vehicle_id"));
                Vehicle vehicle = new VehicleRepository().findById(vehicleId);
                Ticket ticket = new Ticket(parkingSpotId, vehicle);
                ticket.setExitTime(entryTime);
                return ticket;
            }
        }
        return null;
    }
    @Override
    public void update(Ticket ticket) throws SQLException {
        String sql = "UPDATE Tickets SET exit_time = ?, parking_charge = ? WHERE ticket_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(ticket.getExitTime()));
            pstmt.setDouble(2, ticket.getParkingCharge());
            pstmt.setObject(3, ticket.getTicketId());
            pstmt.executeUpdate();
        }
    }
}