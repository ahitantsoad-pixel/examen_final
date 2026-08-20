package gestion.Controller;

import gestion.Model.StockMovement;
import gestion.Service.StockMovementService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    // GET /stock-movements?type=in|out
    @GetMapping("/stock-movements")
    public ResponseEntity<List<StockMovement>> getAllStockMovements(
            @RequestParam(required = false) String type) {
        return ResponseEntity.ok(stockMovementService.findAll(type));
    }

    // GET /products/{id}/stock-movements
    @GetMapping("/products/{id}/stock-movements")
    public ResponseEntity<List<StockMovement>> getStockMovementsByProduct(@PathVariable String id) {
        return ResponseEntity.ok(stockMovementService.findByProductId(id));
    }

    // POST /stock-movements
    @PostMapping("/stock-movements")
    public ResponseEntity<StockMovement> createStockMovement(@RequestBody StockMovement stockMovement) {
        StockMovement created = stockMovementService.create(stockMovement);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}