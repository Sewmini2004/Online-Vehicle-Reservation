package com.example.OnlineVehicleReservation.controller;

import com.example.OnlineVehicleReservation.model.Booking;
import com.example.OnlineVehicleReservation.service.BookingServices;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class BookingServlet extends HttpServlet {
    private BookingServices bookingServices;

    public void init() {
        bookingServices = new BookingServices();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addBooking".equals(action)) {
            // Handle add booking
            Booking booking = new Booking();
            booking.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
            booking.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
            booking.setDriverID(Integer.parseInt(request.getParameter("driverId")));
            booking.setPickupLocation(request.getParameter("pickupLocation"));
            booking.setDropLocation(request.getParameter("dropLocation"));
            booking.setBookingDate(request.getParameter("bookingDate"));
            booking.setCarType(request.getParameter("carType"));
            booking.setTotalBill(Double.parseDouble(request.getParameter("totalBill")));

            if (bookingServices.addBooking(booking)) {
                response.sendRedirect("bookingSuccess.jsp"); // Redirect to success page
            } else {
                response.sendRedirect("bookingError.jsp"); // Show error message
            }

        } else if ("updateBooking".equals(action)) {
            // Handle update booking
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            Booking booking = bookingServices.searchBooking(bookingId);
            if (booking != null) {
                booking.setPickupLocation(request.getParameter("pickupLocation"));
                booking.setDropLocation(request.getParameter("dropLocation"));
                booking.setCarType(request.getParameter("carType"));
                booking.setTotalBill(Double.parseDouble(request.getParameter("totalBill")));
                if (bookingServices.updateBooking(booking)) {
                    response.sendRedirect("updateSuccess.jsp"); // Redirect to success page
                } else {
                    response.sendRedirect("updateError.jsp"); // Show error message
                }
            }
        } else if ("cancelBooking".equals(action)) {
            // Handle cancel booking
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            if (bookingServices.cancelBooking(bookingId)) {
                response.sendRedirect("cancelSuccess.jsp"); // Redirect to success page
            } else {
                response.sendRedirect("cancelError.jsp"); // Show error message
            }
        }
    }
}
