<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.fashionstore.model.CartItem" %>

<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/cart.css">
</head>

<body>

<%@ include file="partials/navbar.jsp" %>

<div class="cart-container">

    <h2>Your Cart</h2>

    <%
        List<CartItem> items = (List<CartItem>) request.getAttribute("cartItems");
        double total = 0;
    %>

    <%
        if (items == null || items.isEmpty()) {
    %>
        <p class="empty">Your cart is empty 🛒</p>
    <%
        } else {
            for (CartItem item : items) {
                double itemTotal = item.getPrice() * item.getQuantity();
                total += itemTotal;
    %>

    <div class="cart-item">

        <!-- IMAGE -->
        <img src="<%=request.getContextPath()%>/<%=item.getImageUrl()%>" />

        <!-- DETAILS -->
        <div class="details">
            <h3><%= item.getProductName() %></h3>
            <p>Size: <%= item.getSize() %></p>
            <p>Price: ₹<%= item.getPrice() %></p>
        </div>

        <!-- UPDATE QUANTITY -->
        <form action="<%=request.getContextPath()%>/cart" method="post" class="qty-form">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="cartId" value="<%=item.getCartId()%>">
            <input type="hidden" name="variantId" value="<%=item.getVariantId()%>">

            <input type="number" name="quantity" value="<%=item.getQuantity()%>" min="1">

            <button type="submit">Update</button>
        </form>

        <!-- REMOVE ITEM -->
        <a class="remove-btn"
           href="<%=request.getContextPath()%>/cart?action=remove&cartId=<%=item.getCartId()%>&variantId=<%=item.getVariantId()%>">
           Remove
        </a>

    </div>

    <%
            }
        }
    %>

    <!-- TOTAL -->
    <%
        if (items != null && !items.isEmpty()) {
    %>
        <div class="total-box">
            <h3>Total: ₹ <%= total %></h3>
            <button class="checkout-btn">Proceed to Checkout</button>
        </div>
    <%
        }
    %>
    <a href="<%=request.getContextPath()%>/checkout" class="checkout-btn">
    Proceed to Checkout
</a>

</div>

</body>
</html>