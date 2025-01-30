package com.example.OnlineVehicleReservation.controller;

import com.example.OnlineVehicleReservation.model.Driver;
import com.example.OnlineVehicleReservation.service.DriverService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/driver")
public class DriverServlet extends HttpServlet {

    private DriverService driverService;

    @Override
    public void init() throws ServletException {
        driverService = new DriverService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String licenseNumber = request.getParameter("licenseNumber");
        String status = request.getParameter("status");
        String shiftTiming = request.getParameter("shiftTiming");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int experienceYears = Integer.parseInt(request.getParameter("experienceYears"));
        String phoneNumber = request.getParameter("phoneNumber");
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));

        Driver driver = new Driver(0, vehicleId, name, licenseNumber, status, shiftTiming, salary, experienceYears, phoneNumber);

        if (driverService.addDriver(driver)) {
            response.getWriter().write("Driver added successfully!");
        } else {
            response.getWriter().write("Error adding driver.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        Driver driver = driverService.getDriverById(driverId);

        if (driver != null) {
            response.getWriter().write("Driver Details: " + driver.toString());
        } else {
            response.getWriter().write("Driver not found.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        String name = request.getParameter("name");
        String licenseNumber = request.getParameter("licenseNumber");
        String status = request.getParameter("status");
        String shiftTiming = request.getParameter("shiftTiming");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int experienceYears = Integer.parseInt(request.getParameter("experienceYears"));
        String phoneNumber = request.getParameter("phoneNumber");

        Driver driver = new Driver(driverId, 0, name, licenseNumber, status, shiftTiming, salary, experienceYears, phoneNumber);

        if (driverService.updateDriver(driver)) {
            response.getWriter().write("Driver updated successfully!");
        } else {
            response.getWriter().write("Error updating driver.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));

        if (driverService.deleteDriver(driverId)) {
            response.getWriter().write("Driver deleted successfully!");
        } else {
            response.getWriter().write("Error deleting driver.");
        }
    }
}
