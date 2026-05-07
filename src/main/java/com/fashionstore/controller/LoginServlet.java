package com.fashionstore.controller;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.dao.impl.UserDAOImpl;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    // 👉 Load login page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
               .forward(request, response);
    }

    // 👉 Handle login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String email = request.getParameter("email").trim();
            String password = request.getParameter("password").trim();

            User user = userDAO.getUserByEmail(email);

            if (user == null) {

                // ❌ Email not registered
                request.setAttribute("error", "Email not registered");

                request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                       .forward(request, response);

            } else if (!user.getPassword().equals(password)) {

                // ❌ Wrong password
                request.setAttribute("error", "Invalid password");

                request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                       .forward(request, response);

            } else {

                // ✅ Success
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userName", user.getFullName());

                response.sendRedirect(request.getContextPath() + "/home");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}