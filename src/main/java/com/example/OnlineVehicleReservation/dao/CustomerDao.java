package com.example.OnlineVehicleReservation.dao;

import com.example.OnlineVehicleReservation.model.Customer;

import java.util.List;

public interface CustomerDao {
    public void saveCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(Long id);
    public List<Customer> getAllCustomers();
    public Customer getById(Long id);
}
