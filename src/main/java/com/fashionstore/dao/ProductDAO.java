package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.Product;

public interface ProductDAO {

    List<Product> getAllProducts();

    Product getProductById(int productId);

    List<Product> getProductsByCategory(int categoryId);

    List<Product> searchProducts(String keyword);

    List<Product> filterProducts(int categoryId, String brand, double minPrice, double maxPrice);

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);
}