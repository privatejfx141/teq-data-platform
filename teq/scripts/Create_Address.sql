CREATE TABLE Address (
    id                  INTEGER PRIMARY KEY NOT NULL,
    postal_code         CHAR(6) NOT NULL,
    street_number       INT,
    street_name         VARCHAR(32),
    street_direction    VARCHAR(2) CHECK (street_direction IN (
        "NW", "N", "NE", "E", "SE", "S", "SW", "W"
    )),
    city                VARCHAR(32),
    province            VARCHAR(32)
);
