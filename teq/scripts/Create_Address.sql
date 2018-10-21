CREATE TABLE Address (
    id                  INTEGER PRIMARY KEY NOT NULL,
    postal_code         CHAR(6) NOT NULL,
    street_number       INT,
    street_name         VARCHAR(32),
    street_direction    CHAR(1),
    city                VARCHAR(32),
    province            VARCHAR(32)
);
