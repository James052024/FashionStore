<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="partials/header.jsp" %>
<%@ include file="partials/navbar.jsp" %>

<div style="text-align:center; padding:50px;">

    <h2>🎉 Order Placed Successfully!</h2>

    <p>Thank you for shopping with us.</p>

    <a href="<%=request.getContextPath()%>/products">
        Continue Shopping
    </a>

</div>