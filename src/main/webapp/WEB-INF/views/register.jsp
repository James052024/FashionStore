<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Register</title>

    <!-- GLOBAL CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">

    <!-- REGISTER PAGE CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/register.css">
</head>

<body>

<%@ include file="partials/navbar.jsp" %>

<div class="form-container">

    <h2>Register</h2>

    <form action="<%=request.getContextPath()%>/register" method="post">

        <input type="text" name="fullName" placeholder="Full Name" required>

        <input type="email" name="email" placeholder="Email" required>

        <input type="text" name="phone" placeholder="Phone" required>

        <input type="password" name="password" placeholder="Password" required>

        <input type="text" name="address1" placeholder="Address Line 1" required>

        <input type="text" name="address2" placeholder="Address Line 2">

        <input type="text" name="city" placeholder="City" required>

        <input type="text" name="state" placeholder="State" required>

        <input type="text" name="pincode" placeholder="Pincode" required>

        <button type="submit">Register</button>

    </form>

    <p>
        Already have an account? 
        <a href="<%=request.getContextPath()%>/login">Login</a>
    </p>

</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>