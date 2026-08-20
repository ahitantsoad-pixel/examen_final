package gestion.DAO;

import gestion.Model.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/stock_management",
                "postgres",
                "Ahitamamba02!");
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try (Connection c = getConnection();
             Statement statement = c.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product")) {

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

    public Product findById(String id) throws SQLException {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getBigDecimal("unit_price")
                    );
                }
                return null;
            }
        }
    }

    public void save(Product product) throws SQLException {
        String sql = "INSERT INTO product (id, name, description, unit_price) VALUES (?, ?, ?, ?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setBigDecimal(4, product.getUnitPrice());
            ps.executeUpdate();
        }
    }

    public boolean existsById(String productId) throws SQLException {
        String sql = "SELECT 1 FROM product WHERE id = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public int calculateTotalStock(String productId) throws SQLException {
        String sql = "SELECT COALESCE(SUM(CASE WHEN movement_type = 'IN' THEN quantity ELSE -quantity END), 0) " +
                "FROM stock_movement WHERE product_id = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }
}