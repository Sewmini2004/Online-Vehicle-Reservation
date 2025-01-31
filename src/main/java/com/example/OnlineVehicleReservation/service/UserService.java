package com.example.OnlineVehicleReservation.service;

import com.example.OnlineVehicleReservation.db.DBConnection;
import java.sql.*;

public class UserService {

    // Method for validating login
    public boolean validateLogin(String username, String password) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM User WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                return rs.next(); // If a record exists, login is successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method for validating the username (for password reset)
    public boolean validateUsername(String username) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM User WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                return rs.next(); // Return true if username exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to reset password
    public boolean resetPassword(String username, String newPassword) {
        try (Connection conn = DBConnection.getInstance().getConnection()) {
            String query = "UPDATE User SET password = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newPassword);
                stmt.setString(2, username);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0; // Return true if the password is updated
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
