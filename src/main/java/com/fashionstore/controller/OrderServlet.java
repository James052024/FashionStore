package com.fashionstore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔹 Get form data
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String pincode = request.getParameter("pincode");

        // 🔹 Basic validation
        if (address == null || city == null || pincode == null ||
            address.isEmpty() || city.isEmpty() || pincode.isEmpty()) {

            response.sendRedirect("checkout");
            return;
        }

        // 🔹 TEMP: simulate order success (we'll add DB later)
        HttpSession session = request.getSession();

        // clear cart after order
        session.removeAttribute("cart");

        // redirect to confirmation page
        response.sendRedirect("order-success");
    }
}