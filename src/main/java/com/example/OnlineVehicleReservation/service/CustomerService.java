package com.example.OnlineVehicleReservation.service;

import com.example.OnlineVehicleReservation.db.DBConnection;
import com.example.OnlineVehicleReservation.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService {
    private Connection connection;

    public CustomerService() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public boolean addCustomer(Customer customer) {
        try {
            String query = "INSERT INTO customers (user_id, name, address, nic, phone_number, registration_date, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, customer.getUserId());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getNic());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.setString(6, customer.getRegistrationDate());
            stmt.setString(7, customer.getEmail());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Customer getCustomerById(int customerId) {
        try {
            String query = "SELECT * FROM customers WHERE customer_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String nic = rs.getString("nic");
                String phoneNumber = rs.getString("phone_number");
                String registrationDate = rs.getString("registration_date");
                String email = rs.getString("email");

                return new Customer(customerId, userId, name, address, nic, phoneNumber, registrationDate, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateCustomer(Customer customer) {
        try {
            String query = "UPDATE customers SET name = ?, address = ?, nic = ?, phone_number = ?, registration_date = ?, email = ? WHERE customer_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getNic());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getRegistrationDate());
            stmt.setString(6, customer.getEmail());
            stmt.setInt(7, customer.getCustomerId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(int customerId) {
        try {
            String query = "DELETE FROM customers WHERE customer_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, customerId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
