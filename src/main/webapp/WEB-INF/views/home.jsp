<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.fashionstore.model.Product, com.fashionstore.model.Category" %>

<%@ include file="partials/header.jsp" %>

<body>

<%@ include file="partials/navbar.jsp" %>

<!-- ===== HERO SECTION ===== -->
<div class="hero">
    <div>
        <h1>Welcome to FashionStore</h1>
        <p>Discover the latest trends in fashion</p>
    </div>
</div>

<!-- ===== CATEGORY SECTION ===== -->
<div class="categories">
    <h2 class="section-title">Categories</h2>

    <div class="category-list">

        <%
            List<Category> categories = (List<Category>) request.getAttribute("categories");

            if (categories != null) {
                for (Category c : categories) {
        %>

        <div class="category-item">
            <%= c.getCategoryName() %>
        </div>

        <%
                }
            }
        %>

    </div>
</div>

<!-- ===== FEATURED PRODUCTS ===== -->
<div class="featured">

    <h2 class="section-title">Featured Products</h2>

    <div class="grid">

        <%
            List<Product> products = (List<Product>) request.getAttribute("products");

            if (products != null) {
                for (Product p : products) {
        %>

        <div class="card">

            <img src="<%= request.getContextPath() + "/" + p.getImageUrl() %>" />

            <h3><%= p.getProductName() %></h3>

            <p><%= p.getBrand() %></p>

            <div class="price">₹ <%= p.getPrice() %></div>

        </div>

        <%
                }
            }
        %>

    </div>

</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>