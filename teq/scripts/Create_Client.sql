CREATE TABLE CLIENT (
    id                 INTEGER PRIMARY KEY NOT NULL,
    id_type            TEXT NOT NULL,
    birth_date         DATE NOT NULL,
    phone_number       VARCHAR(16),
    email_address      VARCHAR(64),
    address_id         INTEGER NOT NULL,
    language           CHAR(3) NOT NULL,
    consents           TINYINT NOT NULL,
    FOREIGN KEY(address_id) REFERENCES ADDRESS(id)
);
