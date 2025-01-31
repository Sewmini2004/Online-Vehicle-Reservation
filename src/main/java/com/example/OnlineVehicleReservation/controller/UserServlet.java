package com.example.OnlineVehicleReservation.controller;

import com.example.OnlineVehicleReservation.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private UserService userService;

    public void init() {
        userService = new UserService(); // Initialize the service
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            // Handle login
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (userService.validateLogin(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username); // Set the username in session
                response.sendRedirect("dashboard.jsp"); // Redirect to dashboard
            } else {
                response.sendRedirect("login.jsp?error=invalid_credentials"); // Invalid credentials
            }

        } else if ("forgotPassword".equals(action)) {
            // Handle forgot password
            String username = request.getParameter("username");

            if (userService.validateUsername(username)) {
                String newPassword = request.getParameter("newPassword");
                if (userService.resetPassword(username, newPassword)) {
                    response.sendRedirect("login.jsp?message=password_reset"); // Redirect to login page with success
                } else {
                    response.sendRedirect("forgotPassword.jsp?error=reset_failed"); // Show error
                }
            } else {
                response.sendRedirect("forgotPassword.jsp?error=username_not_found"); // Username not found
            }
        }
    }
}
