CREATE DATABASE stock_management
    WITH ENCODING = 'UTF8'
    LC_COLLATE = 'fr_FR.UTF-8'
    LC_CTYPE = 'fr_FR.UTF-8'
    TEMPLATE = template0;

\c stock_management

CREATE TYPE movement_type AS ENUM ('IN', 'OUT');

CREATE TABLE product (
                         id           VARCHAR(36) PRIMARY KEY,
                         name         VARCHAR(255) NOT NULL,
                         description  TEXT,
                         unit_price   NUMERIC(12, 2) NOT NULL CHECK (unit_price >= 0)
);

CREATE TABLE stock_movement (
                                id             VARCHAR(36) PRIMARY KEY,
                                created_at     TIMESTAMPTZ NOT NULL DEFAULT now(),
                                movement_type  movement_type NOT NULL,
                                quantity       INTEGER NOT NULL CHECK (quantity > 0),
                                product_id     VARCHAR(36) NOT NULL,
                                CONSTRAINT fk_stock_movement_product
                                    FOREIGN KEY (product_id)
                                        REFERENCES product (id)
                                        ON DELETE CASCADE
);

CREATE INDEX idx_stock_movement_product_id ON stock_movement (product_id);
CREATE INDEX idx_stock_movement_type ON stock_movement (movement_type);