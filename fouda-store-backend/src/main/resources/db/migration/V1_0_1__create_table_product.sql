create table product (
    id BIGSERIAL NOT NULL ,
    label varchar NOT NULL ,
    description varchar NOT NULL ,
    prix DECIMAL NOT NULL,
    rating NUMERIC NOT NULL,
    image varchar,
    PRIMARY KEY (id)
)
