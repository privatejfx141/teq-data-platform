CREATE TABLE ChildType (
    ChildTypeID    CHAR(2) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO ChildType
    (ChildTypeID, description)
VALUES
	('SH', 'Short term'),
	('LG', 'Long term');