package com.fashionstore.util;

import com.fashionstore.dao.impl.CategoryDAOImpl;
import com.fashionstore.model.Category;

import java.util.List;

public class TestCategory {

    public static void main(String[] args) {

        CategoryDAOImpl dao = new CategoryDAOImpl();

        List<Category> list = dao.getAllCategories();

        for (Category c : list) {
            System.out.println(c.getCategoryId() + " " + c.getCategoryName());
        }
    }
}