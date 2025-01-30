package com.example.OnlineVehicleReservation.service;

import com.example.OnlineVehicleReservation.db.DBConnection;
import com.example.OnlineVehicleReservation.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleService {

    private Connection connection;

    public VehicleService() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    // Add a new vehicle
    public boolean addVehicle(Vehicle vehicle) {
        try {
            String query = "INSERT INTO vehicles (car_type, model, availability_status, registration_number, fuel_type, car_model, seating_capacity) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, vehicle.getCarType());
            stmt.setString(2, vehicle.getModel());
            stmt.setString(3, vehicle.getAvailabilityStatus());
            stmt.setString(4, vehicle.getRegistrationNumber());
            stmt.setString(5, vehicle.getFuelType());
            stmt.setString(6, vehicle.getCarModel());
            stmt.setInt(7, vehicle.getSeatingCapacity());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a vehicle by its ID
    public Vehicle getVehicleById(int vehicleId) {
        try {
            String query = "SELECT * FROM vehicles WHERE vehicle_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String carType = rs.getString("car_type");
                String model = rs.getString("model");
                String availabilityStatus = rs.getString("availability_status");
                String registrationNumber = rs.getString("registration_number");
                String fuelType = rs.getString("fuel_type");
                String carModel = rs.getString("car_model");
                int seatingCapacity = rs.getInt("seating_capacity");

                return new Vehicle(vehicleId, carType, model, availabilityStatus, registrationNumber, fuelType, carModel, seatingCapacity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update a vehicle
    public boolean updateVehicle(Vehicle vehicle) {
        try {
            String query = "UPDATE vehicles SET car_type = ?, model = ?, availability_status = ?, registration_number = ?, fuel_type = ?, car_model = ?, seating_capacity = ? WHERE vehicle_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, vehicle.getCarType());
            stmt.setString(2, vehicle.getModel());
            stmt.setString(3, vehicle.getAvailabilityStatus());
            stmt.setString(4, vehicle.getRegistrationNumber());
            stmt.setString(5, vehicle.getFuelType());
            stmt.setString(6, vehicle.getCarModel());
            stmt.setInt(7, vehicle.getSeatingCapacity());
            stmt.setInt(8, vehicle.getVehicleId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a vehicle by its ID
    public boolean deleteVehicle(int vehicleId) {
        try {
            String query = "DELETE FROM vehicles WHERE vehicle_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, vehicleId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
