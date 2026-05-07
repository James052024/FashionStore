<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <!-- Global CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">

    <!-- Login CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/login.css">
</head>

<body>

<%@ include file="partials/navbar.jsp" %>

<div class="form-container">

    <h2>Login</h2>

    <!-- ERROR MESSAGE -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p class="error"><%= error %></p>
    <%
        }
    %>

    <form action="<%=request.getContextPath()%>/login" method="post">

        <input type="email" name="email" placeholder="Email" required>

        <input type="password" name="password" placeholder="Password" required>

        <button type="submit">Login</button>

    </form>

    <p>
        Don't have an account?
        <a href="<%=request.getContextPath()%>/register">Register</a>
    </p>

</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>