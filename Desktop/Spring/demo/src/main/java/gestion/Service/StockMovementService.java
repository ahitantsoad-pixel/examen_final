// package com.example.monprojet.service;

import com.example.monprojet.model.StockMovement;
import com.example.monprojet.repository.ProductRepository;
import com.example.monprojet.repository.StockMovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository, ProductRepository productRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
    }


    public List<StockMovement> getStockMovements(String type) {
        return stockMovementRepository.findAll(type);
    }
    public List<StockMovement> getMovementsByProduct(String productId) {
        return stockMovementRepository.findByProductId(productId);
    }


    public void createStockMovement(StockMovement movement) {

        if (movement.getQuantity() <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à zéro");
        }
        if (movement.getCreatedAt() == null) {
            movement.setCreatedAt(java.time.Instant.now()); // Date du jour par défaut
        }
        stockMovementRepository.save(movement);
    }


    public int getProductStock(String productId) {
        return productRepository.calculateStock(productId);
    }
}