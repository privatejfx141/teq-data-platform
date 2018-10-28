CREATE TABLE NewcomerChildCare (
    id                  INTEGER PRIMARY KEY NOT NULL,
    service_id          INTEGER NOT NULL,
    age                 VARCHAR(32) NOT NULL,
    care_type           VARCHAR(32) NOT NULL,
    FOREIGN KEY(service_id) REFERENCES Service(id)
);
