package com.fashionstore.dao.impl;

import com.fashionstore.dao.ProductVariantDAO;
import com.fashionstore.model.ProductVariant;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductVariantDAOImpl implements ProductVariantDAO {

    private Connection con;

    public ProductVariantDAOImpl() {
        con = DBConnection.getConnection();
    }

    // ✅ Add Variant
    @Override
    public boolean addVariant(ProductVariant variant) {

        String sql = "INSERT INTO product_variants(product_id, size, stock_quantity) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, variant.getProductId());
            ps.setString(2, variant.getSize());
            ps.setInt(3, variant.getStockQuantity());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ Get variants by product
    @Override
    public List<ProductVariant> getVariantsByProductId(int productId) {

        List<ProductVariant> list = new ArrayList<>();

        String sql = "SELECT * FROM product_variants WHERE product_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ProductVariant v = new ProductVariant();

                v.setVariantId(rs.getInt("variant_id"));
                v.setProductId(rs.getInt("product_id"));
                v.setSize(rs.getString("size"));
                v.setStockQuantity(rs.getInt("stock_quantity"));

                list.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Get single variant
    @Override
    public ProductVariant getVariantById(int variantId) {

        String sql = "SELECT * FROM product_variants WHERE variant_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, variantId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                ProductVariant v = new ProductVariant();

                v.setVariantId(rs.getInt("variant_id"));
                v.setProductId(rs.getInt("product_id"));
                v.setSize(rs.getString("size"));
                v.setStockQuantity(rs.getInt("stock_quantity"));

                return v;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ✅ Update stock
    @Override
    public boolean updateStock(int variantId, int quantity) {

        String sql = "UPDATE product_variants SET stock_quantity = ? WHERE variant_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, variantId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ Delete variant
    @Override
    public boolean deleteVariant(int variantId) {

        String sql = "DELETE FROM product_variants WHERE variant_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, variantId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}