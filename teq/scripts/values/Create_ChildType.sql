CREATE TABLE ChildType (
    id CHAR(2) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO ChildType
    (id, description)
VALUES
	('SH', 'Short term'),
	('LG', 'Long term');