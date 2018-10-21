CREATE TABLE SERVICE (
    id                 INTEGER PRIMARY KEY NOT NULL,
    client_id          INTEGER NOT NULL,
    language           CHAR(3),
    organization_type  TEXT,
    referred_by        TEXT,
    update_reason      TEXT,
    service_type       CHAR(1),
    FOREIGN KEY(client_id) REFERENCES CLIENT(id)
);
