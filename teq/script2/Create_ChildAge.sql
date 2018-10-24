CREATE TABLE ChildAge (
    ChildAgeID    CHAR(2) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO ChildAge
    (ChildAgeID, description)
VALUES
	('IF', 'Infant (6-18 months)'),
	('TD', 'Toddler (19-35 months)'),
	('PS', 'Pre-school (36 months - 6 years)'),
	('SA', 'School age (more than 6 years)');