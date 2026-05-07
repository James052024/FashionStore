<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.fashionstore.model.Product" %>

<%@ include file="partials/header.jsp" %>

<body>

<%@ include file="partials/navbar.jsp" %>

<div class="container">

    <h2 style="text-align:center;">Products</h2>

    <div class="grid">

        <%
            List<Product> products = (List<Product>) request.getAttribute("products");

            if (products != null && !products.isEmpty()) {
                for (Product p : products) {
        %>

        <a href="<%=request.getContextPath()%>/product?id=<%=p.getProductId()%>" class="product-link">

            <div class="card">

                <!-- IMAGE -->
                <img src="<%=request.getContextPath()%>/<%=p.getImageUrl()%>" alt="Product Image" />

                <!-- DETAILS -->
                <h3><%= p.getProductName() %></h3>
                <p><%= p.getBrand() %></p>
                <div class="price">₹ <%= p.getPrice() %></div>

            </div>

        </a>

        <%
                }
            } else {
        %>

            <!-- EMPTY STATE -->
            <p style="text-align:center; width:100%; font-size:18px;">
                No products available
            </p>

        <%
            }
        %>

    </div>

</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>