package com.interview.automatedparkinglot.repository.impl;

import com.interview.automatedparkinglot.config.DatabaseConnection;
import com.interview.automatedparkinglot.model.entity.Vehicle;
import com.interview.automatedparkinglot.model.enums.VehicleType;
import com.interview.automatedparkinglot.repository.IRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class VehicleRepository implements IRepository<Vehicle> {
    @Override
    public void save(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO Vehicles (vehicle_id, license_plate_number, vehicle_type) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, vehicle.getVehicleId());
            pstmt.setString(2, vehicle.getLicensePlateNumber());
            pstmt.setString(3, vehicle.getVehicleType().name());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Vehicle findById(UUID vehicleId) throws SQLException {
        String sql = "SELECT * FROM Vehicles WHERE vehicle_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, vehicleId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String licensePlate = rs.getString("license_plate_number");
                VehicleType vehicleType = VehicleType.valueOf(rs.getString("vehicle_type"));
                return new Vehicle(licensePlate, vehicleType);
            }
        }
        return null;
    }

    @Override
    public void update(Vehicle entity) throws SQLException {}
}