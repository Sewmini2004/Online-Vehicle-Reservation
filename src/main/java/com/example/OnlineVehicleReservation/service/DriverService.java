package com.example.OnlineVehicleReservation.service;

import com.example.OnlineVehicleReservation.db.DBConnection;
import com.example.OnlineVehicleReservation.model.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverService {

    private Connection connection;

    public DriverService() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public boolean addDriver(Driver driver) {
        try {
            String query = "INSERT INTO drivers (vehicle_id, name, license_number, status, shift_timing, salary, experience_years, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, driver.getVehicleId());
            stmt.setString(2, driver.getName());
            stmt.setString(3, driver.getLicenseNumber());
            stmt.setString(4, driver.getStatus());
            stmt.setString(5, driver.getShiftTiming());
            stmt.setDouble(6, driver.getSalary());
            stmt.setInt(7, driver.getExperienceYears());
            stmt.setString(8, driver.getPhoneNumber());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Driver getDriverById(int driverId) {
        try {
            String query = "SELECT * FROM drivers WHERE driver_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int vehicleId = rs.getInt("vehicle_id");
                String name = rs.getString("name");
                String licenseNumber = rs.getString("license_number");
                String status = rs.getString("status");
                String shiftTiming = rs.getString("shift_timing");
                double salary = rs.getDouble("salary");
                int experienceYears = rs.getInt("experience_years");
                String phoneNumber = rs.getString("phone_number");

                return new Driver(driverId, vehicleId, name, licenseNumber, status, shiftTiming, salary, experienceYears, phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateDriver(Driver driver) {
        try {
            String query = "UPDATE drivers SET name = ?, license_number = ?, status = ?, shift_timing = ?, salary = ?, experience_years = ?, phone_number = ? WHERE driver_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getStatus());
            stmt.setString(4, driver.getShiftTiming());
            stmt.setDouble(5, driver.getSalary());
            stmt.setInt(6, driver.getExperienceYears());
            stmt.setString(7, driver.getPhoneNumber());
            stmt.setInt(8, driver.getDriverId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDriver(int driverId) {
        try {
            String query = "DELETE FROM drivers WHERE driver_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, driverId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
