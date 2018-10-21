CREATE TABLE ADDRESS (
    id                 INTEGER PRIMARY KEY NOT NULL,
    postal_code        CHAR(6) NOT NULL,
    street_number      INT,
    street_name        TEXT,
    street_direction   CHAR(1),
    city               TEXT,
    province           TEXT
);
