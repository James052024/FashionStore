package com.fashionstore.test;

import com.fashionstore.dao.impl.CategoryDAOImpl;
import com.fashionstore.model.Category;

import java.util.List;

public class TestCategory {

    public static void main(String[] args) {

        CategoryDAOImpl dao = new CategoryDAOImpl();

        try {
            List<Category> list = dao.getAllCategories();

            if (list == null || list.isEmpty()) {
                System.out.println("❌ No categories found");
                return;
            }

            System.out.println("✅ Categories:");

            for (Category c : list) {
                System.out.println(
                        c.getCategoryId() + " - " +
                        c.getCategoryName() + " - " +
                        c.getDescription()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}