package gestion.Model;

import java.time.Instant;

public class StockMovement {
    private String id;
    private Instant createdAt;
    private MovementType movementType;
    private int quantity;

    public StockMovement(String id, Instant createdAt, MovementType movementType, int quantity) {
        this.id = id;
        this.createdAt = createdAt;
        this.movementType = movementType;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
