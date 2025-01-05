package com.interview.automatedparkinglot.repository.impl;

import com.interview.automatedparkinglot.config.DatabaseConnection;
import com.interview.automatedparkinglot.model.entity.ParkingSpot;
import com.interview.automatedparkinglot.model.enums.ParkingSlotStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingSpotRepository {
    public ParkingSpot findAvailableSpot() throws SQLException {
        String sql = "SELECT * FROM ParkingSpots WHERE status = 'AVAILABLE' LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String spotId = rs.getString("spot_id");
                ParkingSlotStatus status = ParkingSlotStatus.valueOf(rs.getString("status"));
                return new ParkingSpot(spotId, status);
            }
        }
        return null;
    }
    public void updateSpot(ParkingSpot spot) throws SQLException {
        String sql = "UPDATE ParkingSpots SET status = ? WHERE spot_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, spot.getStatus().name());
            pstmt.setString(2, spot.getSpotId());
            pstmt.executeUpdate();
        }
    }
}