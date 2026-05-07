<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="partials/header.jsp" %>

<body>

<%@ include file="partials/navbar.jsp" %>

<div class="checkout-container">

    <div class="checkout-card">

        <h2>Checkout</h2>
        <p class="subtitle">Enter your delivery details</p>

        <form action="<%=request.getContextPath()%>/order" method="post">

            <!-- ADDRESS -->
            <div class="form-group">
                <label>Address</label>
                <input type="text" name="address" placeholder="Enter Address" required>
            </div>

            <!-- CITY -->
            <div class="form-group">
                <label>City</label>
                <input type="text" name="city" placeholder="City" required>
            </div>

            <!-- PINCODE -->
            <div class="form-group">
                <label>Pincode</label>
                <input type="text" name="pincode" placeholder="Pincode" required>
            </div>

            <!-- BUTTON -->
            <button type="submit" class="place-order-btn">
                Place Order
            </button>

        </form>

    </div>

</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>