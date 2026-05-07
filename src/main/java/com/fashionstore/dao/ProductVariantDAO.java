package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.ProductVariant;

public interface ProductVariantDAO {

    boolean addVariant(ProductVariant variant);

    List<ProductVariant> getVariantsByProductId(int productId);

    ProductVariant getVariantById(int variantId);

    boolean updateStock(int variantId, int quantity);

    boolean deleteVariant(int variantId);
}