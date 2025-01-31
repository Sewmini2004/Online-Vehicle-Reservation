package com.example.OnlineVehicleReservation.service;

import com.example.OnlineVehicleReservation.db.DBConnection;
import com.example.OnlineVehicleReservation.model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingServices {

    // Add a new booking
    public boolean addBooking(Booking booking) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "INSERT INTO Booking (customerId, vehicleId, driverId, pickupLocation, dropLocation, bookingDate, carType, totalBill, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, booking.getCustomerId());
                stmt.setInt(2, booking.getVehicleId());
                stmt.setInt(3, booking.getDriverID());
                stmt.setString(4, booking.getPickupLocation());
                stmt.setString(5, booking.getDropLocation());
                stmt.setString(6, booking.getBookingDate());
                stmt.setString(7, booking.getCarType());
                stmt.setDouble(8, booking.getTotalBill());
                stmt.setString(9, "active"); // Status when a booking is created
                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0; // If at least one row is inserted
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search for a booking by booking ID
    public Booking searchBooking(int bookingId) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM Booking WHERE bookingId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, bookingId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(rs.getInt("bookingId"));
                    booking.setCustomerId(rs.getInt("customerId"));
                    booking.setVehicleId(rs.getInt("vehicleId"));
                    booking.setDriverID(rs.getInt("driverId"));
                    booking.setPickupLocation(rs.getString("pickupLocation"));
                    booking.setDropLocation(rs.getString("dropLocation"));
                    booking.setBookingDate(rs.getString("bookingDate"));
                    booking.setCarType(rs.getString("carType"));
                    booking.setTotalBill(rs.getDouble("totalBill"));
//                    booking.setStatus(rs.getString("status"));
                    return booking;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update booking details (e.g., pickup location, drop location)
    public boolean updateBooking(Booking booking) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "UPDATE Booking SET pickupLocation = ?, dropLocation = ?, carType = ?, totalBill = ? WHERE bookingId = ? AND status = 'active'";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, booking.getPickupLocation());
                stmt.setString(2, booking.getDropLocation());
                stmt.setString(3, booking.getCarType());
                stmt.setDouble(4, booking.getTotalBill());
                stmt.setInt(5, booking.getBookingId());
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cancel a booking (update status to 'canceled')
    public boolean cancelBooking(int bookingId) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "UPDATE Booking SET status = 'canceled' WHERE bookingId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, bookingId);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
