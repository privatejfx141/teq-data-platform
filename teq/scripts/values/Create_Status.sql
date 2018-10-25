CREATE TABLE Status (
    id CHAR(3) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO Status
    (id, description)
VALUES
	('EAR', 'Service ended early (i.e. client ended participation)'),
	('ONG', 'Ongoing'),
	('COM', 'Completed');