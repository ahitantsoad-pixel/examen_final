package gestion.DAO;

import gestion.Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/stock_management",
                "postgres",
                "Ahitamamba02!"

        );
             Statement statement = c.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM \"product\"")) {

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("unit_price")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error with the database : " + e.getMessage());
            e.printStackTrace();
        }

        return products;
    }
}