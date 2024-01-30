create table purchase_order_products
(
    purchase_order_id BIGSERIAL,
    product_id BIGSERIAL,
    PRIMARY KEY (purchase_order_id,product_id)
);
ALTER TABLE purchase_order_products
    ADD CONSTRAINT purchase_order_products_purchase_order_id_fk FOREIGN KEY (purchase_order_id) references purchase_order (id);

ALTER TABLE purchase_order_products
    ADD CONSTRAINT purchase_order_products_product_id FOREIGN KEY (product_id) references product (id);