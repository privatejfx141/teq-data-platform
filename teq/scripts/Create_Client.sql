CREATE TABLE ClientIDType (
    id                  INTEGER PRIMARY KEY NOT NULL,
    description         VARCHAR(32) NOT NULL
);

INSERT INTO ClientIDType 
    (description)
VALUES
    ('FOSS/GCMS Client ID'),
    ('Temporary Resident or Minister’s Permit Number'),
    ('IMM5292, IMM5509, IMM1000 Number');

CREATE TABLE Client (
    id                  INTEGER PRIMARY KEY NOT NULL,
    id_type             INTEGER NOT NULL,
    birth_date          DATE NOT NULL,
    phone_number        VARCHAR(16),
    email_address       VARCHAR(32),
    address_id          INTEGER NOT NULL,
    language            CHAR(3) NOT NULL,
    consents            BOOLEAN NOT NULL DEFAULT 0,
    FOREIGN KEY(address_id) REFERENCES Address(id),
    FOREIGN KEY(id_type) REFERENCES ClientIDType(id)
);
