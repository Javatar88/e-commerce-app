
INSERT INTO CATEGORY (id, description, name) VALUES
(NEXTVAL('category_seq'), 'Electronics and gadgets', 'Electronics'),
(NEXTVAL('category_seq'), 'Home and kitchen appliances', 'Home Appliances'),
(NEXTVAL('category_seq'), 'Books and stationery', 'Books'),
(NEXTVAL('category_seq'), 'Computer peripherals', 'Keyboards'),
(NEXTVAL('category_seq'), 'Computer displays', 'Monitors'),
(NEXTVAL('category_seq'), 'Mobile accessories', 'Accessories');


INSERT INTO PRODUCT (id, description, name, available_quantity, price, category_id) VALUES
(NEXTVAL('product_seq'), 'Smartphone with 128GB storage', 'Smartphone', 50, 699.99, 1),
(NEXTVAL('product_seq'), 'LED Television 42-inch', 'Television', 20, 399.99, 1),
(NEXTVAL('product_seq'), 'Microwave oven with grill', 'Microwave', 30, 199.99, 2),
(NEXTVAL('product_seq'), 'Fiction novel', 'Book', 100, 15.99, 3),
(NEXTVAL('product_seq'), 'Mechanical keyboard with RGB lighting', 'Keyboard', 25, 89.99, 4),
(NEXTVAL('product_seq'), '24-inch Full HD computer monitor', 'Monitor', 15, 129.99, 5),
(NEXTVAL('product_seq'), 'Protective smartphone case', 'Phone Case', 70, 19.99, 6);

-- Commit the transaction
COMMIT;