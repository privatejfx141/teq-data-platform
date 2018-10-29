CREATE TABLE Translation (
    service_id          INTEGER PRIMARY KEY NOT NULL,
    language_between    CHAR(3) NOT NULL,
    language_and        CHAR(3) NOT NULL,
    FOREIGN KEY(service_id) REFERENCES service(id)
);

CREATE TABLE Interpretation (
    service_id          INTEGER PRIMARY KEY NOT NULL,
    language_between    CHAR(3) NOT NULL,
    language_and        CHAR(3) NOT NULL,
    FOREIGN KEY(service_id) REFERENCES service(id)
);
