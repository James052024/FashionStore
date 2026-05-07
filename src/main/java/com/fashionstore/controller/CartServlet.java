package com.fashionstore.controller;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.impl.CartDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private CartDAO cartDAO;

    @Override
    public void init() {
        cartDAO = new CartDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) action = "view";

        switch (action) {

            case "remove":
                removeItem(request, response);
                break;

            case "view":
            default:
                viewCart(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {

            case "add":
                addItem(request, response);
                break;

            case "update":
                updateItem(request, response);
                break;

            default:
                response.sendRedirect("cart");
        }
    }

    // 🔹 VIEW CART
    private void viewCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        request.setAttribute("cartItems", cartDAO.getCartItemsByUserId(userId));

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
               .forward(request, response);
    }

    // 🔹 ADD ITEM
    private void addItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        int variantId = Integer.parseInt(request.getParameter("variantId"));
        int quantity = 1;

        cartDAO.addToCart(userId, variantId, quantity);

        response.sendRedirect("cart");
    }

    // 🔹 UPDATE ITEM
    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int variantId = Integer.parseInt(request.getParameter("variantId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        cartDAO.updateItemQuantity(cartId, variantId, quantity);

        response.sendRedirect("cart");
    }

    // 🔹 REMOVE ITEM
    private void removeItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int variantId = Integer.parseInt(request.getParameter("variantId"));

        cartDAO.removeItemFromCart(cartId, variantId);

        response.sendRedirect("cart");
    }
}