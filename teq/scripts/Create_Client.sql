CREATE TABLE Client (
    id                  INTEGER PRIMARY KEY NOT NULL,
    id_type             VARCHAR(32) NOT NULL,
    birth_date          DATE NOT NULL,
    phone_number        VARCHAR(16),
    email_address       VARCHAR(32),
    address_id          INTEGER NOT NULL,
    language            CHAR(3) NOT NULL,
    consents            BOOLEAN NOT NULL,
    FOREIGN KEY(address_id) REFERENCES Address(id)
);
