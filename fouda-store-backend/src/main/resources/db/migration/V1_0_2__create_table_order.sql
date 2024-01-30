create table purchase_order
(
    id         BIGSERIAL NOT NULL,
    client_id  BIGSERIAL NOT NULL,
    order_date timestamp,
    status     varchar,
    PRIMARY KEY (id)
);
ALTER TABLE purchase_order
    ADD CONSTRAINT CLIENT_ID_FK FOREIGN KEY (client_id) references CLIENT (id);