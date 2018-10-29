CREATE TABLE ChildAge (
    id CHAR(2) PRIMARY KEY NOT NULL,
	value VARCHAR(32) NOT NULL,
    description VARCHAR(32) NOT NULL
);

INSERT INTO ChildAge
    (id, value, description)
VALUES
	('IF', 'Infant', 'Infant (6-18 months)'),
	('TD', 'Toddler', 'Toddler (19-35 months)'),
	('PS', 'Preschool', 'Pre-school (36 months - 6 years)'),
	('SA', 'School', 'School age (more than 6 years)');
