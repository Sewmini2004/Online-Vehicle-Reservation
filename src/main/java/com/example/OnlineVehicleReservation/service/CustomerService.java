package com.example.OnlineVehicleReservation.service;

import com.example.OnlineVehicleReservation.model.Customer;

//Service layer eke nthrma thynne Dto object
public interface CustomerService {
    public boolean addCustomer(Customer customer);
    public Customer getCustomerById(int customerId);
    public boolean updateCustomer(Customer customer);
    public boolean deleteCustomer(int customerId);
}
