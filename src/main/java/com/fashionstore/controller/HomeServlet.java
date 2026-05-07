package com.fashionstore.controller;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.CategoryDAOImpl;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Category;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;

    @Override
    public void init() {
        categoryDAO = new CategoryDAOImpl();
        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Fetch categories
            List<Category> categories = categoryDAO.getAllCategories();

            // Fetch products (we use all for now)
            List<Product> products = productDAO.getAllProducts();

            // Send to JSP
            request.setAttribute("categories", categories);
            request.setAttribute("products", products);

            // Forward to home page
            request.getRequestDispatcher("/WEB-INF/views/home.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}