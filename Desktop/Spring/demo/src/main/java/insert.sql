INSERT INTO product (id, name, description, unit_price) VALUES
                                                            ('p1', 'Clavier mécanique', 'Clavier mécanique rétroéclairé', 45000.00),
                                                            ('p2', 'Souris sans fil', 'Souris ergonomique Bluetooth', 25000.00),
                                                            ('p3', 'Écran 24 pouces', 'Moniteur Full HD 24"', 320000.00);

INSERT INTO stock_movement (id, created_at, movement_type, quantity, product_id) VALUES
                                                                                     ('m1', now(), 'IN', 50, 'p1'),
                                                                                     ('m2', now(), 'IN', 30, 'p2'),
                                                                                     ('m3', now(), 'OUT', 10, 'p1');