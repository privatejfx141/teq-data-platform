CREATE TABLE Institution (
    InstitutionID    CHAR(3) PRIMARY KEY NOT NULL,
    description VARCHAR(32) NOT NULL
);

INSERT INTO Institution
    (InstitutionID, description)
VALUES
    ('SSP', 'Settlement service provider'),
	('LIB', 'Public library'),
	('ELE', 'Elementary school'),
	('SEC', 'Secondary school'),
	('POS', 'Post-secondary institution'),
	('HEA', 'Healthcare institution'),
	('COM', 'Community centre/neighbourhood house'),
	('EMP', 'Employment agency'),
	('HOM', 'The client’s home'),
	('POE', 'The client’s place of employment'),
	('PSP', 'A public space (shopping centre, etc.)'),
	('POR', 'Port of entry'),
	('ONL', 'Online'),
	('NPR', 'Not for profit organization');