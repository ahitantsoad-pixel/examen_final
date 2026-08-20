package gestion.Service;

import gestion.Model.StockMovement;
import gestion.DAO.ProductDAO;
import gestion.DAO.StockMovementDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementDAO stockMovementDAO;
    private final ProductDAO productDAO;

    public StockMovementService(StockMovementDAO stockMovementDAO, ProductDAO productDAO) {
        this.stockMovementDAO = stockMovementDAO;
        this.productDAO = productDAO;
    }

    public List<StockMovement> getStockMovements(String type) {
        try {
            return stockMovementDAO.findAll(type);
        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching stock movements", e);
        }
    }

    public List<StockMovement> getMovementsByProduct(String productId) {
        try {
            return stockMovementDAO.findByProductId(productId);
        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching stock movements", e);
        }
    }

    public void createStockMovement(StockMovement movement) {
        if (movement.getQuantity() <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à zéro");
        }
        if (movement.getCreatedAt() == null) {
            movement.setCreatedAt(Instant.now());
        }
        try {
            stockMovementDAO.save(movement);
        } catch (SQLException e) {
            throw new RuntimeException("Database error while creating stock movement", e);
        }
    }

    public int getProductStock(String productId) {
        try {
            return productDAO.calculateTotalStock(productId);
        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching stock", e);
        }
    }
}