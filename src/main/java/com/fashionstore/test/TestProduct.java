package com.fashionstore.test;

import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

import java.util.List;

public class TestProduct {

    public static void main(String[] args) {

        ProductDAOImpl dao = new ProductDAOImpl();

        try {
            List<Product> list = dao.getAllProducts();

            if (list == null || list.isEmpty()) {
                System.out.println("❌ No products found");
                return;
            }

            System.out.println("✅ Products:");

            for (Product p : list) {
                System.out.println(
                        p.getProductId() + " | " +
                        p.getProductName() + " | " +
                        p.getBrand() + " | ₹" +
                        p.getPrice()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}