package gestion.DAO;

import gestion.Model.MovementType;
import gestion.Model.StockMovement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockMovementDAO {
    public List<StockMovement> findAll() {
        List<StockMovement> stockMovements = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/stock_management",
                "postgres",
                "Ahitamamba02!"

        );
             Statement statement = c.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM \"stock_movement\"")) {

            while (resultSet.next()) {
                stockMovements.add(new StockMovement(
                        resultSet.getString("id"),
                        resultSet.getTimestamp("created_at").toInstant(),
                        MovementType.valueOf(resultSet.getString("movement_type")),
                        resultSet.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error with the database : " + e.getMessage());
            e.printStackTrace();
        }

        return stockMovements;
    }
}