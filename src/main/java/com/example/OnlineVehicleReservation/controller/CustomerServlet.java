package com.example.OnlineVehicleReservation.controller;

import com.example.OnlineVehicleReservation.model.Customer;
import com.example.OnlineVehicleReservation.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

    @WebServlet("/customer")
    public class CustomerServlet extends HttpServlet {

        private CustomerService customerService;

        @Override
        public void init() throws ServletException {
            customerService = new CustomerService();
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String nic = request.getParameter("nic");
            String phoneNumber = request.getParameter("phone");
            String registrationDate = request.getParameter("registrationDate");
            String email = request.getParameter("email");
            int userId = Integer.parseInt(request.getParameter("userId"));

            Customer customer = new Customer(0, userId, name, address, nic, phoneNumber, registrationDate, email);

            if (customerService.addCustomer(customer)) {
                response.getWriter().write("Customer added successfully!");
            } else {
                response.getWriter().write("Error adding customer.");
            }
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            Customer customer = customerService.getCustomerById(customerId);

            if (customer != null) {
                response.getWriter().write("Customer Details: " + customer.toString());
            } else {
                response.getWriter().write("Customer not found.");
            }
        }

        @Override
        protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String nic = request.getParameter("nic");
            String phoneNumber = request.getParameter("phone");
            String registrationDate = request.getParameter("registrationDate");
            String email = request.getParameter("email");

            Customer customer = new Customer(customerId, 0, name, address, nic, phoneNumber, registrationDate, email);

            if (customerService.updateCustomer(customer)) {
                response.getWriter().write("Customer updated successfully!");
            } else {
                response.getWriter().write("Error updating customer.");
            }
        }

        @Override
        protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int customerId = Integer.parseInt(request.getParameter("customerId"));

            if (customerService.deleteCustomer(customerId)) {
                response.getWriter().write("Customer deleted successfully!");
            } else {
                response.getWriter().write("Error deleting customer.");
            }
        }
}
