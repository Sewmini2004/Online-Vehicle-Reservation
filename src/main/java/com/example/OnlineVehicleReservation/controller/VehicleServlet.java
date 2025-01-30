package com.example.OnlineVehicleReservation.controller;

import com.example.OnlineVehicleReservation.model.Vehicle;
import com.example.OnlineVehicleReservation.service.VehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/vehicle")
public class VehicleServlet extends HttpServlet {

    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        vehicleService = new VehicleService();
    }

    // Handle POST request to add a new vehicle
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carType = request.getParameter("carType");
        String model = request.getParameter("model");
        String availabilityStatus = request.getParameter("availabilityStatus");
        String registrationNumber = request.getParameter("registrationNumber");
        String fuelType = request.getParameter("fuelType");
        String carModel = request.getParameter("carModel");
        int seatingCapacity = Integer.parseInt(request.getParameter("seatingCapacity"));

        Vehicle vehicle = new Vehicle(0, carType, model, availabilityStatus, registrationNumber, fuelType, carModel, seatingCapacity);

        if (vehicleService.addVehicle(vehicle)) {
            response.getWriter().write("Vehicle added successfully!");
        } else {
            response.getWriter().write("Error adding vehicle.");
        }
    }

    // Handle GET request to fetch a vehicle by its ID
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        if (vehicle != null) {
            response.getWriter().write("Vehicle Details: " + vehicle.toString());
        } else {
            response.getWriter().write("Vehicle not found.");
        }
    }

    // Handle PUT request to update vehicle information
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String carType = request.getParameter("carType");
        String model = request.getParameter("model");
        String availabilityStatus = request.getParameter("availabilityStatus");
        String registrationNumber = request.getParameter("registrationNumber");
        String fuelType = request.getParameter("fuelType");
        String carModel = request.getParameter("carModel");
        int seatingCapacity = Integer.parseInt(request.getParameter("seatingCapacity"));

        Vehicle vehicle = new Vehicle(vehicleId, carType, model, availabilityStatus, registrationNumber, fuelType, carModel, seatingCapacity);

        if (vehicleService.updateVehicle(vehicle)) {
            response.getWriter().write("Vehicle updated successfully!");
        } else {
            response.getWriter().write("Error updating vehicle.");
        }
    }

    // Handle DELETE request to delete a vehicle by its ID
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));

        if (vehicleService.deleteVehicle(vehicleId)) {
            response.getWriter().write("Vehicle deleted successfully!");
        } else {
            response.getWriter().write("Error deleting vehicle.");
        }
    }
}
