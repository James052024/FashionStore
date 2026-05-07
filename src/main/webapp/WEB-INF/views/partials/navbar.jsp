<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String userName = (String) session.getAttribute("userName");
%>

<div class="navbar">

    <!-- LEFT (optional - can keep empty or add logo small) -->
    <div class="nav-left"></div>

    <!-- CENTER LOGO -->
    <div class="nav-center">
        <a class="logo" href="<%=request.getContextPath()%>/home">FashionStore</a>
    </div>

    <!-- RIGHT SIDE NAV -->
    <div class="nav-right">

        <% if (userName == null) { %>

            <a href="<%=request.getContextPath()%>/login">Login</a>
            <a href="<%=request.getContextPath()%>/register">Register</a>

        <% } else { %>

            <a href="<%=request.getContextPath()%>/home">Home</a>
            <a href="<%=request.getContextPath()%>/products">Products</a>
            <a href="<%=request.getContextPath()%>/cart">Cart</a>

            <span class="username">Hi, <%= userName %></span>

            <a href="<%=request.getContextPath()%>/logout">Logout</a>

        <% } %>

    </div>

</div>