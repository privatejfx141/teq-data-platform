CREATE TABLE IF NOT EXISTS UserRole (
    id                  INTEGER PRIMARY KEY NOT NULL,
    name                VARCHAR(64) NOT NULL UNIQUE                
);

CREATE TABLE IF NOT EXISTS User (
    id                  INTEGER PRIMARY KEY NOT NULL,
    username            VARCHAR(64) NOT NULL UNIQUE,
    role_id             INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES UserRole(id)
);

CREATE TABLE IF NOT EXISTS UserPassword (
    user_id             INTEGER PRIMARY KEY NOT NULL,
    password            CHAR(64) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id)
);
