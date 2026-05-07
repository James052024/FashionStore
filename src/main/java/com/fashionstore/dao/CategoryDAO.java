package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.Category;

public interface CategoryDAO {

    List<Category> getAllCategories();

    Category getCategoryById(int categoryId);

    Category getCategoryByName(String name);

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int categoryId);
}