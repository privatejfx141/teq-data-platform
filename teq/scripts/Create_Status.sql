CREATE TABLE Status (
    StatusID    CHAR(3) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO Status
    (StatusID, description)
VALUES
	('EAR', 'Service ended early (i.e. client ended participation)'),
	('ONG', 'Ongoing'),
	('COM', 'Completed');