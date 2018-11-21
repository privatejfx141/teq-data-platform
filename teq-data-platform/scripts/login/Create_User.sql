CREATE TABLE IF NOT EXISTS Role (
    id                  INTEGER PRIMARY KEY NOT NULL,
    description         VARCHAR(64) NOT NULL UNIQUE                
);

CREATE TABLE IF NOT EXISTS User (
    id                  INTEGER PRIMARY KEY NOT NULL,
    username            VARCHAR(64) NOT NULL UNIQUE,
    password            CHAR(64) NOT NULL,
    role_id             INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES UserRole(id)
);
