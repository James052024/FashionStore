package com.fashionstore.test;

import java.sql.Connection;
import com.fashionstore.util.DBConnection;

public class TestDBConnection {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            if (con != null) {
                System.out.println("✅ DB Connected Successfully");
            } else {
                System.out.println("❌ Connection Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}