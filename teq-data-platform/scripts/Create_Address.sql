CREATE TABLE Address (
    id                  INTEGER PRIMARY KEY NOT NULL,
    postal_code         CHAR(6) NOT NULL,
    unit_number         INTEGER,
    street_number       INTEGER,
    street_name         VARCHAR(32),
    street_type         VARCHAR(32),
    street_direction    VARCHAR(2),
    city                VARCHAR(32),
    province            VARCHAR(32)
);
