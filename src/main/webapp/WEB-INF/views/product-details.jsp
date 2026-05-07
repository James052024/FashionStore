<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.fashionstore.model.Product, com.fashionstore.model.ProductVariant, java.util.*" %>

<%@ include file="partials/header.jsp" %>

<body>

<%@ include file="partials/navbar.jsp" %>

<%
    Product p = (Product) request.getAttribute("product");
    List<ProductVariant> variants = (List<ProductVariant>) request.getAttribute("variants");
%>

<div class="details-container">

<% if (p != null) { %>

    <div class="details-card">

        <!-- IMAGE -->
        <div class="image-section">
            <img src="<%=request.getContextPath()%>/<%=p.getImageUrl()%>" alt="Product Image" />
        </div>

        <!-- INFO -->
        <div class="info-section">

            <h2><%= p.getProductName() %></h2>
            <p class="brand"><%= p.getBrand() %></p>
            <h3 class="price">₹ <%= p.getPrice() %></h3>
            <p class="desc"><%= p.getDescription() %></p>

            <!-- ADD TO CART FORM -->
            <form action="<%=request.getContextPath()%>/cart" method="post">

                <!-- REQUIRED FOR CART SERVLET -->
                <input type="hidden" name="action" value="add">

                <!-- VARIANT / SIZE -->
                <div class="sizes">
                    <h4>Select Size:</h4>

                    <% if (variants != null && !variants.isEmpty()) {
                        for (ProductVariant v : variants) { %>

                        <label class="size-option">
                            <input type="radio" name="variantId"
                                   value="<%=v.getVariantId()%>" required>
                            <span><%= v.getSize() %></span>
                        </label>

                    <% } } else { %>
                        <p style="color:red;">No sizes available</p>
                    <% } %>

                </div>

                <!-- QUANTITY -->
                <div style="margin-top:15px;">
                    <label>Quantity:</label>
                    <input type="number" name="quantity" value="1" min="1" style="width:60px;">
                </div>

                <!-- BUTTON -->
                <button type="submit" class="add-btn">Add to Cart</button>

            </form>

        </div>

    </div>

<% } else { %>

    <h2 style="text-align:center;">Product not found</h2>

<% } %>

</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>