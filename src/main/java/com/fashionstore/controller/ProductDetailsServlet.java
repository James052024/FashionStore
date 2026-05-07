package com.fashionstore.controller;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.ProductVariantDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.dao.impl.ProductVariantDAOImpl;
import com.fashionstore.model.Product;
import com.fashionstore.model.ProductVariant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/product")   // 🔥 VERY IMPORTANT
public class ProductDetailsServlet extends HttpServlet {

    private ProductDAO productDAO;
    private ProductVariantDAO variantDAO;

    @Override
    public void init() {
        productDAO = new ProductDAOImpl();
        variantDAO = new ProductVariantDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int productId = Integer.parseInt(request.getParameter("id"));

            Product product = productDAO.getProductById(productId);
            List<ProductVariant> variants = variantDAO.getVariantsByProductId(productId);

            request.setAttribute("product", product);
            request.setAttribute("variants", variants);

            request.getRequestDispatcher("/WEB-INF/views/product-details.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("products");
        }
    }
}