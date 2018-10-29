CREATE TABLE EmploymentStatus (
    id CHAR(2) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO EmploymentStatus
    (id, description)
VALUES
	('UN', 'Unemployed'),
	('PT', 'Employed part time - less than 30 hours at main or only job'),
	('FT', 'Employed full time - 30 hours or more at main or only job');
